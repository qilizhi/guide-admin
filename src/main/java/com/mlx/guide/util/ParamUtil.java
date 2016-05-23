package com.mlx.guide.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参数工具类
 * 
 * @author quan
 * 
 */
public class ParamUtil {

	private static Logger logger = LoggerFactory.getLogger( ParamUtil.class );

	private Map<String, String> map;

	private String appId = null;

	private String appMd5 = null;

	private String app3DES = null;

	private String version = null;

	private static PropertiesLoaderUtil propertiesLoaderUtil = PropertiesLoaderUtil.getInstance();

	private ParamUtil() {
		map = new HashMap<String, String>();
		appId = propertiesLoaderUtil.getPropertyValue( "appId" );
		appMd5 = propertiesLoaderUtil.getPropertyValue( "appMd5" );
		app3DES = propertiesLoaderUtil.getPropertyValue( "app3DES" );
		version = propertiesLoaderUtil.getPropertyValue( "api.version", "1.0" );
	}

	/**
	 * 获取一个新的实例
	 * 
	 * @return
	 */
	public static ParamUtil getNewInstance() {
		return new ParamUtil();
	}

	/**
	 * 添加参数和值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ParamUtil addParam( String name, String value ) {
		if( !StringUtil.empty( value ) ) {
			map.put( name, value );
		}
		return this;
	}

	/**
	 * 设置map参数集
	 * 
	 * @param map
	 */
	public ParamUtil setParamsMap( Map<String, String> map ) {
		this.map = map;
		return this;
	}

	/**
	 * 获取处理后的参数集
	 * 
	 * @return
	 */
	public List<NameValuePair> getResultParams() {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		try {
			String data = TripleDESClientParamUtil.Encrypt( map, StringUtil.stringValue( app3DES, "" ) );
			String t = String.valueOf( System.currentTimeMillis() );
			String sign = MD5Util.getMD5String( appId + version + t + StringUtil.stringValue( appMd5, "" ) );
			nameValuePairs.add( new BasicNameValuePair( "appId", appId ) );
			nameValuePairs.add( new BasicNameValuePair( "v", version ) );
			nameValuePairs.add( new BasicNameValuePair( "data", data ) );
			nameValuePairs.add( new BasicNameValuePair( "t", t ) );
			nameValuePairs.add( new BasicNameValuePair( "sign", sign ) );
			return nameValuePairs;
		}
		catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		return null;
	}
}
