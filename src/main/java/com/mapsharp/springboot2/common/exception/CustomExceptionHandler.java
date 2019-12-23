package com.mapsharp.springboot2.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hwx
 * @create 2019/12/3 14:31
 * @desc 自定义异常
 **/
@ControllerAdvice
public class CustomExceptionHandler {

	/**
	 * 文件上传数据过大异常
	 *
	 * @author: hwx
	 * @date: 2019/12/19 16:31
	 * @param: e ->
	 * @param: response ->
	 * @return:
	 */
	@ResponseBody
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String uploadException(MaxUploadSizeExceededException e, HttpServletResponse response) throws IOException {
		return "上传文件不可大于1MB";
	}

	/**
	 * Shiro无权限异常
	 *
	 * @author: hwx
	 * @date: 2019/12/19 16:21
	 * @return:
	 */
	@ResponseBody
	@ExceptionHandler(AuthorizationException.class)
	public String authorizationException() {
		return "抱歉您没有权限访问该内容!";
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		return "系统异常!";
	}

}
