package com.mlx.guide.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * 
 * @author quan
 * 
 */
public class DateTimeUtil {

	private static Logger logger = LoggerFactory.getLogger( DateTimeUtil.class );

	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_SHORT = "yyyy-MM-dd";

	/**
	 * 英文简写 如：20101201
	 */
	public static String FORMAT_SHORT_1 = "yyyyMMdd";

	/**
	 * 英文全称 如：2010-12-01 23:15:06
	 */
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 英文全称 如：20101201231506
	 */
	public static String FORMAT_LONG_1 = "yyyyMMddHHmmss";
	
	/**
	 * 英文全称 如：20101201231506111
	 */
	public static String FORMAT_LONG_2 = "yyyyMMddHHmmssSSS";

	/**
	 * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

	/**
	 * 中文简写 如：2010年12月01日
	 */
	public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

	/**
	 * 中文全称 如：2010年12月01日 23时15分06秒
	 */
	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	/**
	 * 精确到毫秒的完整中文时间
	 */
	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateNow() throws ParseException {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 根据预设格式返回当前日期
	 * 
	 * @return
	 */
	public static String getNow() {
		return getDateFormateByDate( new Date() );
	}

	/**
	 * 根据用户格式返回当前日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getNow( String format ) {
		return getDateFormateByDate( new Date(), format );
	}

	/**
	 * 根据日期字符串和格式获取日期
	 * 
	 * @param date
	 * @param formate
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFormate( String date, String formate ) throws ParseException {
		if( date == null || date.isEmpty() ) {
			return null;
		}
		SimpleDateFormat smplDateFormate = new SimpleDateFormat( formate );
		return smplDateFormate.parse( date );
	}

	/**
	 * 根据默认yyyy-MM-dd HH:mm:ss格式获取日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFormate( String date ) throws ParseException {
		return getDateFormate( date, getDatePattern() );
	}

	/**
	 * 根据格式和日期获取日期字符串
	 * 
	 * @param date
	 * @param formate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateFormateByDate( Date date, String formate ) {
		if( date == null ) {
			return null;
		}
		SimpleDateFormat smplDateFormate = new SimpleDateFormat( formate, Locale.CHINA );
		String sDate = smplDateFormate.format( date );
		return sDate;
	}

	/**
	 * 根据默认yyyy-MM-dd HH:mm:ss格式和日期获取日期字符串
	 * 
	 * @param date
	 * @param formate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateFormateByDate( Date date ) {
		return getDateFormateByDate( date, getDatePattern() );
	}

	/**
	 * 根据两个日期之间返回所有日期
	 * 
	 * @param date
	 * @param formate
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getBetweenDate( String beginDate, String endDate ) {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Date begin = null;
		try {
			begin = sdf.parse( beginDate );
		}
		catch( ParseException e ) {
			// TODO Auto-generated catch block
			logger.info( e.getMessage() );
		}
		Date end = null;
		try {
			end = sdf.parse( endDate );
		}
		catch( ParseException e ) {
			// TODO Auto-generated catch block
			logger.info( e.getMessage() );
		}
		double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		double day = between / (24 * 3600);
		List<String> list = new ArrayList<String>();
		for( int i = 0; i <= day; i++ ) {
			Calendar cd = Calendar.getInstance();
			try {
				cd.setTime( sdf.parse( beginDate ) );
			}
			catch( ParseException e ) {
				// TODO Auto-generated catch block
				logger.info( e.getMessage() );
			}
			cd.add( Calendar.DATE, i );// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月
			list.add( sdf.format( cd.getTime() ) );
		}
		return list;
	}

	/**
	 * 根据当前日期获取几个月后的日期
	 * 
	 * @param date
	 * @param formate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateByMonthNum( int num ) {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		c.setTime( new Date() );
		c.add( Calendar.MONTH, num );
		Date d2 = c.getTime();
		String s = df.format( d2 );
		return s;
	}

	/**
	 * 根据日期获取最后一天
	 * 
	 * @param date
	 * @param formate
	 * @return
	 * @throws ParseException
	 */
	public static Date getLastDayOfMonth( String date ) {

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Date sDate1 = null;
		try {
			sDate1 = sdf.parse( date );
		}
		catch( ParseException e ) {
			// TODO Auto-generated catch block
			logger.info( e.getMessage() );
		}
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime( sDate1 );
		cDay1.set( Calendar.DAY_OF_MONTH, 1 );
		cDay1.add( Calendar.MONTH, 1 );
		cDay1.add( Calendar.DATE, -1 );
		return cDay1.getTime();
	}

	/**
	 * 根据传递的日期字符串判断是否有值,如果无则返回默认设置的日期
	 * 
	 * @param date
	 * @param defaultMaxDate 当date无值时设置是否返回最大日期还是最小日期
	 * @return
	 */
	public static String getDefaultDateString( String date, boolean defaultMaxDate ) {
		if( date != null && !date.isEmpty() ) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		if( defaultMaxDate ) {
			calendar.set( 2050, 12, 31 );
			return getDateFormateByDate( calendar.getTime(), FORMAT_SHORT );
		}
		else {
			calendar.set( 1900, 1, 1 );
			return getDateFormateByDate( calendar.getTime(), FORMAT_SHORT );
		}
	}

	/**
	 * 获取两个日期之间相差的天数 wuzhy
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static long getDaySub( String beginDateStr, String endDateStr ) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat( "yyyy-MM-dd" );
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse( beginDateStr );
			endDate = format.parse( endDateStr );
			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		}
		catch( ParseException e ) {
			logger.info( e.getMessage() );
		}
		return day;
	}

	/**
	 * 日期加天数得到新的日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDate( Date d, long day ) throws ParseException {

		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date( time );

	}
}
