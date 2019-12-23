//package com.mapsharp.springboot2.controller;
//
//import com.mapsharp.springboot2.entity.Book;
//import com.mapsharp.springboot2.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
///**
// * @author hwx
// * @create 2019/12/5 13:56
// * @desc	mybatis下 Book类的控制器
// **/
//@Controller
//@RequestMapping(value = "/book")
//public class BookController {
//
//
//	@Autowired
//	private BookService bookService;
//
//	@ResponseBody
//	@RequestMapping(value = "/addBook")
//	public int addBook(String name, String author) {
//		Book book = new Book();
//		book.setBookName(name);
//		book.setBookAuthor(author);
//		int i = bookService.addBook(book);
//		return i;
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/delBookById")
//	public int delBookById(Integer id) {
//		int i = bookService.delBookById(id);
//		return i;
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/updateBook")
//	public int updateBook(Integer id, String name, String author) {
//		Book book = new Book();
//		book.setId(id);
//		book.setBookName(name);
//		book.setBookAuthor(author);
//		int i = bookService.updateBookById(book);
//		return i;
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/getBookById")
//	public Book getBookById(Integer id) {
//		Book book = bookService.getBookById(id);
//		return book;
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/getAllBook")
//	public List<Book> getAllBook() {
//		List<Book> allBooks = bookService.getAllBooks();
//		return allBooks;
//	}
//
//}
