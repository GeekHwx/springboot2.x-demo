package com.mapsharp.springboot2.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author hwx
 * @create 2019/12/18 10:08
 * @desc
 **/
@Getter
@Setter
@Entity
@Table(name = "t_sys_role")
public class SysRole {

	@Id
	@Column(name = "id")
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	private Integer roleId;

	@Column(columnDefinition = "varchar(30) COMMENT '角色名称'", name = "role_name")
	private String roleName;

	@Column(columnDefinition = "varchar(50) COMMENT '关于角色的描述'", name = "description")
	private String description;

	private Boolean available = Boolean.TRUE;//是否可用,如果不可用将不会添加给用户

	/**
	 * 用户 - 角色关系定义
	 * 一个角色对应多个用户(一对多)
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_sys_user_role",
			joinColumns = {@JoinColumn(name = "roleId")},
			inverseJoinColumns = {@JoinColumn(name = "userId")})
	private Set<SysUser> users;

	/**
	 * 角色 - 权限关系(多对多关系)
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_sys_role_permission",
			joinColumns = {@JoinColumn(name = "roleId")},
			inverseJoinColumns = {@JoinColumn(name = "permissionId")})
	private Set<SysPermission> permissions;


}
