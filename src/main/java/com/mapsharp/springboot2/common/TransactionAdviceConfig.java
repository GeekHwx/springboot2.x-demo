package com.mapsharp.springboot2.common;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @author: hwx
 * @create: 2019/12/05 15:38
 * @desc: 基于AOP配置全局事务管理
 **/
@Aspect
@Configuration
public class TransactionAdviceConfig {

	//项目service路径
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.mapsharp.springboot2.service.*.*(..))";

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	public TransactionInterceptor txAdvice() {

		//声明事务级别1
		DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
		txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);//事务级别
		//声明事务级别2
		DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
		txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		txAttr_REQUIRED_READONLY.setReadOnly(true);

		//创建
		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

		source.addTransactionalMethod("add*", txAttr_REQUIRED);
		source.addTransactionalMethod("save*", txAttr_REQUIRED);
		source.addTransactionalMethod("insert*", txAttr_REQUIRED);
		source.addTransactionalMethod("edit*", txAttr_REQUIRED);
		source.addTransactionalMethod("delete*", txAttr_REQUIRED);
		source.addTransactionalMethod("del*", txAttr_REQUIRED);
		source.addTransactionalMethod("update*", txAttr_REQUIRED);
		source.addTransactionalMethod("exec*", txAttr_REQUIRED);
		source.addTransactionalMethod("set*", txAttr_REQUIRED);
		source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);

		return new TransactionInterceptor(transactionManager, source);
	}

	@Bean
	public Advisor txAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}

}
