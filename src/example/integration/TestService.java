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

import java.io.Serializable;


/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public interface TestService {
	int add(int i, int j);

	Message sendAndReceive(Message msg);

	public static class Message implements Serializable {
		private static final long serialVersionUID = 1L;
		private String msg;

		public Message(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

	}
}

/*
 * 修改历史
 * $Log$ 
 */