package com.mapsharp.springboot2.controller;

import com.alibaba.fastjson.JSON;
import com.mapsharp.springboot2.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hwx
 * @create 2019/12/2 16:41
 * @desc
 **/
@Controller
@RequestMapping(value = "/")
public class IndexController {


	/**
	 * 首页
	 *
	 * @author: hwx
	 * @date: 2019/12/19 10:43
	 * @return:
	 */
	@RequestMapping({"/", "/index"})
	public String index() {
		SysUser u = new SysUser();
		u.setLoginNum("6666666666");
		System.out.println(JSON.toJSON(u));
		return "/index";
	}

	/**
	 * 无权限页面
	 *
	 * @author: hwx
	 * @date: 2019/12/19 10:42
	 * @return:
	 */
	@RequestMapping("/403")
	public String unauthorizedRole(Integer code) {
		System.out.println("code:"+code);
		return "error/403";
	}

}
