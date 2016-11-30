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

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class SocketPacket {
	private Socket socket;
	private boolean accept;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public SocketPacket(Socket socket, boolean accept) throws IOException {
		this.socket = socket;
		this.accept = accept;
	}

	public Object read() throws IOException {
		if (ois == null) {
			ois = new ObjectInputStream(socket.getInputStream());
		}
		Object ret = null;
		try {
			ret = (Object) ois.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (EOFException e) {
			System.err.println("May be client is stop");
		}

		if (accept) {
			System.out.println("Receive Req - " + ret);
		} else {
			System.out.println("Receive Resp - " + ret);
		}
		return ret;
	}

	public void write(Object obj) throws IOException {
		if (oos == null) {
			oos = new ObjectOutputStream(socket.getOutputStream());
		}
		oos.writeObject(obj);
		oos.flush();
		if (accept) {
			System.out.println("Send Resp - " + obj);
		} else {
			System.out.println("Send Req - " + obj);
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */