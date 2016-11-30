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

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class ArrayExample {
	public static void visitArray(Object array) {
		System.out.println("Is array: " + array.getClass().isArray());
		System.out.println("Array component type: " + array.getClass().getComponentType());
		System.out.println("Array length: " + Array.getLength(array));
		Array.set(array, 3, 4);
		System.out.println("Array values: " + Arrays.toString((int[]) array));

	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 3 };
		visitArray(array);
	}
}

/*
 * 修改历史
 * $Log$ 
 */