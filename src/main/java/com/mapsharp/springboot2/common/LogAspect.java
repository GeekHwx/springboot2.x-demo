package com.mapsharp.springboot2.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author hwx
 * @create 2019/12/4 9:32
 * @desc AOP切面
 **/
@Aspect
@Component//（把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
public class LogAspect {

	@Pointcut("execution(* com.mapsharp.springboot2.service.*.*(..))")
	public void pcl() {

	}

	@Before(value = "pcl()")
	public void before(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.println("前置通知：" + name + "方法开始执行");
	}

	@AfterReturning(value = "pcl()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String name = joinPoint.getSignature().getName();
		System.out.println("返回通知：" + name + "方法，返回了" + result);
	}

	@AfterThrowing(value = "pcl()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String name = joinPoint.getSignature().getName();
		System.out.println("异常通知：" + name + "方法抛出了异常，异常为：" + e);
	}

	@After(value = "pcl()")
	public void after(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.println("后置通知：" + name + "方法结束");
	}

	@Around(value = "pcl()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("环绕通知");
		return proceedingJoinPoint.proceed();
	}
}
