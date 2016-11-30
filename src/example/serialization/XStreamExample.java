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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class XStreamExample {
	public static void main(String[] args) {
		XStream xstream = new XStream(new DomDriver());
		Data original = Data.newInstance();
		String xml = xstream.toXML(original);
		Data deserialize = (Data) xstream.fromXML(xml);
		System.out.println(xml);
		System.out.println("----------------");
		System.out.println(xstream.toXML(deserialize));
	}
}

/*
 * 修改历史
 * $Log$ 
 */