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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 环信util
 * 
 * @author QiQi-04-PC
 *
 */
@Component
public class EasemobClientUtil {

	private static Logger logger = LoggerFactory.getLogger(EasemobClientUtil.class);
	private static PoolingHttpClientConnectionManager poolCM = new PoolingHttpClientConnectionManager();
	private static CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolCM).build();
	@Value("${API_PROTOCAL}")
	private String api_protocal;
	@Value("${API_HOST}")
	private String api_host;
	@Value("${API_ORG_NAME}")
	private String api_org_name;
	@Value("${API_APP_NAME}")
	private String api_app_name;
	@Value("${APP_CLIENT_ID}")
	private String app_client_id;
	@Value("${APP_CLIENT_SECRET}")
	private String app_client_secret;
	//private  String APP_IMP_LIB ;
	
   public 	EasemobClientUtil(){
	
   }
	public String getApi_protocal() {
		return api_protocal;
	}

	public EasemobClientUtil(String api_protocal, String api_host, String api_org_name, String api_app_name,
			String app_client_id, String app_client_secret) {
		super();
		this.api_protocal = api_protocal;
		this.api_host = api_host;
		this.api_org_name = api_org_name;
		this.api_app_name = api_app_name;
		this.app_client_id = app_client_id;
		this.app_client_secret = app_client_secret;
	}

	public void setApi_protocal(String api_protocal) {
		this.api_protocal = api_protocal;
	}

	public String getApi_host() {
		return api_host;
	}

	public void setApi_host(String api_host) {
		this.api_host = api_host;
	}

	public String getApi_org_name() {
		return api_org_name;
	}

	public void setApi_org_name(String api_org_name) {
		this.api_org_name = api_org_name;
	}

	public String getApi_app_name() {
		return api_app_name;
	}

	public void setApi_app_name(String api_app_name) {
		this.api_app_name = api_app_name;
	}

	public String getApp_client_id() {
		return app_client_id;
	}

	public void setApp_client_id(String app_client_id) {
		this.app_client_id = app_client_id;
	}

	public String getApp_client_secret() {
		return app_client_secret;
	}

	public void setApp_client_secret(String app_client_secret) {
		this.app_client_secret = app_client_secret;
	}

	/**
	 * 基本url
	 * 
	 * @return
	 */
	public  String getBaseUrl() {

		return api_protocal + "://" + api_host + "/" + api_org_name + "/" + api_app_name;
	}

	/**
	 * 请求token 的url
	 * 
	 * @return
	 */
	public  String getTokenUrl() {
		return getBaseUrl() + "/token";
	}

	/**
	 * 获取accessToken
	 * 
	 * @return
	 */
	public  String getToken() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("client_id", app_client_id);
		params.put("client_secret", app_client_secret);
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
	public  String get(String uri){
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
	public  String post(String uri,String jsonString){
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
	public String put(String uri,String jsonString){
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
	public  String delete(String uri){
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
