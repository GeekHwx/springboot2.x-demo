package com.mapsharp.springboot2.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hwx
 * @create 2019/12/19 11:30
 * @desc	登录校验结果model
 **/
@Setter
@Getter
public class LoginResult {
	private boolean checkMark = false;
	private String result;
}
