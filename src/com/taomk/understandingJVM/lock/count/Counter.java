package com.taomk.understandingJVM.lock.count;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全的计数器方法
 * 
 * @author taomk 2017年6月14日 上午11:17:52
 *
 */
public class Counter {

	private Lock lock = new ReentrantLock();
	private int count = 0;
	
	public int inc(){
		lock.lock();
		count++;
		lock.unlock();
		return count;
	}
	
	public synchronized int get(){
		return count;
	}
}