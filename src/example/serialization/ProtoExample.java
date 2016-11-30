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

import example.serialization.AddressBookProtos.Person;
import example.serialization.AddressBookProtos.Person.PhoneNumber;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class ProtoExample {
	public static void main(String[] args) {
		Person person = Person.newBuilder()
		.setEmail("abc@gmail.com")
		.setId(1)
		.setName("name_1")
		.addPhone(PhoneNumber.newBuilder().setNumber("13312345678").build())
		.build();

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			person.writeTo(baos);
			
			System.out.println(baos.toByteArray().length);
			System.out.println("-----------------");
			Person deserialize = Person.parseFrom(baos.toByteArray());
			System.out.println(deserialize.getEmail());
			System.out.println(deserialize.getId());
			System.out.println(deserialize.getName());
			System.out.println(deserialize.getPhone(0).getNumber());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */