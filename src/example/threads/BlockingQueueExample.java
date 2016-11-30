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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * TODO 此处填写 class 信息
 * 
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class BlockingQueueExample extends Thread {
	public static BlockingQueue<String> queue = new LinkedBlockingQueue<String>(3);

	private int index;

	public BlockingQueueExample(int i) {
		this.index = i;
	}

	public void run() {
		try {
			queue.put(String.valueOf(this.index));
			System.out.println("{" + this.index + "} in queue!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			service.submit(new BlockingQueueExample(i));
		}
		Thread thread = new Thread() {
			public void run() {
				try {
					while (true) {
						Thread.sleep((int) (Math.random() * 1000));
						if (BlockingQueueExample.queue.isEmpty())
							break;
						String str = BlockingQueueExample.queue.take();
						System.out.println(str + " has take!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		service.submit(thread);
		service.shutdown();
	}
}

/*
 * 修改历史
 * $Log$ 
 */