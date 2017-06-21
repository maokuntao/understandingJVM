package com.taomk.understandingJVM.concurrent.task.consumeAndProducer.multi;

/**
 * 生产者（多个模式）
 * 
 * @author taomk 2017年6月20日 下午10:07:58
 *
 */
public class Producer implements Runnable {

	private Resource resource;

	public Producer(Resource r) {
		this.resource = r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// 生产者的任务是：一直印钱
		while (true) {
			resource.product("Print Money ...");
		}
	}

}
