package com.taomk.understandingJVM.concurrent.task.consumeAndProducer.single;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类（单一的生产者和消费者，通过Lock来实现）
 * 
 * @author taomk 2017年6月20日 下午10:20:21
 *
 */
public class ResourceByLock {

	private String resourceName;
	private int resourceCount;
	private boolean hasResource;

	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	/**
	 * 生产物料
	 * 
	 * @param name
	 *            物料名称
	 */
	public void produce(String name) {
		lock.lock();
		try {
			if (hasResource) {
				condition.await();
			}
			// 库存数量加1
			this.resourceCount++;
			// 库存的物料名称
			this.resourceName = name + String.valueOf(resourceCount);
			System.out.println(Thread.currentThread().getName() + " 生产了 " + this.resourceName);
			// 还有库存
			hasResource = true;
			// 唤醒生产者线程
			condition.signalAll();

		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
	}

	public void consume() {
		lock.lock();
		try {
			if (!hasResource) {
				condition.wait();
			}
			System.out.println(Thread.currentThread().getName() + " 消费了 " + this.resourceName);
			// 库存已经消耗完毕
			hasResource = false;
			// 唤醒生产者线程开始生产物料
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
