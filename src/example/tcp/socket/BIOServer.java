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
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class BIOServer {
	private int port = 8821;
	private ServerSocket serverSocket;
	private ExecutorService executorService;// 线程池
	private final int POOL_SIZE = 10;// 单个CPU线程池大小

	public BIOServer() throws IOException {
		serverSocket = new ServerSocket(port);
		// Runtime的availableProcessor()方法返回当前系统的CPU数目.
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
		System.out.println("服务器启动");
	}

	public void service() {
		while (true) {
			Socket socket = null;
			try {
				// 接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
				socket = serverSocket.accept();
				executorService.execute(new Handler(socket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new BIOServer().service();
	}

	private class Handler implements Runnable {
		private Socket socket;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		private PrintWriter getWriter(Socket socket) throws IOException {
			OutputStream socketOut = socket.getOutputStream();
			return new PrintWriter(socketOut, true);
		}

		private BufferedReader getReader(Socket socket) throws IOException {
			InputStream socketIn = socket.getInputStream();
			return new BufferedReader(new InputStreamReader(socketIn));
		}

		public String echo(String msg) {
			return "echo:" + msg;
		}

		public void run() {
			try {
				System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
				BufferedReader br = getReader(socket);
				PrintWriter pw = getWriter(socket);
				String msg = null;
				while ((msg = br.readLine()) != null) {
					System.out.println(msg);
					pw.println(echo(msg));
					if (msg.equals("bye"))
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */