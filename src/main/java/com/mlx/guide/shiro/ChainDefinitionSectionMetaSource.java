package com.mlx.guide.shiro;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mlx.guide.entity.Resource;
import com.mlx.guide.entity.Role;
import com.mlx.guide.service.ResourceService;
import com.mlx.guide.service.RoleService;
import com.mlx.guide.service.UserInfoService;
import com.mlx.guide.util.SpringContextHolder;

/**
 * 借助spring {@link FactoryBean} 对apache shiro的premission进行动态创建
 * 
 * @author quan
 * 
 */
@Component
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	private static Logger logger = LoggerFactory.getLogger(ChainDefinitionSectionMetaSource.class);

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	// shiro默认的链接定义
	private String filterChainDefinitions;
	
	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;

	public void reLoad() {
		AbstractShiroFilter shiroFilter = null;
		try {
			shiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		
		// 获取过滤管理器  
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter  
                .getFilterChainResolver();  
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();  

        // 清空初始权限配置  
        manager.getFilterChains().clear();  
        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();  

        // 重新构建生成  
        shiroFilterFactoryBean.setFilterChainDefinitions(filterChainDefinitions);  
        
		try {
			Map<String, String> chains = getObject();
	        for (Map.Entry<String, String> entry : chains.entrySet()) {  
	            String url = entry.getKey();  
	            String chainDefinition = entry.getValue().trim().replace(" ", "");  
	            manager.createChain(url, chainDefinition);  
	            System.out.println(url+" = " + chainDefinition);
	        } 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

 
		
	}
	
	/**
	 * 通过filterChainDefinitions对默认的链接过滤定义
	 * 
	 * @param filterChainDefinitions
	 *            默认的接过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws Exception {
		// TODO Auto-generated method stub
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		logger.info(filterChainDefinitions);
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}

		// section.put("/acct/scene","authc,user, perms[scene:*]");
		// test/* = role[admin],perms[test:view]

		
		

		List<Role> rolesList = roleService.list(null);
		// 循环数据库资源的url
		if (rolesList != null && rolesList.size() > 0) {
			logger.info("loaded role menu finish! result = " + rolesList.size(), this);
			for (Role role : rolesList) {
				if (role != null && !role.equals("")) {
					// 查找角色下的资 源
					List<Resource> resources = resourceService.getResourceByRoleId(role.getId());

					for (Resource resource : resources) {
						if (resource != null) {
							String[] _strRegexs = new String[] { "\\/admin|\\/admin\\/" };
							Pattern pattern = Pattern.compile(_strRegexs[0]);
							Matcher matcher = pattern.matcher(resource.getPath());
							if (matcher.find()) {
								section.put(resource.getPath(),
										"role[" + role.getName() + "],perms[" + resource.getPath() + "]");
							}
						}
					}

					/*
					 * System.out.println( cmlMenu.getActionUrl() + " = role[" +
					 * cmlMenu.getAppRoleName() + "], perms[" +
					 * cmlMenu.getPermission() + "]" );
					 */
				}
			}
			// admin/** = role[admin],perms
			/*section.put("/admin/**", "role[admin],perms[admin]");*/
			logger.info("权限拦截表：" + section.values().toString());
			logger.info("权限拦截表：" + ini.toString());
		} else {
			logger.info("loaded resource menu finish !,but it's null", this);
		}

		return section;
	}
	
	

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
