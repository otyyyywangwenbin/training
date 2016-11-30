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
 * Created on 2013-1-7
 *******************************************************************************/

package example.serialization;

import java.io.ByteArrayOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class JacksonExample {
	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Data original = Data.newInstance();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			mapper.writeValue(baos, original);
			Data deserialize = mapper.readValue(baos.toByteArray(), Data.class);

			System.out.println(mapper.writeValueAsString(original));
			System.out.println("--------------");
			System.out.println(mapper.writeValueAsString(deserialize));
			System.out.println("--------------");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */