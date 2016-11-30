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

import java.util.List;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

@SuppressWarnings("unused")
public class BeanChild extends BeanParent {
	private int i;
	public static final String CONS1 = "CONS1";
	public static final StringBuffer CONS2 = new StringBuffer("CONS2");
	public String ps;
	private List<String> list;

	private BeanChild(String s, int i) {
		super.setS(s);
		this.i = i;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public BeanChild(int i) {
		this.i = i;
	}

	private void addI(int j) {
		i = i + j;
	}

}

/*
 * 修改历史
 * $Log$ 
 */