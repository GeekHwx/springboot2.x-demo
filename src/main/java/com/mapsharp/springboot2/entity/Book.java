package com.mapsharp.springboot2.entity;

import lombok.Data;

/**
 * @author hwx
 * @create 2019/12/5 10:03
 * @desc mybatis-book测试类
 **/
@Data
public class Book {

	private Integer id;

	private String bookName;

	private String bookAuthor;
}
