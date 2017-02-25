package com.taomk.understandingJVM.keyworld;

import java.util.concurrent.CountDownLatch;

/**
 * <code>volatile</code> 关键字研究
 * @author taomk 2017年2月25日 下午2:59:17
 *
 */
public class VolatileStudy {

	/**
	 * 启动线程数量
	 */
	private static final int THREAD_COUNT = 10*100*1000;
	
	/**
	 * 线程操作目标，每个线程都会使value值加1，TODO 1，试一试添加volatile前后区别
	 */
	private static /* volatile */ int value;
	
	/**
	 * 使用CountDownLatch来完成并发控制，TODO 2，试一试不使用CountDownLatch的区别
	 */
	private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
	
	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			new Thread(){
				@Override
				public void run() {
					increment();
					countDownLatch.countDown();
				}
			}.start();
		}
		try {
			countDownLatch.await();
			System.out.println(getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * value值加1，<code>value++;</code> 本身并不是原子操作，TODO，试试加上synchronized前后区别
	 * @return
	 */
	public static /* synchronized */ int increment(){
		return value++;
	}
	
	public static int getValue(){
		return value;
	}
}
