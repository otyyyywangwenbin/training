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

package example.threads;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class ThreadPoolExecutorExample {
	public static void main(String[] args) {
		int poolMinSize = 0;
		int poolMaxSize = 0;
		int queueMaxSize = 0;
		int taskMaxSize = 10;
		
//		{
//			/* 只用最小线程数就可以处理所有任务 */
//			 poolMinSize = 5;
//			 poolMaxSize = 10;
//			 queueMaxSize = 10;
//		}
		
//		{
//			/* 超过最小线程数,小于最大线程数 处理所有任务 */
//			 poolMinSize = 5;
//			 poolMaxSize = 10;
//			 queueMaxSize = 1;
//		}
//		
		{
			/* 会有任务被拒绝 */
			 poolMinSize = 5;
			 poolMaxSize = 5;
			 queueMaxSize = 1;
		}
		
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(poolMinSize, poolMaxSize, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(queueMaxSize));
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.err.println(r + " is rejected.");
			}
		});

		
		for (int i = 0; i < taskMaxSize; i++) {
			executor.execute(new Task(String.valueOf(i)));
		}
		
		executor.shutdown();
	}

	private static class Task implements Runnable {
		private String taskId;

		public Task(String taskId) {
			this.taskId = taskId;
		}

		@Override
		public void run() {
			System.out.println("Task: " + taskId + " run by thread: " + Thread.currentThread().getId() + ".");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		public String toString() {
			return "Task: " + taskId;
		}

	}
}

/*
 * 修改历史
 * $Log$ 
 */