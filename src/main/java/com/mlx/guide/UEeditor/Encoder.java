package com.mlx.guide.UEeditor;
/**
 * 
 * @author QiQi-04-PC
	@date 2016年4月18日- @Time 上午11:20:46
 *
 */

public class Encoder {

	public static String toUnicode ( String input ) {
		
		StringBuilder builder = new StringBuilder();
		char[] chars = input.toCharArray();
		
		for ( char ch : chars ) {
			
			if ( ch < 256 ) {
				builder.append( ch );
			} else {
				builder.append( "\\u" +  Integer.toHexString( ch& 0xffff ) );
			}
			
		}
		
		return builder.toString();
		
	}
	
}