package com.taomk.understandingJVM.concurrent.task;

/**
 * 资源类（单一的生产者和消费者）
 * @author taomk 2017年6月20日 下午7:52:14
 *
 */
public class Resource {

	private String resourceName;
	private int resourceCount;
	private boolean hasResource;
	
	/**
	 * 生产资源
	 * 
	 * @param name 资源名称
	 */
	public synchronized void product(String name){
		
		// 如果库存还有资源，就等待，不生产
		if(hasResource){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 
		this.resourceCount++;
		this.resourceName = name + String.valueOf(resourceCount);
		System.out.println(Thread.currentThread().getName() + " 生产了 " + this.resourceName);
		hasResource = true;
		notifyAll();
		
	}
}
