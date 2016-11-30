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

package example.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class JDKExample {
	public static void main(String[] args) {
		try {
			writeToFile();
			readFromFile();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void writeToFile() throws Throwable {
		File file = new File("temp.out");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Data data = Data.newInstance();
		oos.writeObject(data);
		oos.flush();
		oos.close();
	}

	private static void readFromFile() throws Throwable {
		File file = new File("temp.out");
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream oin = new ObjectInputStream(fis);
		Data data = (Data) oin.readObject();
		oin.close();
		System.out.println("S=" + data.getS());
		System.out.println("TS=" + data.getTs());
	}
}

/*
 * 修改历史
 * $Log$ 
 */