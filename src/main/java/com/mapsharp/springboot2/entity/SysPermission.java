package com.mapsharp.springboot2.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author hwx
 * @create 2019/12/18 10:17
 * @desc
 **/
@Getter
@Setter
@Entity
@Table(name = "t_sys_permission")
public class SysPermission {
	@Id
	@Column(name = "id")
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	private Integer permissionId;

	@Column(columnDefinition = "varchar(30) COMMENT '权限名称'", name = "permission_name")
	private String permissionName;

	@Column(columnDefinition = "varchar(30) COMMENT '资源类型,（menu,button）'", name = "resource_type")
	private String resourceType;

	@Column(columnDefinition = "varchar(255) COMMENT '资源路径'", name = "resource_url")
	private String resourceURL;

	/**
	 * 权限字符串
	 * menu例子：role:*
	 * button例子：role:create,role:update,role:delete,role:view
	 */
	@Column(columnDefinition = "varchar(255) COMMENT '权限字符串'", name = "permission")
	private String permission;

	private Long parentId; //父编号
	private String parentIds; //父编号列表
	private Boolean available = Boolean.TRUE;

	/**
	 * 权限 - 角色关系(多对多关系)
	 */
	@ManyToMany
	@JoinTable(name = "t_sys_role_permission",
			joinColumns = {@JoinColumn(name = "permissionId")},
			inverseJoinColumns = {@JoinColumn(name = "roleId")})
	private Set<SysRole> roles;

}
