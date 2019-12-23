package com.mapsharp.springboot2.common.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hwx
 * @create 2019/12/18 10:39
 * @desc
 **/
@Configuration
public class ShiroConfig {


	/**
	 * 自定义权限和授权配置
	 *
	 * @return
	 */
	@Bean
	public CustomRealm myShiroRealm() {
		CustomRealm myShiroRealm = new CustomRealm();
		return myShiroRealm;
	}

	/**
	 * 配置核心安全事务管理器
	 *
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	/**
	 * shiro内置过滤器，可以实现权限相关的拦截
	 * 常用如下：
	 * anon		匿名使用，无需认证即可访问
	 * authc	需要认证(登录)才能使用
	 * roles	表示用户必需已通过认证，并拥有某种角色才可以访问
	 * loginUrl 		未登录的请求自动跳转到登录页面，不是必须的属性，不输入地址的话会自动寻找项目web项目的根目录下的”/login.jsp”页面,可自定义登录页的路径
	 * successUrl		登录成功默认跳转页面，不配置则跳转至”/”。如果登陆前点击的一个需要登录的页面，则在登录自动跳转到那个需要登录的页面。不跳转到此
	 * unauthorizedUrl	没有权限默认跳转的页面
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		//创建shiro过滤器工厂
		ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
		//设置安全管理器
		filterFactory.setSecurityManager(securityManager);
		//通用配置
		filterFactory.setLoginUrl("/login/tologin.do");//配置登录的过滤，未登录则跳转对应url,已登录则跳过
		filterFactory.setUnauthorizedUrl("/403.do");//未授权。注意：基于过滤器拦截则生效，若基于注解的权限拦截则会抛出移除，需要手动处理异常
		//设置过滤器集合
		Map<String, String> map = new LinkedHashMap<String, String>();
		//因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
		map.put("/css/**", "anon");
		map.put("/fonts/**", "anon");
		map.put("/img/**", "anon");
		map.put("/js/**", "anon");
		map.put("/html/**", "anon");
		map.put("/login/tologin.do", "anon");
		map.put("/login/checkLogin.do", "anon");

		map.put("/logout", "logout");//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		map.put("/**", "authc");//过滤链定义，从上向下顺序执行，一般将/**放在最为下边
		filterFactory.setFilterChainDefinitionMap(map);
		return filterFactory;
	}


	/**
	 * 开启对shiro注解支持
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/**
	 * 配置thymeleaf与shiro页面标签整合使用
	 *
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

}
