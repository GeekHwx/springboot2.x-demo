package com.mapsharp.springboot2.repository;

import com.mapsharp.springboot2.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hwx
 * @create 2019/12/5 15:51
 * @desc 用户 Repository
 **/
@Repository
public interface UserReporsitory extends JpaRepository<SysUser, Integer> {

	SysUser findByLoginNum(String loginNum);
}
