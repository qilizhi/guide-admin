package com.mlx.guide.util.sensitiveword;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 敏感词过滤工具类
 * @author quan
 *
 */
public class WordFilterUtil {
	
	private static Node tree = new Node();

	static {
		//加载敏感词库
		InputStream is = WordFilterUtil.class.getResourceAsStream( "/SensitiveWord.txt" );
		try {
			InputStreamReader reader = new InputStreamReader( is, "UTF-8" );
			Properties prop = new Properties();
			prop.load( reader );
			Enumeration<?> en = prop.propertyNames();

			while( en.hasMoreElements() ) {
				String word = (String)en.nextElement();
				insertWord( word, Integer.valueOf( prop.getProperty( word ) ).intValue() );
			}
		}
		catch( UnsupportedEncodingException e ) {
			e.printStackTrace();
			if( is != null ) {
				try {
					is.close();
				}
				catch( IOException e1 ) {
					e.printStackTrace();
				}
			}
		}
		catch( IOException e ) {
			e.printStackTrace();
			if( is != null ) {
				try {
					is.close();
				}
				catch( IOException e2 ) {
					e.printStackTrace();
				}
			}
		}
		finally {
			if( is != null ) {
				try {
					is.close();
				}
				catch( IOException e ) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void insertWord( String word, int level ) {
		Node node = tree;
		for( int i = 0; i < word.length(); i++ ) {
			node = node.addChar( word.charAt( i ) );
		}
		node.setEnd( true );
		node.setLevel( level );
	}

	private static boolean isPunctuationChar( String c ) {
		String regex = "[\\pP\\pZ\\pS\\pM\\pC]";
		Pattern p = Pattern.compile( regex, 2 );
		Matcher m = p.matcher( c );
		return m.find();
	}

	private static PunctuationOrHtmlFilteredResult filterPunctation( String originalString ) {
		StringBuffer filteredString = new StringBuffer();
		ArrayList<Integer> charOffsets = new ArrayList<Integer>();
		for( int i = 0; i < originalString.length(); i++ ) {
			String c = String.valueOf( originalString.charAt( i ) );
			if( !isPunctuationChar( c ) ) {
				filteredString.append( c );
				charOffsets.add( Integer.valueOf( i ) );
			}
		}
		PunctuationOrHtmlFilteredResult result = new PunctuationOrHtmlFilteredResult();
		result.setOriginalString( originalString );
		result.setFilteredString( filteredString );
		result.setCharOffsets( charOffsets );
		return result;
	}

	private static PunctuationOrHtmlFilteredResult filterPunctationAndHtml( String originalString ) {
		StringBuffer filteredString = new StringBuffer();
		ArrayList<Integer> charOffsets = new ArrayList<Integer>();
		int i = 0;
		for( int k = 0; i < originalString.length(); i++ ) {
			String c = String.valueOf( originalString.charAt( i ) );
			if( originalString.charAt( i ) == '<' ) {
				for( k = i + 1; k < originalString.length(); k++ ) {
					if( originalString.charAt( k ) == '<' ) {
						k = i;
					}
					else {
						if( originalString.charAt( k ) == '>' ) {
							break;
						}
					}
				}
				i = k;
			}
			else if( !isPunctuationChar( c ) ) {
				filteredString.append( c );
				charOffsets.add( Integer.valueOf( i ) );
			}
		}
		PunctuationOrHtmlFilteredResult result = new PunctuationOrHtmlFilteredResult();
		result.setOriginalString( originalString );
		result.setFilteredString( filteredString );
		result.setCharOffsets( charOffsets );
		return result;
	}

	private static FilteredResult filter( PunctuationOrHtmlFilteredResult pohResult, char replacement ) {
		StringBuffer sentence = pohResult.getFilteredString();
		ArrayList<Integer> charOffsets = pohResult.getCharOffsets();
		StringBuffer resultString = new StringBuffer( pohResult.getOriginalString() );
		StringBuffer badWords = new StringBuffer();
		int level = 0;
		Node node = tree;
		int start = 0;
		int end = 0;
		for( int i = 0; i < sentence.length(); i++ ) {
			start = i;
			end = i;
			node = tree;
			for( int j = i; j < sentence.length(); j++ ) {
				node = node.findChar( sentence.charAt( j ) );
				if( node == null ) {
					break;
				}
				if( node.isEnd() ) {
					end = j;
					level = node.getLevel();
				}
			}
			if( end > start ) {
				for( int k = start; k <= end; k++ ) {
					resultString.setCharAt( ((Integer)charOffsets.get( k )).intValue(), replacement );
				}
				if( badWords.length() > 0 ) {
					badWords.append( "," );
				}
				badWords.append( sentence.substring( start, end + 1 ) );
				i = end;
			}
		}
		FilteredResult result = new FilteredResult();
		result.setOriginalContent( pohResult.getOriginalString() );
		result.setFilteredContent( resultString.toString() );
		result.setBadWords( badWords.toString() );
		result.setLevel( Integer.valueOf( level ) );
		return result;
	}

	public static String simpleFilter( String sentence, char replacement ) {
		StringBuffer sb = new StringBuffer();
		Node node = tree;
		int start = 0;
		int end = 0;
		for( int i = 0; i < sentence.length(); i++ ) {
			start = i;
			end = i;
			node = tree;
			for( int j = i; j < sentence.length(); j++ ) {
				node = node.findChar( sentence.charAt( j ) );
				if( node == null ) {
					break;
				}
				if( node.isEnd() ) {
					end = j;
				}
			}
			if( end > start ) {
				for( int k = start; k <= end; k++ ) {
					sb.append( replacement );
				}
				i = end;
			}
			else {
				sb.append( sentence.charAt( i ) );
			}
		}
		return sb.toString();
	}

	/**
	 * 过滤敏感词
	 * @param originalString
	 * @return
	 */
	public static String filterText( String originalString ) {
		if( null == originalString || originalString.isEmpty()){
			return null;
		}
		FilteredResult res = filter( filterPunctation( originalString ), '*' );
		return res.getFilteredContent();
	}
	
	public static FilteredResult filterText( String originalString, char replacement ) {
		return filter( filterPunctation( originalString ), replacement );
	}

	public static FilteredResult filterHtml( String originalString, char replacement ) {
		return filter( filterPunctationAndHtml( originalString ), replacement );
	}

	private static class PunctuationOrHtmlFilteredResult {
		private String originalString;

		private StringBuffer filteredString;

		private ArrayList<Integer> charOffsets;

		public String getOriginalString() {
			return this.originalString;
		}

		public void setOriginalString( String originalString ) {
			this.originalString = originalString;
		}

		public StringBuffer getFilteredString() {
			return this.filteredString;
		}

		public void setFilteredString( StringBuffer filteredString ) {
			this.filteredString = filteredString;
		}

		public ArrayList<Integer> getCharOffsets() {
			return this.charOffsets;
		}

		public void setCharOffsets( ArrayList<Integer> charOffsets ) {
			this.charOffsets = charOffsets;
		}
	}

	
	
	public static void main( String args[] ) {
		// 测试敏感词过滤
		for( int i = 0; i < 20; i++ ) {
			String word = "法轮功狗大爷的法轮麻痹的我日取钱去去强奸去去去啊 阿达轮奸大神大神口交阿达阿斯蒂芬啊按错阿达啊立刻发狂的看见啊哈啊法轮功狗大爷的麻痹的我日取钱去去强奸去去去啊 阿达轮奸大神大神口交阿达阿斯蒂芬啊按错阿达啊立刻发狂的看见啊哈啊法轮功狗大爷的麻痹的我日取钱去去强奸去去去啊 阿达轮奸大神大神口交阿达阿斯蒂芬啊按错阿达啊立刻发狂的看见啊哈啊法轮功狗大爷的麻痹的我日取钱去去强奸去去去啊 阿达轮奸大神大神口交阿达阿斯蒂芬啊按错阿达啊立刻发狂的看见啊哈啊 ";
			long beginTime = System.currentTimeMillis();
			FilteredResult res = WordFilterUtil.filterText( word, '*' );
			long endTime = System.currentTimeMillis();
			System.out.println( res.getLevel() );// 检测到的敏感词中最高优先级的值 0为最小
			System.out.println( res.getFilteredContent().toString() );// 过滤后的字符串
			System.out.println( "语句中包含的敏感词：" + res.getBadWords() );// 敏感词列表
			System.out.println( res.getBadWords().length() );// 敏感词列表长度
			System.out.println( res.getOriginalContent() );// 原始字符串
			System.out.println("总共消耗时间为：" + (endTime - beginTime));
			beginTime = System.currentTimeMillis();
			res = WordFilterUtil.filterText( word, '*' );
			endTime = System.currentTimeMillis();
			System.out.println("总共消耗时间为：" + (endTime - beginTime));
        }

	}

}
