package com.mapsharp.springboot2.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author hwx
 * @create 2019/12/2 16:05
 * @desc
 **/
@Getter
@Setter
@Entity
@Table(name = "t_sys_user")
public class SysUser {

	@Id
	@Column(name = "id")
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	private Integer id;

	@Column(columnDefinition = "varchar(30) COMMENT '登录账号'", name = "login_num", unique = true, nullable = false)
	private String loginNum;

	@Column(columnDefinition = "varchar(30) COMMENT '用户姓名'", name = "user_name")
	private String userName;

	@Column(columnDefinition = "varchar(30) COMMENT '密码'", name = "password")
	private String password;

	@Column(columnDefinition = "varchar(30) COMMENT '加密密码的盐'", name = "salt")
	private String salt;//加密密码的盐

	@Column(columnDefinition = "varchar(20) COMMENT '移动电话'", name = "mobile_phone", unique = true)
	private String mobilePhone;

	@Column(columnDefinition = "int(10) COMMENT '用户性别（0保密，1男，2女）'", name = "gender")
	private Integer gender;

	@Column(columnDefinition = "int(10) COMMENT '用户状态（0待审核，1正常，2锁定）'", name = "state")
	private Integer state;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(columnDefinition = "datetime COMMENT '记录创建时间'", name = "create_time")
	private Date createTime;

	/**
	 * 用户 - 角色关系定义（一对多）
	 */
	@ManyToMany(fetch = FetchType.EAGER)//立即从数据库中进行加载数据;
	@JoinTable(name = "t_sys_user_role",
			joinColumns = {@JoinColumn(name = "userId")},
			inverseJoinColumns = {@JoinColumn(name = "roleId")})
	private Set<SysRole> roles;

	/**
	 * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解，可以采用多种方式定义加盐
	 *
	 * @return
	 */
	public String getCredentialsSalt() {
		return this.loginNum + this.salt;
	}
}
