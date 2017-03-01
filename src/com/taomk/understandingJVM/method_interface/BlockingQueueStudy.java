package com.taomk.understandingJVM.method_interface;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue 研究
 * @author taomk 2017年3月1日 下午10:36:39
 *
 */
public class BlockingQueueStudy {

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
		
		new Thread(new Producer(queue)).start();
		new Thread(new Consumer(queue)).start();
		
		Thread.sleep(3*1000);
	}

}


/**
 * 生产者
 * 
 * @author taomk 2017年3月1日 下午10:49:13
 *
 */
class Producer implements Runnable{

	private BlockingQueue<String> queue;
	
	Producer(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			queue.put("A");
			Thread.sleep(1000);
			queue.put("B");
			Thread.sleep(1000);
			queue.put("C");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 消费者
 * 
 * @author taomk 2017年3月1日 下午10:48:57
 *
 */
class Consumer implements Runnable{

	private BlockingQueue<String> queue;
	
	Consumer(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}