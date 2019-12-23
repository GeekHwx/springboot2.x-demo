package com.mapsharp.springboot2.common;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author hwx
 * @create 2019/12/2 16:01
 * @desc
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//自定义的FastJ sonHttpMessageConverter
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		/**
		 * 分别配置JSON 解析过程的一些细节，例如日期格式、数据编码、是否在生成的
		 * JSON 中输出类名、是否输出value 为null 的数据、生成的JSON 格式化、空集合输出口而非
		 * null 、空字符串输出’”’而非null 等基本配直。
		 */
		config.setDateFormat("yyyy-MM-dd");
		config.setCharset(Charset.forName("UTF-8"));
		config.setSerializerFeatures(
				SerializerFeature.WriteClassName,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.PrettyFormat,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullStringAsEmpty
		);
		converter.setFastJsonConfig(config);
		converters.add(converter);
	}

	/**
	 * 设置url后缀模式匹配规则，该设置匹配所有的后缀
	 *
	 * @author: hwx
	 * @date: 2019/12/3 16:01
	 * @param: configurer ->
	 * @return:
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(true)    //设置是否是后缀模式匹配,即:/test.*
				.setUseTrailingSlashMatch(true);    //设置是否自动后缀路径模式匹配,即：/test/
	}


	/**
	 * 设置匹配后缀*.do 可替换其他后缀
	 *
	 * @author: hwx
	 * @date: 2019/12/3 16:03
	 * @param: dispatcherServlet ->
	 * @return:
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean servletServletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
		servletServletRegistrationBean.addUrlMappings("*.do");
		return servletServletRegistrationBean;
	}

}
