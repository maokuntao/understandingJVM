package com.taomk.understandingJVM.concurrent.task.consumeAndProducer.single;

/**
 * 消费者（单一模式）
 * 
 * @author taomk 2017年6月20日 下午10:11:36
 *
 */
public class Consumer extends Thread {
	private Resource resource;

	public Consumer(Resource r) {
		this.resource = r;
	}

	@Override
	public void run() {
		// 消费者一直消费生产的物料
		for (;;) {
			resource.consume();
		}
	}
}
