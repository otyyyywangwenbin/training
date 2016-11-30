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

import java.io.IOException;

import example.integration.TestService.Message;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class TestClient {
	public static void main(String[] args) {
		try {
			ServiceTcpLookup lookup = new ServiceTcpLookup("localhost", 13456);
			TestService service = lookup.lookup(TestService.class);
			System.out.println(service.add(10, 2));

			System.out.println(service.sendAndReceive(new Message("luffy")).getMsg());
			System.out.println(service.add(10, 2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */