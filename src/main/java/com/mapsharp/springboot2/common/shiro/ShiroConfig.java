package com.mapsharp.springboot2.common.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author hwx
 * @create 2019/12/18 10:39
 * @desc
 **/
@Configuration
public class ShiroConfig {

	/**
	 * 流程：
	 * -配置ShiroFilterFactoryBean
	 * <p>
	 * 　　　　　　配置流程和思路
	 * 　　　　　　shiroFilterFactoryBean-》
	 * 　　　　　　　　-SecurityManager-》
	 * 　　　　　　　　　　-CustomSessionManager
	 * 　　　　　　　　　　-CustomRealm-》hashedCredentialsMatcher
	 * 　　　　　　SessionManager
	 * 　　　　　　　　-DefaultSessionManager： 默认实现，常用于javase
	 * 　　　　　　　　-ServletContainerSessionManager: web环境
	 * 　　　　　　　　-DefaultWebSessionManager：常用于自定义实现(一般用这种)
	 */


	/**
	 * shiro内置过滤器，可以实现权限相关的拦截
	 * 常用如下：
	 * anon		匿名使用，无需认证即可访问
	 * authc	需要认证(登录)才能使用
	 * roles	表示用户必需已通过认证，并拥有某种角色才可以访问
	 * loginUrl 		未登录的请求自动跳转到登录页面，不是必须的属性，不输入地址的话会自动寻找项目web项目的根目录下的”/login.jsp”页面,可自定义登录页的路径
	 * successUrl		登录成功默认跳转页面，不配置则跳转至”/”。如果登陆前点击的一个需要登录的页面，则在登录自动跳转到那个需要登录的页面。不跳转到此
	 * unauthorizedUrl	没有权限默认跳转的页面
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		//配置登录的过滤，未登录则跳转对应url,已登录则跳过
		bean.setLoginUrl("/login/tologin.do");
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		/**
		 * 因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
		 */
		map.put("/css/**", "anon");
		map.put("/fonts/**", "anon");
		map.put("/img/**", "anon");
		map.put("/js/**", "anon");
		map.put("/html/**", "anon");
		map.put("/login/tologin.do", "anon");
		map.put("/login/checkLogin.do", "anon");

		map.put("/logout", "logout");//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		map.put("/**", "authc");//过滤链定义，从上向下顺序执行，一般将/**放在最为下边
		bean.setFilterChainDefinitionMap(map);
		return bean;
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
	 * 自定义权限和授权配置
	 *
	 * @return
	 */
	@Bean
	public CustomRealm myShiroRealm() {
		CustomRealm myShiroRealm = new CustomRealm();
//		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	/**
	 * 凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
	 *
	 * @return
	 */
//	@Bean
//	public HashedCredentialsMatcher hashedCredentialsMatcher() {
//		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//		return hashedCredentialsMatcher;
//	}


	/**
	 * 开启shiro aop注解支持.
	 * 使用代理方式;所以需要开启代码支持;
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
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
