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

package example.integration;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class TestServiceImpl implements TestService {
	public int add(int i, int j) {
		return i + j;
	}

	public Message sendAndReceive(Message msg) {
		return new Message("echo: " + msg.getMsg());
	}

}

/*
 * 修改历史
 * $Log$ 
 */