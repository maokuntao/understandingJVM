package com.taomk.understandingJVM.concurrent.task.consumeAndProducer.multi;

/**
 * 客户端-测试类
 * 
 * @author taomk 2017年6月20日 下午10:14:07
 *
 */
public class Client {

	public static void main(String[] args) {

		Resource resource = new Resource();

		Producer p = new Producer(resource);
		Consumer c = new Consumer(resource);

		// 三个生产者同时生产
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();

		// 四个消费同时消费
		new Thread(c).start();
		new Thread(c).start();
		new Thread(c).start();
		new Thread(c).start();
	}

}
