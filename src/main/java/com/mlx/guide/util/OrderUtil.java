package com.mlx.guide.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 订单工具类
 * @author QiQi-04-PC
 *@category 订单util
 *
 */
public class OrderUtil {

	private static Logger logger =LoggerFactory.getLogger(OrderUtil.class);
	private static final String ALGORITHM = "MD5";
    private static final String KEY="HsB1ZqZUr6UDG553e37e8S06JN3vj1ng";//私钥，加密验签干扰项

	public static byte[] encode(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance(ALGORITHM);
		return md.digest(data);
	}
	/**
	 * 获到加密串
	 * @param ps 加密串 用参数的值按照一定顺序排列。
	 * @return
	 */
	public static String getSign (String ps){
		
		byte[] data=null;
		try {
			data = encode(ps.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			
		}
		String sign = new String(Base64.encodeBase64String(HexStr.bytesToHexString(data).getBytes()));
		
		return sign;
	}

	/**
	 *  post 方法
	 * @param url
	 * @param params  TreeMap<String, Object> null 参数将忽略
	 * @return
	 */
	public static String post(String url,TreeMap<String, Object> params){
		StringBuilder signStr = new StringBuilder();
		
		for (String key : params.keySet()) {
			Object param=params.get(key);
			if(param!=null){
			signStr.append(params.get(key));
			
			}else{
				logger.info("拼接待签名串： 参数:"+key+"为null，忽略！不加入签 名");
			}
			
		}
		signStr.append(KEY);
		logger.info("待签名串："+signStr.toString());
		String sign =getSign(signStr.toString());
		logger.info("签名串："+sign);
		params.put("sign", sign);
		String result=HttpClientUtil.post(url, params);
		return result;
	}
	
	/**
	 * get 方法
	 * @param url
	 * @param params @TreeMap<string,object> 类  ，null 参数将忽略
	 * @return
	 */
	public static String get(String url,TreeMap<String, Object> params){
		StringBuilder signStr = new StringBuilder();
		
		for (String key : params.keySet()) {
			Object param=params.get(key);
			if(param!=null){
			signStr.append(params.get(key));
			
			}else{
				logger.info("拼接待签名串： 参数:"+key+"为null，忽略！不加入签 名");
			}
			
		}
		signStr.append(KEY);
		logger.info("待签名串："+signStr.toString());
		String sign =getSign(signStr.toString());
		logger.info("签名串："+signStr.toString());
		params.put("sign", sign);
		String getUrl=HttpClientUtil.getParamUrl(url, params);
		logger.info("转化后的请求url:"+getUrl);
		String result=HttpClientUtil.get(getUrl);
		return result;
	}
	

}
