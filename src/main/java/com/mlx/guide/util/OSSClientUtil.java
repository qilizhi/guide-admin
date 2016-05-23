package com.mlx.guide.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;

/**
 * 
 * 阿里云OSSClient 工具类
 * 
 * @author QiQi-04-PC
 * 
 */
@SuppressWarnings( "restriction" )
public class OSSClientUtil {

	private static String bucketName = "mlx"; // 阿里云的子域

	private static String endpoint = "oss-cn-hangzhou.aliyuncs.com";// 阿里云OSS内网地址
	
	private static String internet_endpoint = "http://mlx.oss-cn-hangzhou.aliyuncs.com";// 阿里云OSS外网地址

	private static String accessKeyId = "nHCIv3XmAOpCoIOW";

	private static String accessKeySecret = "2cB6hpdCDuZuIWLnjMKjSlu5KotriG";

	// 初始化OSSClient
	private static OSSClient client = new OSSClient( endpoint, accessKeyId, accessKeySecret );

	private static List<String> fileTypes = new ArrayList<String>( 5 );
	
	static{
		//图片类型
		fileTypes.add( "jpg" );
		fileTypes.add( "jpeg" );
		fileTypes.add( "bmp" );
		fileTypes.add( "gif" );
		fileTypes.add( "png" );
		
		//文件类型
//		fileTypes.add( "xls" );
//		fileTypes.add( "xlsx" );
//		fileTypes.add( "pdf" );
//		fileTypes.add( "txt" );
//		fileTypes.add( "doc" );
//		fileTypes.add( "docx" );
	}
	
	/**
	 * 检查文件是否是支持的类型
	 * @param file 例如: xxxxx.jpg
	 * @return
	 */
	public static boolean checkFileType(String file) {
		String[] _sp = file.split( "\\." );
	    return fileTypes.contains( _sp[_sp.length - 1].toLowerCase() );
    }
	
	/**
	 * 组装路径
	 * @param key 上传到OSS起的名  例如: xxxxx.jpg
	 * @return
	 */
	public static String getKeyPath(String key) {
	    return bucketName + "/" + DateTimeUtil.getNow( DateTimeUtil.FORMAT_SHORT_1 ) + "/" + key;
    }
	
	/**
	 * 上传文件
	 * 
	 * @param key 上传到OSS起的名 例如: xxxxx.jpg
	 * @param fileName 本地文件名
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String uploadFile( String key, String fileName ) throws FileNotFoundException {
		key = getKeyPath( key );
		setBucketPublicReadable();
		File file = new File( fileName );
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength( file.length() );
		// 判断上传类型，多的可根据自己需求来判定
		if( fileName.endsWith( "xml" ) ) {
			objectMeta.setContentType( "text/xml" );
		}
		else if( fileName.endsWith( "jpg" ) ) {
			objectMeta.setContentType( "image/jpeg" );
		}
		else if( fileName.endsWith( "png" ) ) {
			objectMeta.setContentType( "image/png" );
		}

		InputStream input = new FileInputStream( file );
		client.putObject( bucketName, key, input, objectMeta );
		return internet_endpoint + "/" + key;
	}

	/**
	 * 根据文件流上传文件
	 * 
	 * @param key 上传到OSS起的名  例如: xxxxx.jpg
	 * @param input 文件流
	 * @throws FileNotFoundException
	 */
	public static String uploadFile( String key, InputStream input ) throws FileNotFoundException {
		key = getKeyPath( key );
		setBucketPublicReadable();
		ObjectMetadata objectMeta = new ObjectMetadata();
		// 判断上传类型，多的可根据自己需求来判定
		if( key.endsWith( "xml" ) ) {
			objectMeta.setContentType( "text/xml" );
		}
		else if( key.endsWith( "jpg" ) ) {
			objectMeta.setContentType( "image/jpeg" );
		}
		else if( key.endsWith( "png" ) ) {
			objectMeta.setContentType( "image/png" );
		}
		client.putObject( bucketName, key, input, objectMeta );
		return internet_endpoint + "/" + key;
	}

	/**
	 * 根据表单上传文件
	 * 
	 * @param multipartFile
	 * @return
	 * @throws IOException 
	 */
	public static String uploadFile( MultipartFile multipartFile ) throws IOException {
		String originalFileName = multipartFile.getOriginalFilename();
		if( StringUtil.isNotEmpty( originalFileName ) ) {
			String ext = originalFileName.substring( originalFileName.lastIndexOf( "." ) + 1, originalFileName.length() );
			ext = ext.toLowerCase();
			if( "jpeg".equals( ext ) ) {
				ext = ".jpg";
			}
			String filename = DateTimeUtil.getNow( DateTimeUtil.FORMAT_LONG_2 ) + "." + ext;
			InputStream input = multipartFile.getInputStream();
			String key = getKeyPath( filename );
			setBucketPublicReadable();
			ObjectMetadata objectMeta = new ObjectMetadata();
			// 判断上传类型，多的可根据自己需求来判定
			if( key.endsWith( "xml" ) ) {
				objectMeta.setContentType( "text/xml" );
			}
			else if( key.endsWith( "jpg" ) ) {
				objectMeta.setContentType( "image/jpeg" );
			}
			else if( key.endsWith( "png" ) ) {
				objectMeta.setContentType( "image/png" );
			}
			client.putObject( bucketName, key, input, objectMeta );
			return internet_endpoint + "/" + key;
		}
		return null;
	}
	
	/**
	 * 文件上传
	 * @param fileData 图片字节码编码字符串
	 * @return
	 * @throws IOException 
	 */
    public static String upload( String fileData ) throws IOException {
		String key =  StringUtil.generateStr( 25 ) + ".jpg";
		return upload( key, fileData );
	}
	
	/**
	 * 文件上传
	 * @param key  上传到OSS起的名  例如: xxxxx.jpg
	 * @param fileData 图片字节码编码字符串
	 * @return
	 * @throws IOException 
	 */
    public static String upload( String key , String fileData ) throws IOException {
		key = getKeyPath( key );
		setBucketPublicReadable();
		ObjectMetadata objectMeta = new ObjectMetadata();
		// 判断上传类型，多的可根据自己需求来判定
		if( key.endsWith( "xml" ) ) {
			objectMeta.setContentType( "text/xml" );
		}
		else if( key.endsWith( "jpg" ) ) {
			objectMeta.setContentType( "image/jpeg" );
		}
		else if( key.endsWith( "png" ) ) {
			objectMeta.setContentType( "image/png" );
		}
		// 使用BASE64对图片文件数据进行解码操作
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(fileData);
		client.putObject(bucketName, key, new ByteArrayInputStream(bytes),objectMeta);
		return internet_endpoint + "/" + key;
	}	

	/**
	 * 列出所有Object
	 * 
	 * @param bucketName
	 */
	public static ObjectListing listObjects() {
		// 获取指定bucket下的所有Object信息
		ObjectListing listing = client.listObjects( bucketName );
		return listing;
		// 遍历所有Object
//		for( OSSObjectSummary objectSummary : listing.getObjectSummaries() ) {
//			System.out.println( objectSummary.getKey() );
//		}
	}

	/**
	 * 
	 * @param key
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static ObjectMetadata getObject( String key, String fileName ) throws IOException {
		// 获取Object，返回结果为OSSObject对象
		return client.getObject( new GetObjectRequest( bucketName, key ), new File( fileName ) );
	}

	/**
	 * 新建bucket
	 * 
	 * @param bucketName
	 */
	public static void createBucket() {
		client.createBucket( bucketName );
	}

	/**
	 * 把Bucket设置成所有人可读
	 * 
	 * @param client OSSClient对象
	 * @param bucketName Bucket名
	 * @throws OSSException
	 * @throws ClientException
	 */
	private static void setBucketPublicReadable() throws OSSException, ClientException {
		// 创建bucket
		client.createBucket( bucketName );
		// 设置bucket的访问权限， public-read-write权限
		client.setBucketAcl( bucketName, CannedAccessControlList.PublicRead );
	}

}
