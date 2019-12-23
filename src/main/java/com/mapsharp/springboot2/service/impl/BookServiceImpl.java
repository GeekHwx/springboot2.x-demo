//package com.mapsharp.springboot2.service.impl;
//
//import com.mapsharp.springboot2.entity.Book;
//import com.mapsharp.springboot2.mapper.BookMapper;
//import com.mapsharp.springboot2.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author hwx
// * @create 2019/12/5 11:10
// * @desc	mybatis下 Book类的service的实现
// **/
//@Service
//public class BookServiceImpl implements BookService {
//
//	@Autowired
//	private BookMapper bookMapper;
//
//	@Override
//	public int addBook(Book vo) {
//		int book = bookMapper.addBook(vo);
//		return book;
//	}
//
//	@Override
//	public int delBookById(Integer id) {
//		return bookMapper.delBookById(id);
//	}
//
//	@Override
//	public int updateBookById(Book vo) {
//		return bookMapper.updateBookById(vo);
//	}
//
//	@Override
//	public Book getBookById(Integer id) {
//		return bookMapper.getBookById(id);
//	}
//
//	@Override
//	public List<Book> getAllBooks() {
//		return bookMapper.getAllBooks();
//	}
//}
