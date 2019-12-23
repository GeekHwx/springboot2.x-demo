package com.mapsharp.springboot2.controller;

import com.mapsharp.springboot2.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author hwx
 * @create 2019/12/19 10:33
 * @desc 登录控制器
 **/
@Controller
@RequestMapping(value = "/login")
public class LoginController {


	/**
	 * 跳转登录页面
	 *
	 * @author: hwx
	 * @date: 2019/12/19 10:50
	 * @return:
	 */
	@RequestMapping(value = "/tologin")
	public ModelAndView toLoginView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		return mav;
	}

	/**
	 * 校验登录
	 *
	 * @author: hwx
	 * @date: 2019/12/19 10:51
	 * @param: user ->
	 * @param: model ->
	 * @return:
	 */
	@RequestMapping(value = "/checkLogin")
	public String checkLogin(SysUser user, Model model) {
		Subject subject = SecurityUtils.getSubject();
		String username = user.getLoginNum();
		String pwd = user.getPassword();
		UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
		try {
			subject.login(token);
			return "redirect:/index.do";
		} catch (UnknownAccountException e) {
			model.addAttribute("msg", "账号不存在，请重新输入！");
			return "login/login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg", "密码错误，请重新输入！");
			return "login/login";
		}
	}

	/**
	 * 注销登录
	 *
	 * @author: hwx
	 * @date: 2019/12/19 10:55
	 * @param: session ->
	 * @return:
	 */
	/*@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login/login";
	}*/
}
