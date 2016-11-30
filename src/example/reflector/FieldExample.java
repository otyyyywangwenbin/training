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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class FieldExample {
	public static void visitPublicField(Object beanChild) {
		try {
			Field field = beanChild.getClass().getField("ps");
			field.set(beanChild, "public");
			System.out.println("visitPublicField: ps=" + ((BeanChild) beanChild).ps);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void visitPrivateField(Object beanChild) {
		try {
			Field field = beanChild.getClass().getDeclaredField("i"); /**/
			field.setAccessible(true); /**/
			field.set(beanChild, 100);
			System.out.println("visitPrivateField: i=" + ((BeanChild) beanChild).getI());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void visitConstantsField1(Object beanChild) {
		try {
			Field field = BeanChild.class.getField("CONS1");

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.setAccessible(true); /**/

			field.set(null, "CONSTANT_1"); /**/
			System.out.println("visitConstantsField1: CONS1=" + BeanChild.CONS1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void visitConstantsField2(Object beanChild) {
		try {
			Field field = BeanChild.class.getField("CONS2");

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			field.setAccessible(true); /**/
			field.set(null, new StringBuffer("CONSTANT_2")); /**/
			System.out.println("visitConstantsField2: CONS2=" + BeanChild.CONS2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void visitParentField(Object beanChild) {
		try {
			Field field = BeanParent.class.getDeclaredField("s");
			field.setAccessible(true);
			field.set(beanChild, "public");
			System.out.println("visitParentField: s=" + ((BeanChild) beanChild).getS());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void visitGenericField(Object beanChild) {
		try {
			Field field = beanChild.getClass().getDeclaredField("list");
			Type type = field.getGenericType();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				Type[] actualTypes = paramType.getActualTypeArguments();
				for (Type aType : actualTypes) {
					if (aType instanceof Class) {
						Class clz = (Class) aType;
						System.out.println(clz.getName()); // 输出java.lang.String
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		BeanChild child = new BeanChild(0);
		visitPublicField(child);
		visitPrivateField(child);
		visitConstantsField1(child);
		visitConstantsField2(child);
		visitParentField(child);
		visitGenericField(child);
	}

}

/*
 * 修改历史
 * $Log$ 
 */