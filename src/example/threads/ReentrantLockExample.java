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
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class ReentrantLockExample extends Thread {
	TestReentrantLock lock;
	private int id;

	public ReentrantLockExample(int i, TestReentrantLock test) {
		this.id = i;
		this.lock = test;
	}

	public void run() {
		lock.print(id);
	}

	public static void main(String args[]) {
		ExecutorService service = Executors.newCachedThreadPool();
		TestReentrantLock lock = new TestReentrantLock();
		for (int i = 0; i < 10; i++) {
			service.submit(new ReentrantLockExample(i, lock));
		}
		service.shutdown();
	}
}

class TestReentrantLock {
	private ReentrantLock lock = new ReentrantLock();

	public void print(int str) {
		try {
			lock.lock();
			System.out.println(str + "获得");
			Thread.sleep((int) (Math.random() * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(str + "释放");
			lock.unlock();
		}
	}
}

/*
 * 修改历史
 * $Log$ 
 */