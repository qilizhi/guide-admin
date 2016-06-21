package com.mlx.guide.shiro;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
/**
 * 
 * @author QiQi-04-PC
 *
 */

public class URLPermissionsFilter extends PermissionsAuthorizationFilter {
	
	
	 /** 
     *@param mappedValue 指的是在声明url时指定的权限字符串，如/User/create.do=perms[User:create].我们要动态产生这个权限字符串，所以这个配置对我们没用 
     */  
	@Override
    public boolean isAccessAllowed(ServletRequest request,  
            ServletResponse response, Object mappedValue) throws IOException { 
    	
    	 Subject subject = getSubject(request, response);
         String[] perms = buildPermissions(request);

           subject.getPrincipals().getPrimaryPrincipal();
      
/*         boolean isPermitted = true;
         if (perms != null && perms.length > 0) {
             if (perms.length == 1) {
                 if (!subject.isPermitted(perms[0])) {
                     isPermitted = false;
                 }
             } else {
                 if (!subject.isPermittedAll(perms)) {
                     isPermitted = false;
                 }
            // }
         }

         return isPermitted;*/
     
  return super.isAccessAllowed(request, response, buildPermissions(request));  
    }  
    /** 
     * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm 
     * @param request 
     * @return 
     */  
    protected String[] buildPermissions(ServletRequest request) {  
        String[] perms = new String[1];  
        HttpServletRequest req = (HttpServletRequest) request;  
        String path = req.getServletPath();  
        perms[0] = path;//path直接作为权限字符串  
       /* String regex = "/(.*?)/(.*?)\\.(.*)"; 
        if(path.matches(regex)){ 
            Pattern pattern = Pattern.compile(regex); 
            Matcher matcher = pattern.matcher(path); 
            String controller =  matcher.group(1); 
            String action = matcher.group(2); 
             
        } */
        return perms;  
    }  

}
