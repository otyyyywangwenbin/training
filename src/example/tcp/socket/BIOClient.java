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
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class BIOClient {
	public static void main(String[] args) {
		int numTasks = 10;

		ExecutorService exec = Executors.newCachedThreadPool();

		for (int i = 0; i < numTasks; i++) {
			exec.execute(createTask(i));
		}

	}

	private static int port = 8821;

	// 定义一个简单的任务
	private static Runnable createTask(final int taskID) {
		return new Runnable() {
			private Socket socket = null;

			public void run() {
				System.out.println("Task " + taskID + ":start");
				try {
					socket = new Socket("localhost", port);
					// 发送关闭命令
					OutputStream socketOut = socket.getOutputStream();
					PrintWriter pw = new PrintWriter(socketOut, true);
					pw.println("Task " + taskID + " say hi.");

					// 接收服务器的反馈
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String msg = null;
					while ((msg = br.readLine()) != null)
						System.out.println(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};
	}
}

/*
 * 修改历史
 * $Log$ 
 */