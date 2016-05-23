package com.mlx.guide.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mlx.guide.constant.Const;
import com.mlx.guide.model.ClientJsonResp;
import com.mlx.guide.util.HttpClientUtil;
import com.mlx.guide.util.ParamUtil;

/**
 * 美丽行(PC端)接口处理工具类
 * 
 * @author quan
 * 
 */
public class MlxingAPIUtil {

	private static Logger logger = LoggerFactory.getLogger( MlxingAPIUtil.class );

	/**
	 * 接口调用(无response时)
	 * @param url
	 * @param paramUtil
	 * @return
	 */
	public static boolean getAPIData( String url, ParamUtil paramUtil ) {
		try {
			String result = HttpClientUtil.post( url, paramUtil.getResultParams() );
			TypeReference<ClientJsonResp<JSONObject>> typeRef = new TypeReference<ClientJsonResp<JSONObject>>() {};
			ClientJsonResp<JSONObject> cl = JSON.parseObject( result, typeRef );
			if (cl != null && cl.getCode() == Const.API_RESPONSE_OK) {
				return true;
			}
		}
		catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		return false;
	}
	
	/**
	 * 接口调用(对象)
	 * @param url
	 * @param paramUtil
	 * @param c
	 * @return
	 */
	public static <T> T getAPIDataT( String url, ParamUtil paramUtil, Class<T> c ) {
		try {
			String result = HttpClientUtil.post( url, paramUtil.getResultParams() );
			TypeReference<ClientJsonResp<JSONObject>> typeRef = new TypeReference<ClientJsonResp<JSONObject>>() {};
			ClientJsonResp<JSONObject> cl = JSON.parseObject( result, typeRef );
			if (cl != null && cl.getCode() == Const.API_RESPONSE_OK) {
				return JSON.toJavaObject( cl.getResponse(), c );
			}
			logger.error( "美丽行(PC端)接口调用返回状态标识为非成功" );
		}
		catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		return null;
	}

	/**
	 * 接口调用(集合)
	 * 
	 * @param url
	 * @param paramUtil
	 * @param c
	 * @return
	 */
	public static <T> List<T> getAPIDataTs( String url, ParamUtil paramUtil, Class<T> c ) {
		try {
			String result = HttpClientUtil.post( url, paramUtil.getResultParams() );
			TypeReference<ClientJsonResp<JSONArray>> typeRef = new TypeReference<ClientJsonResp<JSONArray>>() {};
			ClientJsonResp<JSONArray> cl = JSON.parseObject( result, typeRef );
			if (cl != null && cl.getCode() == Const.API_RESPONSE_OK) {
				return JSON.parseArray( cl.getResponse().toJSONString(), c );
			}
			logger.error( "美丽行(PC端)接口调用返回状态标识为非成功" );
		}
		catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		return null;
	}
	
	/**
	 * 接口调用(分页集合)
	 * 
	 * @param url
	 * @param paramUtil
	 * @param c
	 * @return
	 */
	public static <T> ClientJsonResp<List<T>> getAPIPageData( String url, ParamUtil paramUtil, Class<T> c ) {
		try {
			String result = HttpClientUtil.post( url, paramUtil.getResultParams() );
			TypeReference<ClientJsonResp<JSONArray>> typeRef = new TypeReference<ClientJsonResp<JSONArray>>() {};
			ClientJsonResp<JSONArray> cl = JSON.parseObject( result, typeRef );
			if (cl != null && cl.getCode() == Const.API_RESPONSE_OK) {
				List<T> ls = JSON.parseArray( cl.getResponse().toJSONString(), c );
				ClientJsonResp<List<T>> clResp = new ClientJsonResp<List<T>>();
				clResp.setCode( cl.getCode() );
				clResp.setMsg( cl.getMsg() );
				clResp.setMsgDetail( cl.getMsgDetail() );
				clResp.setPageCount( cl.getPageCount() );
				clResp.setPageNo( cl.getPageNo() );
				clResp.setPageSize( cl.getPageSize() );
				clResp.setResponse( ls );
				clResp.setRowCount( cl.getRowCount() );
				clResp.setToken( cl.getToken() );
				clResp.setTotalCount( cl.getTotalCount() );
				return clResp;
			}
			logger.error( "美丽行(PC端)接口调用返回状态标识为非成功" );
		}
		catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		return null;
	}

}
