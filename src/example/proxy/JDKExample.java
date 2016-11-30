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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import example.proxy.IBank.AccountInfo;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class JDKExample {
	public static void main(String[] args) {
		IBank bank = (IBank) Proxy.newProxyInstance(JDKExample.class.getClassLoader(), new Class[] { IBank.class }, new LogInvocationHandler(new Bank()));
		System.out.println(bank.getClass());
		AccountInfo account = new AccountInfo();
		System.out.println(bank.deposit(account, 100));
	}

	private static class LogInvocationHandler implements InvocationHandler {
		private IBank target = null;

		public LogInvocationHandler(IBank target) {
			this.target = target;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("LogInvocationHandler enter '" + method.getName() + "'.");
			Object ret = null;
			try {
				ret = method.invoke(target, args);
			} catch (Throwable e) {
				System.out.println("LogInvocationHandler exit '" + method.getName() + "' with THROW: " + e);
				throw e;
			}
			System.out.println("LogInvocationHandler exit '" + method.getName() + "' with RETURN: " + ret);
			return ret;
		}

	}
}

/*
 * 修改历史
 * $Log$ 
 */