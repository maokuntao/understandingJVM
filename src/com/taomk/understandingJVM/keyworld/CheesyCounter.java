package com.taomk.understandingJVM.keyworld;

/**
 * <pre>
 * volatile关键字学习：
 * {@link https://www.ibm.com/developerworks/cn/java/j-jtp06197.html}
 * </pre>
 * 
 * @author taomk 2017年5月25日 下午10:07:42
 *
 */
public class CheesyCounter {

	// 使用volatile来修饰count，保证count的可见性
	private volatile int count;

	// 因为count的可见性保证了每次取得的值都是最新的，所以测出不用加锁
	public int getCount() {
		return this.count;
	}

	// 因为 count++ 操作并非一个原子操作，所以使用 synchronized 关键字来保证自增的原子和可见性
	public synchronized void increment() {
		this.count++;
	}
}
