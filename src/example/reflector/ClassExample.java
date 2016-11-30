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
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

@SuppressWarnings("rawtypes")
public class ClassExample {
	public static void visitClass(Class clazz) {
		Class[] interfaces = clazz.getInterfaces();
		for (Class inf : interfaces) {
			visitClass(inf);
		}
		Class superClass = clazz.getSuperclass();
		if (superClass != null) {
			visitClass(superClass);
		}
		if (clazz.isInterface()) {
			System.out.println("Interface name: " + clazz.getName());
		} else {
			System.out.println("Class name: " + clazz.getName());
			Constructor[] constructors = clazz.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
				System.out.println("\tConstructor: " + constructor);
			}
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("\tField: " + field);
		}
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println("\tMethod: " + method);
		}
	}

	public static void main(String[] args) {
		visitClass(BeanChild.class);
	}
}

/*
 * 修改历史
 * $Log$ 
 */