package com.mapsharp.springboot2.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author hwx
 * @create 2019/12/3 9:12
 * @desc 文件上传工具类
 **/
public class FileUploadUtil {

	/**
	 * 提取上传方法为公共方法
	 *
	 * @author: hwx
	 * @date: 2019/12/3 9:12
	 * @param: uploadDir ->	上传文件目录
	 * @param: file -> 上传对象
	 * @return:
	 */
	public static String executeUpload(String uploadDir, MultipartFile file, HttpServletRequest request) {
		LocalDate today = LocalDate.now();
		try {
			String newPath = uploadDir + today;
			File dir = new File(newPath);
			//如果目录不存在，则创建多层级文件夹
			if ((!dir.exists()) || (!dir.isDirectory())) {
				dir.mkdirs();
			}
			//文件后缀名
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			//上传文件名
			String filename = UUID.randomUUID() + suffix;
			//服务器端保存的文件对象
			File serverFile = new File(newPath + "/" + filename);
			//将上传的文件写入到服务器端文件内
			file.transferTo(serverFile);
			//返回文件的物理地址
			String filePtah = newPath + "/" + filename;
			//返回文件的数据库存放地址
			String dbFilePtah = today + "/" + filename;
			return dbFilePtah;
		} catch (IOException e) {
			e.printStackTrace();
			return "上传失败";
		}
	}


}
