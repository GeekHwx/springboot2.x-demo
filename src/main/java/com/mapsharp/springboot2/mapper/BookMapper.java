//package com.mapsharp.springboot2.mapper;
//
//import com.mapsharp.springboot2.entity.Book;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @author hwx
// * @create 2019/12/5 10:19
// * @desc
// **/
//
///**
// * @Mapper -> 通过该注解将BookMapper注入spring容器（代理对象），若启动类添加了@MapperScan注解的话，各个接口类则不用写@Mapper
// * @Component -> 官方的原话是：带此注解的类看为组件，当使用【基于注解的配置和类路径【扫描】的时候】，这些类就会被实例化。。自动扫描的时候转化为spring bean，即相当<bean id="" class="" />中的id
// * @Param("vo") Book book -> 将参数book声明为vo,传递给xml调用使用
// * @Select -> 注解查询
// * 注意：接口不可以定义重名方法，因为会生成相同的id,即不支持方法重载
// */
//@Mapper
//@Component
//public interface BookMapper {
//
//	//方式1、使用xml文件进行关联
//	int addBook(@Param("vo") Book bookVo);
//
//	int delBookById(@Param("id") Integer id);
//
//	int updateBookById(@Param("vo") Book bookVo);
//
//	//方式2、使用注解
//	@Select("select id as id,name as bookName,author as bookAuthor from t_book where id= #{id} ")
//	Book getBookById(@Param("id") Integer id);
//
//	List<Book> getAllBooks();
//}
