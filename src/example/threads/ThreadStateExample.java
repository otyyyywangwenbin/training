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
 * Created on 2013-1-10
 *******************************************************************************/


package example.threads;


/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class ThreadStateExample {
	public synchronized void sleep() throws InterruptedException{
		Thread.sleep(1000 * 60);
	}
	
	public static void main(String[] args) {
		/*执行main方法后可以 kill -3 pid 或 jstack pid 看线程栈, 会有BLOCKED和TIMED_WAITING*/
		final ThreadStateExample example = new ThreadStateExample();
		Runnable r = new Runnable() {
			public void run() {
				try {
					example.sleep();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		};
		new Thread(r, "T1").start();
		new Thread(r, "T2").start();
	}
}

/*
 * 修改历史
 * $Log$ 
 */