package com.mlx.guide.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

/**
 * 反射拓展工具类
 * 
 * @author quan
 *
 */
public class ReflectionExtUtil {

	private static final String shortDateFormate = "yyyy-MM-dd";

	private static final String longDateFormate = "yyyy-MM-dd HH:mm:ss";

	private static final String mmDateFormate = "yyyy-MM-dd HH:mm";

	/**
	 * 判断类型并转换成对应类型数据
	 * 
	 * @param value
	 * @param type
	 * @return
	 * @throws ParseException
	 */
	private static Object converteParse( String value, Type type ) throws ParseException {
		if( type == Integer.class || type == int.class )
			return Integer.parseInt( value );
		if( type == BigDecimal.class )
			return new BigDecimal( value );
		if( type == Long.class || type == long.class )
			return Long.parseLong( value );
		if( type == Double.class || type == double.class )
			return Double.parseDouble( value );
		if( type == Float.class || type == float.class )
			return Float.parseFloat( value );
		if( type == String.class )
			return value;
		if( type == Date.class ) {
			return getParseDate( value );
		}
		if( type == short.class || type == Short.class ) {
			return Short.parseShort( value );
		}
		return value;
	}

	private static Date getParseDate( String value ) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat( longDateFormate );
		try {
			return sdf.parse( value );
		}
		catch( ParseException e ) {
		}

		SimpleDateFormat mmf = new SimpleDateFormat( mmDateFormate );
		try {
			return mmf.parse( value );
		}
		catch( ParseException e ) {
		}

		SimpleDateFormat sf = new SimpleDateFormat( shortDateFormate );
		try {
			return sf.parse( value );
		}
		catch( ParseException e ) {
		}

		throw new ParseException( "ReflectionFromParamUtil类中反射实体类中日期转换出错", 0 );

	}

	/**
	 * 根据Map数据转换成指定对象 (使用条件,map.get("name")中的name必须是对象属性名称)
	 * 
	 * @param request
	 * @param c
	 * @return
	 */
	public static <T> T getObject( Map<String, String> map, Class<T> c ) {

		T t = null;
		try {
			t = (T)c.newInstance();
			getDeclaredField( map, t );
		}
		catch( InstantiationException e ) {
			e.printStackTrace();
		}
		catch( IllegalAccessException e ) {
			e.printStackTrace();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 根据界面Request提交的数据转换成指定对象
	 * (使用条件,界面Request.getParameter("name")中的name必须是对象属性名称)
	 * 
	 * @param request
	 * @param c
	 * @return
	 */
	public static <T> T getObject( HttpServletRequest request, Class<T> c ) {

		T t = null;
		try {
			t = (T)c.newInstance();
			getDeclaredField( request, t );
		}
		catch( InstantiationException e ) {
			e.printStackTrace();
		}
		catch( IllegalAccessException e ) {
			e.printStackTrace();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 循环获取类的基类
	 * 
	 * @param request
	 * @param t
	 */
	private static <T> void getDeclaredField( Map<String, String> map, T t ) {
		for( Class<?> superClass = t.getClass(); superClass != Object.class; superClass = superClass.getSuperclass() ) {
			try {
				Field[] aryField = superClass.getDeclaredFields();
				for( Field field : aryField ) {
					String fileName = field.getName();
					String value = map.get( fileName );
					if( value == null || value.trim().isEmpty() )
						continue;
					field.setAccessible( true );
					Object objValue = converteParse( value, field.getType() );
					field.set( t, objValue );
				}
			}
			catch( IllegalAccessException e ) {
				e.printStackTrace();
			}
			catch( Exception e ) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 循环获取类的基类
	 * 
	 * @param request
	 * @param t
	 */
	private static <T> void getDeclaredField( HttpServletRequest request, T t ) {
		for( Class<?> superClass = t.getClass(); superClass != Object.class; superClass = superClass.getSuperclass() ) {
			try {
				Field[] aryField = superClass.getDeclaredFields();
				for( Field field : aryField ) {
					String fileName = field.getName();
					String value = request.getParameter( fileName );
					if( value == null || value.trim().isEmpty() )
						continue;
					field.setAccessible( true );
					Object objValue = converteParse( value, field.getType() );
					field.set( t, objValue );
				}
			}
			catch( IllegalAccessException e ) {
				e.printStackTrace();
			}
			catch( Exception e ) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据界面Request提交的数据转换成指定对象
	 * (使用条件,界面Request.getParameter("name")中的name必须是对象属性名称)
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
    public static PageCondition getPageCondition( HttpServletRequest request ) {
		PageCondition pageCondition = new PageCondition();
		pageCondition.setPageNo( StringUtil.parseInt( request.getParameter( "pageNo" ), 1 ) );
		pageCondition.setPageSize( StringUtil.parseInt( request.getParameter( "pageSize" ), 10 ) );

		Map<String, String[]> map = request.getParameterMap();
		Map<String, Object> conditionMap = new LinkedHashMap<String, Object>();
		Iterator<Entry<String, String[]>> entries = map.entrySet().iterator();
		Entry<String, String[]> entry;
		String name = "";
		while( entries.hasNext() ) {
			entry = entries.next();
			name = entry.getKey();
			Object valueObj = entry.getValue();
			if( null == valueObj ) {
				continue;
			}
			else if( valueObj instanceof String[] ) {
				String[] values = (String[])valueObj;
				String value = "";
				for( int i = 0; i < values.length; i++ ) {
					value = values[i] + ",";
				}
				valueObj = value.substring( 0, value.length() - 1 );
			}
			conditionMap.put( name, valueObj );
		}
		pageCondition.setCondition( conditionMap );
		return pageCondition;
	}
	
}
