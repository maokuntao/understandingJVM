package com.taomk.understandingJVM.concurrent.task.consumeAndProducer.multi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者-消费者模式（多对多）
 * 
 * @author taomk 2017年6月21日 上午11:09:25
 * @description 通过lock获取两个监视器，分别监视生产者和消费者
 *
 */
public class ResourceBy2Condition {

	private String resourceName;
	private int resourceCount;
	private boolean hasResource;

	Lock lock = new ReentrantLock();

	Condition consumerCondition = lock.newCondition();
	Condition producerCondition = lock.newCondition();

	public void produce(String resourceName) {
		lock.lock();
		try {
			while (hasResource) {
				producerCondition.await();
			}

			resourceCount++;
			this.resourceName = resourceName + resourceCount;
			System.out.println(Thread.currentThread().getName() + " produce " + this.resourceName);
			hasResource = true;
			// 直接唤醒消费者线程
			consumerCondition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void consume(){
		lock.lock();
		try {
			while(!hasResource){
				consumerCondition.await();
			}
			System.out.println(Thread.currentThread().getName() + " consume " + this.resourceName);
			hasResource = false;
			// 直接唤醒生产者线程
			producerCondition.signal();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
