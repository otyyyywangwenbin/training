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

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class XMLEncoderExample {
	public static void main(String[] args) {
		Data original = Data.newInstance();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(baos);
		encoder.writeObject(original);
		encoder.close();

		XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(baos.toByteArray()));
		Data deserialize = (Data) decoder.readObject();
		decoder.close();
		
		System.out.println(new String(baos.toByteArray()));
		System.out.println("----------------");
		encoder = new XMLEncoder(System.out);
		encoder.writeObject(deserialize);
		encoder.close();
	}
}

/*
 * 修改历史
 * $Log$ 
 */