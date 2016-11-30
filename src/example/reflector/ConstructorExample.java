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

import java.lang.reflect.Constructor;

import sun.reflect.ReflectionFactory;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

@SuppressWarnings("rawtypes")
public class ConstructorExample {
	public static void visitHiddenDefaultConstructor() {
		try {
			Constructor constructor = ReflectionFactory.getReflectionFactory().newConstructorForSerialization(BeanChild.class, Object.class.getDeclaredConstructor(new Class[0]));
			BeanChild child = (BeanChild) constructor.newInstance(new Object[0]);
			System.out.println("visitHiddenDefaultConstructor: i=" + child.getI() + ", s=" + child.getS());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void visitPublicConstructor() {
		try {
			Constructor constructor = BeanChild.class.getConstructor(new Class[] { int.class });
			BeanChild child = (BeanChild) constructor.newInstance(new Object[] { 10 });
			System.out.println("visitPublicConstructor: i=" + child.getI() + ", s=" + child.getS());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public static void visitPrivateConstructor() {
		try {
			Constructor constructor = BeanChild.class.getDeclaredConstructor(new Class[] { String.class, int.class }); /**/
			constructor.setAccessible(true); /**/
			BeanChild child = (BeanChild) constructor.newInstance(new Object[] { "private", 10 });
			System.out.println("visitPrivateConstructor: i=" + child.getI() + ", s=" + child.getS());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		visitHiddenDefaultConstructor();
		visitPublicConstructor();
		visitPrivateConstructor();
	}
}

/*
 * 修改历史
 * $Log$ 
 */