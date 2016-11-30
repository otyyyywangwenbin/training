/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2012 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2013-1-9
 *******************************************************************************/

package example.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import example.proxy.IBank.AccountInfo;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

@SuppressWarnings("unused")
public class AspectJExample {
	public static void main(String[] args) {
		AccountInfo account = new AccountInfo();
		System.out.println(new Bank().deposit(account, 100));
	}
	
	
//	@Aspect
//	public static class LogAspect {
//		@Before("execution(*  Bank.deposit(..)) || execution (* Bank.withdraw(..))")
//		public void beforeLog(JoinPoint jp) {
//			System.out.println("LogAspect enter '" + jp.getSignature().getName() + "'.");
//			for (Object arg : jp.getArgs()) {
//				System.out.println("arg: " + arg);
//			}
//		}
//
//		@After("execution(*  Bank.deposit(..)) || execution (* Bank.withdraw(..))")
//		public void afterLog(JoinPoint jp) {
//			System.out.println("LogAspect exit '" + jp.getSignature().getName() + "'.");
//		}
//
//		@AfterReturning(pointcut = "execution(*  Bank.deposit(..)) || execution (* Bank.withdraw(..))", returning = "r")
//		public void afterLog2(JoinPoint jp, Object r) {
//			System.out.println("LogAspect exit '" + jp.getSignature().getName() + "' with RETURN: " + r);
//		}
//
//		@AfterThrowing(pointcut = "execution(*  Bank.deposit(..)) || execution (* Bank.withdraw(..))", throwing = "t")
//		public void afterLog3(JoinPoint jp, Throwable t) {
//			System.out.println("LogAspect exit '" + jp.getSignature().getName() + "' with THROW: " + t);
//		}
//	}
}

/*
 * 修改历史
 * $Log$ 
 */