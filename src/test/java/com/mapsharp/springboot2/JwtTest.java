package com.mapsharp.springboot2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author hwx
 * @create 2019/12/17 10:56
 * @desc JWT测试
 **/
public class JwtTest {

	/**
	 * 私钥
	 */
	private static final String PRIVATE_KEY = "mykey";


	/**
	 * 通过JJWt生成token
	 *
	 * @author: hwx
	 * @date: 2019/12/17 10:59
	 * @param: args ->
	 * @return:
	 */
	@Test
	public void createToken() {
		JwtBuilder jwtBuilder = Jwts.builder()
				.setId("myid")
				.setSubject("admin")
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, PRIVATE_KEY)
				.claim("age","18")//自定义数据
				.claim("sex","男")//自定义数据
				;
		String compact = jwtBuilder.compact();
		System.out.println(compact);
	}

	/**
	 * 通过JJWt解析token
	 *
	 * @author: hwx
	 * @date: 2019/12/17 13:50
	 * @return:
	 */
	@Test
	public void parseToken() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJteWlkIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1NzY1NjIzNzcsImFnZSI6IjE4Iiwic2V4Ijoi55S3In0.TpHSF-XgP41PjiVpJUS6axjIRh_PsHIWSGFX7GWsgIk";
		Claims claims = Jwts.parser().setSigningKey(PRIVATE_KEY).parseClaimsJws(token).getBody();

		String id = claims.getId();
		String subject = claims.getSubject();
		Date d = claims.getIssuedAt();
		//获取自定义数据
		String age = (String) claims.get("age");
		String sex = (String) claims.get("sex");


		System.out.println("id:" + id);
		System.out.println("签名:" + subject);
		System.out.println("创建时间:" + DateFormatUtils.format(d, "yyyy-MM-dd hh:mm:ss"));
		System.out.println("年龄:" + age);
		System.out.println("性别:" + sex);
	}
}
