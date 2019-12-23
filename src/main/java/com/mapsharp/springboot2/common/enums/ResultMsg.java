package com.mapsharp.springboot2.common.enums;

import lombok.Getter;

/**
 * @author hwx
 * @create 2019/12/19 11:01
 * @desc	统一返回类型
 **/
@Getter
public enum ResultMsg {
	/**
	 * 失败后返回
	 */
	FAIL("0"),
	/**
	 * 成功后返回
	 */
	SUCCESS("1"),
	/**
	 * 出现错误时返回
	 */
	ERROR("-1");

	/**
	 * 返回值
	 */
	private String msg;

	ResultMsg(String msg) {
		this.msg = msg;
	}
}
