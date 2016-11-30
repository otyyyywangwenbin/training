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

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class TestServer {
	public static void main(String[] args) {
		try {
			ServiceTcpExporter exporter = new ServiceTcpExporter("localhost", 13456);
			exporter.bind(new TestServiceImpl());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */