package com.mlx.guide.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 配置文件读取工具类
 * 
 * @author quan
 *
 */
public class PropertiesLoaderUtil {

	private static Logger logger = LoggerFactory.getLogger( PropertiesLoaderUtil.class );

	private static List<String> configs = new ArrayList<String>();

	static {
		// 配置文件
		configs.add( "config.properties" );
	}
	
	private static PropertiesLoaderUtil propUtil = new PropertiesLoaderUtil();

	private Properties properties;

	private PropertiesLoaderUtil() {
		Resource resource = new ClassPathResource( "conf.properties" );
		try {
	        properties = PropertiesLoaderUtils.loadProperties(resource);
        }
        catch( IOException e ) {
	        logger.error( e.getMessage(), e );
        }
	}

	public static PropertiesLoaderUtil getInstance() {
		return propUtil;
	}

	public String getPropertyValue( String key ) {
		return getPropertyValue( key, "" );
	}

	public String getPropertyValue( String key, String defaultValue ) {
		String value = properties.getProperty( key, defaultValue );
		if( StringUtil.empty( value ) ) {
			logger.info( "key = " + key + " value = " + value );
		}
		return value;
	}
}
