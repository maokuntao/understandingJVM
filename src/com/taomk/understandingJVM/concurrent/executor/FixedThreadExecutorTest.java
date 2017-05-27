package com.taomk.understandingJVM.concurrent.executor;

import java.util.concurrent.Executors;

/**
 * <pre>
 * 测试: {@link java.util.concurrent.Executors.newFixedThreadPool()}
 * 
 * 返回的结果表明newFixedThreadPool创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 
 * 数量设置一般和Runtime.getRuntime().availableProcessors();一致
 * </pre>
 * @author taomk 2017年4月27日 下午3:10:30
 *
 */
public class FixedThreadExecutorTest {

	public static void main(String[] args) {

		int coreCount = Runtime.getRuntime().availableProcessors();
		
		System.out.println("Thread pool count : " + coreCount);
		
		java.util.concurrent.ExecutorService fixedExecutorService = Executors.newFixedThreadPool(coreCount);
		
		for (int i = 0; i < 10; i++) {
			
			fixedExecutorService.execute(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println(Thread.currentThread().getName() + " is running... ");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
			});
		}
		
		fixedExecutorService.shutdown();
		
		System.out.println("Outter : " + Thread.currentThread().getName() + " is running... ");
	}

}
