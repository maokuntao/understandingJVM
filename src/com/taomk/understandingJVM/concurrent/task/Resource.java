package com.taomk.understandingJVM.concurrent.task;

/**
 * 资源类（单一的生产者和消费者）
 * 
 * @author taomk 2017年6月20日 下午7:52:14
 *
 */
public class Resource {

	private String resourceName;
	private int resourceCount;
	private boolean hasResource;

	/**
	 * 生产者生产资源
	 * 
	 * @param name
	 *            资源名称
	 */
	public synchronized void product(String name) {

		// 如果库存还有资源，说明消费者还没有消费完成，就等待不生产
		if (hasResource) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 库存数量加1
		this.resourceCount++;
		// 库存的物料名称
		this.resourceName = name + String.valueOf(resourceCount);
		System.out.println(Thread.currentThread().getName() + " 生产了 " + this.resourceName);
		// 是否还有库存指示标示
		hasResource = true;
		// 唤醒消费者可以消费了
		notifyAll();

	}
}
