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

package example.reflector;

import java.lang.reflect.Method;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class MethodExample {
	public static void visitPublicMethod(Object beanChild) {
		try {
			Method method = beanChild.getClass().getMethod("setI", new Class[] { int.class });
			method.invoke(beanChild, 100);
			System.out.println("visitPublicMethod: i=" + ((BeanChild) beanChild).getI());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void visitPrivateMethod(Object beanChild) {
		try {
			Method method = beanChild.getClass().getDeclaredMethod("addI", new Class[] { int.class });
			method.setAccessible(true);
			method.invoke(beanChild, 101);
			System.out.println("visitPrivateMethod: i=" + ((BeanChild) beanChild).getI());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void visitParentMethod(Object beanChild) {
		try {
			Method method = beanChild.getClass().getMethod("setS", new Class[] { String.class });
			method.invoke(beanChild, "method");
			System.out.println("visitParentMethod: s=" + ((BeanChild) beanChild).getS());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BeanChild child = new BeanChild(0);
		visitPublicMethod(child);
		visitPrivateMethod(child);
		visitParentMethod(child);
	}
}

/*
 * 修改历史
 * $Log$ 
 */