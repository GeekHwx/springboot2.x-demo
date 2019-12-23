package com.mapsharp.springboot2.service.impl;

import com.mapsharp.springboot2.entity.SysUser;
import com.mapsharp.springboot2.repository.UserReporsitory;
import com.mapsharp.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hwx
 * @create 2019/12/4 9:30
 * @desc
 **/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserReporsitory userReporsitory;

	@Override
	public List<SysUser> getAllUser() {
		System.out.println("调用数据库查询所有的用户信息");
		return null;
	}

	@Override
	public SysUser findById(Integer id) {
		SysUser user = userReporsitory.getOne(id);
		return user;
	}

	@Override
	public SysUser findByLoginNum(String loginNum) {
		return userReporsitory.findByLoginNum(loginNum);
	}

	@Override
	public String countToAop(Integer fz, Integer fm) {
		int i = fz / fm;
		return "计算结果为" + (float) fz / fm;
	}
}
