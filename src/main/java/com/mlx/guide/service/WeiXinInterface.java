package com.mlx.guide.service;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mlx.guide.controller.WxMenuAdminController;
import com.mlx.guide.model.WxMenuModel;
import com.mlx.guide.util.HttpClientUtil;




@Component 
public class WeiXinInterface {
    
	
	HashMap<String, String[]> map= new HashMap<String, String[]>();
	private static Logger logger = LoggerFactory.getLogger( WeiXinInterface.class );
	/**
	 * 查询微信菜单
	 * @param token
	 * @return JSON
	 */
	 public String selectMenu(String appId, String appsecret){
		 String token=this.getToken(appId, appsecret);
		 String url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+token; 
		if(StringUtils.isNotBlank(token)){
			 return HttpClientUtil.get(url);
		}
		return "appId或appsecret有误";
		
		
	}
	 
	 /**
	  * 创建菜单
	  * @param wxMenuModel
	  * @return
	  */
	 public String createMenu(String appId, String appsecret,WxMenuModel wxMenuModel){
		 //清除空余的字符串
		 if(wxMenuModel!=null&&wxMenuModel.getButton().size()>0){
			 for(int i=0;i<wxMenuModel.getButton().size();i++){
				 for(int y=0;y<wxMenuModel.getButton().get(i).getSub_button().size();y++){
					 if(StringUtils.isBlank(wxMenuModel.getButton().get(i).getSub_button().get(y).getName())||StringUtils.isBlank(wxMenuModel.getButton().get(i).getSub_button().get(y).getUrl())){
						System.out.println("abc");
						 wxMenuModel.getButton().get(i).getSub_button().remove(y);
						 y--;
					 }
				 }
			 }	 
		 }
		String token=this.getToken(appId, appsecret);
		String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token;
		String jsonString=JSONObject.fromObject(wxMenuModel).toString();
	
		logger.info(jsonString);
		String msg=HttpClientUtil.post(url, jsonString);
		//条件判断
		JSONObject json=JSONObject.fromObject(msg);
		logger.info(json.toString());
		String result="";
		Integer code=(Integer) json.get("errcode");
			if(code==0){
				result="操作成功";
			}else if(code==40016||code==40027){
				result="创建格式有误";
			}else if(code==40001){
				result="token无效";
			}else if(code==40054){
				result="无效的URL地址";
			}else if(code==40017){
				result="每个一级菜单下,必须填写一个子菜单";
			}
			else{				
				result="其他错误";
			}
		return result;
		 	 
	 }

	 /**
		 * 获取token
		 * @return
		 */
   
	private  String getToken(String appId ,String appsecret){ 
		//清除超时的 token
		for(Map.Entry<String, String[]> entry:map.entrySet()){   
			if(Integer.parseInt(entry.getValue()[1])<(new Date().getTime()/1000)){
    			map.remove(entry.getKey());
    		};
		    
		}
		//拿出保存内存的token
    	 if(map.get(appId)!=null){
    		String[] strs= (String[])map.get(appId);
    		//判断是否超时
    		if((new Date().getTime()/1000)+60<Integer.parseInt(strs[1])){
    			return strs[0];
    		};
    	 }  
       String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appsecret;
       String str=HttpClientUtil.get(url);
       JSONObject json = JSONObject.fromObject(str);
       String token="";
       Integer time=0;
       try {
    	    token=(String)json.get("access_token");
       	    time=(Integer) json.get("expires_in");
		} catch (Exception e) {			
		}
    	if(StringUtils.isNotBlank(token)){
    		String t=String.valueOf((new Date().getTime()/1000)+time);
    		map.put(appId, new String[]{token,t});
    		return (String)json.get("access_token");
    	}
    	 return null;
		}
	 
	
	 
	 
	
	 
	 
	  public static void main(String[] args) {
		 // WeiXinInterface w= new WeiXinInterface();
		  //String str=w.selectMenu("wx3b17ccef60fce7cc", "ca14035685401a38dd854ff24414a01c");
		//  System.out.println("我的"+str);
		  
		/* WxMenuModel wx=new  WxMenuModel();
		 Button b =new Button();
		 b.setName("name");
		 b.setType("view");
		 b.setUrl("http://www.baidu.com");
		 List<Button> blist= new ArrayList<Button>();
		 blist.add(b);
		 blist.add(b);
		 blist.add(b);
		 wx.setButton(blist);
		System.out.println( net.sf.json.JSONObject.fromObject(wx).toString());*/
  
	  }
  
}  

