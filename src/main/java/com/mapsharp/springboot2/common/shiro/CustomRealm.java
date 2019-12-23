package com.mapsharp.springboot2.common.shiro;

import com.mapsharp.springboot2.entity.SysPermission;
import com.mapsharp.springboot2.entity.SysRole;
import com.mapsharp.springboot2.entity.SysUser;
import com.mapsharp.springboot2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hwx
 * @create 2019/12/18 10:38
 * @desc https://www.cnblogs.com/asker009/p/9471712.html
 **/
public class CustomRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	public void setName(String name){
		super.setName("customRealm");
	}

	/**
	 * 授权方法
	 * 		操作时，判断用户是否具有相应的权限
	 * 		先认证 -- 安全数据
	 * 		再授权 -- 根据安全数据获取用户具有的所有的操作权限
	 *
	 * @author: hwx
	 * @date: 2019/12/19 10:08
	 * @param: principals ->
	 * @return:
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/**
		 * 如果身份认证的时候没有传入User对象，这里只能取到userName
		 * 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
		 */
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<>();//所有角色
		Set<String> perms  = new HashSet<>();//所有权限
		for (SysRole role : user.getRoles()) {
			roles.add(role.getRoleName());
			for (SysPermission p : role.getPermissions()) {
				perms.add(p.getPermission());
				perms.add(p.getResourceURL());
			}
		}
		info.setRoles(roles);
		info.setStringPermissions(perms);
		return info;
	}

	/**
	 * 认证方法
	 *
	 * @author: hwx
	 * @date: 2019/12/19 10:08
	 * @param: token ->
	 * @return:
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String loginNum = token.getUsername();
		SysUser user = userService.findByLoginNum(loginNum);
		if (user == null) {
			return null;//若用户不存在，直接返回null，shiro底层会抛出UnknownAccountException异常
		} else {
			session.setAttribute("user", user);
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user, //安全数据 - 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
				user.getPassword(), //密码
//				ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
				getName()  //realm name
		);
		return authenticationInfo;
	}
}
