package com.mlx.guide.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 环信util
 * 
 * @author QiQi-04-PC
 *
 */
public class EasemobClientUtil {

	private static Logger logger = LoggerFactory.getLogger(EasemobClientUtil.class);
	private static PoolingHttpClientConnectionManager poolCM = new PoolingHttpClientConnectionManager();
	private static CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolCM).build();
	private static String API_PROTOCAL = "https";
	private static String API_HOST = "a1.easemob.com";
	private static String API_ORG_NAME = "mlx";
	private static String API_APP_NAME = "mlxing2016";
	private static String APP_CLIENT_ID = "YXA6fZM-MOHaEeWnoLMsm-GKRg";
	private static String APP_CLIENT_SECRET = "YXA6aLaQtwr_TDi9rrAR_kj2NkLl19E";
	private static String APP_IMP_LIB = "httpclient";

	/**
	 * 基本url
	 * 
	 * @return
	 */
	public static String getBaseUrl() {

		return API_PROTOCAL + "://" + API_HOST + "/" + API_ORG_NAME + "/" + API_APP_NAME;
	}

	/**
	 * 请求token 的url
	 * 
	 * @return
	 */
	public static String getTokenUrl() {
		return getBaseUrl() + "/token";
	}

	/**
	 * 获取accessToken
	 * 
	 * @return
	 */
	public static String getToken() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("client_id", APP_CLIENT_ID);
		params.put("client_secret", APP_CLIENT_SECRET);
		params.put("grant_type", "client_credentials");
		String jsonNames = JSON.toJSONString(params);
		System.out.println(jsonNames);
		String url = getTokenUrl();
		logger.info("获取accessToken请求的url:" + url + "  参数：" + jsonNames);
		String token = HttpClientUtil.post(url, jsonNames);
		JSONObject resultMap = JSON.parseObject(token);
		String access_token = (String) resultMap.get("access_token");
		return access_token;

	}

	
	
	/**
	 * 
	 * @param uri 环信相对路径
	 * @return resultContent
	 */
	public static String get(String uri){
		try {
			String url=getBaseUrl()+"/"+uri;
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Authorization","Bearer "+getToken());
			logger.info("请求的URl:"+url);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			String resultContent = new BasicResponseHandler().handleResponse(response);
			return resultContent;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			poolCM.closeExpiredConnections();
		}
		return "";
	}
	
	/**
	 * 
	 * @param uri 环信相对路径
	 * @param jsonString json参数字符
	 * @return
	 */
	public static String post(String uri,String jsonString){
		try {
			String url=getBaseUrl()+"/"+uri;
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Authorization","Bearer "+getToken());
			StringEntity entity = new StringEntity(jsonString, Consts.UTF_8);
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String resultContent = new BasicResponseHandler().handleResponse(response);
			return resultContent;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			poolCM.closeExpiredConnections();
		}
		return "";

	}

	
	
	/**
	 * 
	 * @param uri 环信相对路径
	 * @param jsonString json参数字符
	 * @return
	 */
	public static String put(String uri,String jsonString){
		try {
			String url=getBaseUrl()+"/"+uri;
			HttpPut httpPost = new HttpPut(url);
			httpPost.addHeader("Authorization","Bearer "+getToken());
			StringEntity entity = new StringEntity(jsonString, Consts.UTF_8);
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String resultContent = new BasicResponseHandler().handleResponse(response);
			return resultContent;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			poolCM.closeExpiredConnections();
		}
		return "";

	}
	
	/**
	 *  删除接口
	 * @param uri
	 * @param jsonString
	 * @return
	 */
	public static String delete(String uri){
		try {
			String url=getBaseUrl()+"/"+uri;
			HttpDelete httpDelete = new HttpDelete(url);
			httpDelete.addHeader("Authorization","Bearer "+getToken());
		
			CloseableHttpResponse response = httpClient.execute(httpDelete);
			String resultContent = new BasicResponseHandler().handleResponse(response);
			return resultContent;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			poolCM.closeExpiredConnections();
		}
		return "";
	}
	
	

}
