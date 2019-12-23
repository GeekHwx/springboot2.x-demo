package com.mapsharp.springboot2.controller;

import com.mapsharp.springboot2.util.FileUploadUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author hwx
 * @create 2019/12/3 9:29
 * @desc 文件上传控制器
 **/
@Controller
@RequestMapping(value = "/file")
public class FileUploadController {

	//上传目录地址
	@Value("${fileUpload.uploadDir}")
	public String uploadDir;

	/**
	 * 文件上传页面跳转
	 *
	 * @author: hwx
	 * @date: 2019/12/3 9:29
	 * @return:
	 */
	@GetMapping(value = "/toFile")
	public ModelAndView toFile() {
		return new ModelAndView("fileUpload/fileUpload");
	}

	/**
	 * 上传单个文件
	 *
	 * @author: hwx
	 * @date: 2019/12/3 9:34
	 * @param: file ->	表单文件
	 * @return: 文件的数据库存放地址
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadASingleFile")
	public String uploadASingleFileTest(MultipartFile file, HttpServletRequest request) {
		try {
			DateFormatUtils.format(new Date(),"yyyy-MM-dd");
			//调用上传方法
			String dbFilePtah = FileUploadUtil.executeUpload(uploadDir, file, request);
			return dbFilePtah;
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
	}

	/**
	 * 上传多个文件
	 *
	 * @author: hwx
	 * @date: 2019/12/3 11:22
	 * @param: file -> 多个表单文件
	 * @param: request ->
	 * @return:
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadMultipleFiles")
	public String uploadMultipleFilesTest(MultipartFile[] file, HttpServletRequest request) {
		try {
			if (file != null && file.length > 0) {
				//循环调用调用上传方法
				for (int i = 0; i < file.length; i++) {
					FileUploadUtil.executeUpload(uploadDir, file[i], request);
				}
			}
			return "上传多个文件成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "上传多个文件失败";
		}
	}


	/**
	 * @author: hwx
	 * @date: 2019/12/3 11:30
	 * @param: username ->
	 * @param: file -> 表单文件
	 * @param: request ->
	 * @return: 文件的数据库存放地址
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadFileByAjax")
	public String uploadFileByAjaxTest(String username, MultipartFile file, HttpServletRequest request) {
		System.out.println("打印：" + username);
		try {
			//调用上传方法
			String dbFilePtah = FileUploadUtil.executeUpload(uploadDir, file, request);
			return dbFilePtah;
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
	}
}
