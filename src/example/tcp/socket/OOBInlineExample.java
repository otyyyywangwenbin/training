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

package example.tcp.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class OOBInlineExample {

	public static void main(String[] args) {
		final OOBInlineExample example = new OOBInlineExample();
		new Thread(new Runnable() {
			public void run() {
				try {
					example.new Server().start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				try {
					example.new Client().start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	class Server {
		public void start() throws IOException {
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("服务器已经启动，端口号：1234");
			while (true) {
				Socket socket = serverSocket.accept();
				socket.setOOBInline(true);
				InputStream in = socket.getInputStream();
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader bReader = new BufferedReader(inReader);
				System.out.println(bReader.readLine());
				System.out.println(bReader.readLine());
				socket.close();
			}
		}
	}

	/*
	 * 客户端发送数据顺序为:
	 * Chello world
	 * AB中国
	 * 
	 * 服务端接受到的数据为:
	 * ABChello world
	 * 中国
	 * */
	class Client {
		public void start() throws IOException {
			Socket socket = new Socket("127.0.0.1", 1234);
			socket.setOOBInline(true);
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter outWriter = new OutputStreamWriter(out);
			outWriter.write(67); // 向服务器发送字符"C"
			outWriter.write("hello world\r\n");
			socket.sendUrgentData(65); // 向服务器发送字符"A"
			socket.sendUrgentData(322); // 向服务器发送字符"B"
			outWriter.flush();
			byte[] bytes = "中国".getBytes();
			for (int i=0;i<bytes.length;i++){
				socket.sendUrgentData(bytes[i]);
			}
			socket.close();
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */