package com.mapsharp.springboot2.controller;

import com.mapsharp.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hwx
 * @create 2019/12/4 9:37
 * @desc 用户控制器
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/getAllUser")
	public void getAllUer() {
		userService.getAllUser();
	}

	@ResponseBody
	@RequestMapping(value = "/getOneUser")
	public void getOneUser(Integer id) {
		userService.findById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/count")
	public String countByAop(Integer fz, Integer fm) {
		return userService.countToAop(fz, fm);
	}

	/**
	 * 用户查询
	 * @return
	 */
	@RequestMapping("/userList")
	public String userInfo(){
		return "user_list";
	}

	/**
	 * 用户添加
	 * @return
	 */
	@RequestMapping("/userAdd")
	public String userInfoAdd(){
		return "user_add";
	}

	/**
	 * 用户删除
	 * @return
	 */
	@RequestMapping("/userDel")
	public String userDel(){
		return "user_del";
	}

	/**
	 * 用户删除
	 * @return
	 */
	@RequestMapping("/userUpdate")
	public String userUpdale(){
		return "user_update";
	}

	/**
	 * 1、shiro + 盐
	 * 2、session管理
	 * 3、缓存管理
	 * 4、整合jwt
	 */

}
