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

package example.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class SemaphoreExample extends Thread {
	Semaphore position;
	private int id;

	public SemaphoreExample(int i, Semaphore s) {
		this.id = i;
		this.position = s;
	}

	public void run() {
		try {
			if (position.availablePermits() > 0) {
				System.out.println("顾客[" + this.id + "]进入厕所，有空位");
			} else {
				System.out.println("顾客[" + this.id + "]进入厕所，没空位，排队");
			}
			position.acquire();
			System.out.println("顾客[" + this.id + "]获得坑位");
			Thread.sleep((int) (Math.random() * 1000));
			System.out.println("顾客[" + this.id + "]使用完毕");
			position.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Semaphore position = new Semaphore(2);
		for (int i = 0; i < 10; i++) {
			executor.submit(new SemaphoreExample(i + 1, position));
		}
		executor.shutdown();
		position.acquireUninterruptibly(2); /* 在有2个可用的数目前,阻塞线程 */
		System.out.println("使用完毕，需要清扫了");
		position.release(2);
	}
}

/*
 * 修改历史
 * $Log$ 
 */