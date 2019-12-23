package com.mapsharp.springboot2.service;

import com.mapsharp.springboot2.entity.SysUser;

import java.util.List;

/**
 * @author hwx
 * @create 2019/12/4 9:29
 * @desc
 **/
public interface UserService {

	List<SysUser> getAllUser();

	SysUser findById(Integer id);

	SysUser findByLoginNum(String loginNum);

	String countToAop(Integer fz, Integer fm);
}
