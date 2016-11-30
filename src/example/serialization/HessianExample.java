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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class HessianExample {
	public static void main(String[] args) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			HessianOutput output = new HessianOutput(baos);
			Data original = Data.newInstance();
			output.writeObject(original);
			System.out.println(baos.toByteArray().length);
			HessianInput input = new HessianInput(new ByteArrayInputStream(baos.toByteArray()));
			Data deserialize = (Data) input.readObject();
			
			
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(original));
			System.out.println(mapper.writeValueAsString(deserialize));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */