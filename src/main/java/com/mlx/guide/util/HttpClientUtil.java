package com.mlx.guide.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.MultipartEntityBuilder;

public class HttpClientUtil {

	private static PoolingHttpClientConnectionManager poolCM = new PoolingHttpClientConnectionManager();

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolCM).build();

	/**
	 * post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, List<NameValuePair> params) {

		try {
			HttpPost httpPost = new HttpPost(url);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
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
	 * 向服务api,post数据
	 * 
	 * @param url
	 * @param 
	 * @return
	 */
	public static String post(String url, Map<String, Object> params) {

		try {
			
			logger.info("请求的URl:"+url);
			HttpPost httpPost = new HttpPost(url);
			//httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
			List<NameValuePair> nvPairs=new ArrayList<NameValuePair>();
		   for(String key:params.keySet()){
			   if (params.get(key) != null) {
			   nvPairs.add(new BasicNameValuePair(key, params.get(key).toString()));
			   }else{
				   logger.info("参数：" + key + "值为null忽略！");   
			   }
		   }
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvPairs, Consts.UTF_8);
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// return response.getEntity();
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
	 * 向服务api,post数据
	 * 
	 * @param url
	 * @param jsonString
	 * @return
	 */
	public static String post(String url, String jsonString) {

		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity entity = new StringEntity(jsonString, Consts.UTF_8);
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// return response.getEntity();
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
	 * 获取完整的带参数的url
	 * 
	 * @param url
	 *            例如:http://www.baidu.com/
	 * @param paramMap
	 * @return 返回值 例如: http://www.baidu.com/?name=hello&pwd=XMSDX322ZSD
	 */
	public static String getParamUrl(String url, Map<String, Object> paramMap) {
		StringBuffer sb = new StringBuffer();
		if (paramMap == null || paramMap.isEmpty()) {
			return "";
		} else {
			for (String key : paramMap.keySet()) {
				Object value = paramMap.get(key);
				if (value != null) {
					if (sb.length() < 1) {
						sb.append(key).append("=").append(value);
					} else {
						sb.append("&").append(key).append("=").append(value);
					}
				} else {
					logger.info("参数：" + key + "值为null忽略！");

				}
			}
			return url += "?" + sb.toString();
		}
	}

	/**
	 * 向服务api,put数据
	 * 
	 * @param url
	 * @param jsonString
	 * @return
	 */
	public static String put(String url, String jsonString) {

		try {
			HttpPut httpPut = new HttpPut(url);
			httpPut.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity entity = new StringEntity(jsonString, Consts.UTF_8);
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPut.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPut);
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
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {

		try {
			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response = httpClient.execute(httpGet);
			/*String resultContent = new BasicResponseHandler().handleResponse(response);*/
			String result= EntityUtils.toString(response.getEntity(),"UTF-8");  
			return result;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			poolCM.closeExpiredConnections();
		}
		return "";
	}

	public static String get(String url, String jsonString) {

		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader(HTTP.CONTENT_TYPE, "application/json");
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
	 * post请求上传文件
	 * 
	 * @param url
	 * @param params
	 * @param file
	 * @return
	 */
	public static String uploadFile(String url, List<NameValuePair> params, File file) {

		try {
			HttpPost httpPost = new HttpPost(url);
			FileBody fileBody = new FileBody(file);
			MultipartEntityBuilder entity = MultipartEntityBuilder.create();
			entity.addPart("file", fileBody);
			for (NameValuePair nameValuePair : params) {
				entity.addTextBody(nameValuePair.getName(), nameValuePair.getValue());
			}
			httpPost.setEntity(entity.build());
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

}
