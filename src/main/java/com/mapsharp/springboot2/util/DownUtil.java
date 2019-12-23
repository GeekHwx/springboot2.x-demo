package com.mapsharp.springboot2.util;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * @author: hwx
 * @create: 2019/12/3 14:06
 * @desc: 文件下载工具类
 **/
public class DownUtil {


	/**
	 * 根据传入的文件路径进行浏览器的下载
	 *
	 * @author: hwx
	 * @date: 2019/12/3 14:07
	 * @param: path ->
	 * @param: request ->
	 * @param: response ->
	 * @return:
	 */
	public static ResponseEntity<byte[]> downloadFiles(String path, HttpServletRequest request, HttpServletResponse response) {
		String patnStr = "";
		try {
			String realPath = request.getSession().getServletContext().getRealPath("");
			patnStr = realPath + "/" + path;
			if (path != null && !"".equals(path)) {
				File file = new File(patnStr);
				String filename = new String(file.getName().getBytes("UTF-8"), "ISO8859-1");
//				String filename = file.getName();// 取得文件名
				HttpHeaders headers = new HttpHeaders();
				headers.setContentDispositionFormData("attachment", filename);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 下载指定文件路径fileUrl下的文件，指定文件名称fileName
	 *
	 * @author: hwx
	 * @date: 2019/12/3 14:07
	 * @param: fileName -> 指定文件名称
	 * @param: fileUrl -> 指定文件路径
	 * @param: response ->
	 * @return:
	 */
	public static boolean downloadFileByUrl(String fileName, String fileUrl, HttpServletResponse response) {
		InputStream in = null;
		boolean tag = false;
		try {
			//1.获取要下载的文件的绝对路径
			File file = new File(fileUrl + "\\" + fileName);
			//5.获取要下载的文件输入流
			in = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(in);
			//2.获取要下载的文件名
//            String fileName = excelName;
			//3.设置content-disposition响应头控制浏览器以下载的形式打开文件
			response.setHeader("conent-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			//4.通过response对象获取OutputStream流
			OutputStream os = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			//6.创建数据缓冲区
			byte[] temp = new byte[1 * 1024 * 10];
			//7.将BufferedInputStream流写入到buffer缓冲区
			int length;
			while ((length = bis.read(temp)) != -1) {
				//8.使用OutputStream将缓冲区的数据输出到客户端浏览器
				bos.write(temp, 0, length);
			}
			bos.flush();
			bis.close();
			bos.close();
			tag = true;
		} catch (IOException e) {
			tag = false;
//			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tag;
	}


}
