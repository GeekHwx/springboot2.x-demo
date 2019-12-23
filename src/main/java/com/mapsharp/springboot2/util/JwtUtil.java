package com.mapsharp.springboot2.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author hwx
 * @create 2019/12/17 16:10
 * @desc
 **/
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
@PropertySource(value = "classpath:jwt.properties")
public class JwtUtil {
	//签名私钥
	private String key;
	//失效时间
	private Integer ttl;

	/**
	 * 设置认证token
	 *
	 * @author: hwx
	 * @date: 2019/12/17 16:20
	 * @param: id ->			登录用户id
	 * @param: loginName ->		登录账号
	 * @param: map ->			自定义数据
	 * @return:
	 */
	public String createToken(String id, String loginName, Map<String, Object> map) {
		//1.设置失效时间
		long now = System.currentTimeMillis();
		long exp = now + ttl;
		//2.创建JwtBuilder
		JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(loginName).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, key);
		//3.根据map设置claims
		jwtBuilder.setClaims(map);
		jwtBuilder.setExpiration(new Date(exp));
		//4.创建token
		String token = jwtBuilder.compact();
		return token;
	}

	/**
	 * 解析token字符串获取claims
	 *
	 * @author: hwx
	 * @date: 2019/12/17 16:26
	 * @param: token ->
	 * @return:
	 */
	public Claims parseToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return claims;
	}
}
