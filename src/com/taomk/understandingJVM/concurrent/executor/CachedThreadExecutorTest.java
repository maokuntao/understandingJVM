package com.taomk.understandingJVM.concurrent.executor;

import java.util.concurrent.Executors;

/**
 * <pre>
 * 测试: {@link java.util.concurrent.Executors.newCachedThreadPool()}
 * 
 * 返回的结果表明newFixedThreadPool创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 
 * 数量设置一般和Runtime.getRuntime().availableProcessors();一致
 * </pre>
 * @author taomk 2017年4月27日 下午3:10:30
 *
 */
public class CachedThreadExecutorTest {

	public static void main(String[] args) {

		java.util.concurrent.ExecutorService fixedExecutorService = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 10; i++) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			final int index = i;
			
			fixedExecutorService.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " " + index + " is running...");
				}
			});
		}
		
		fixedExecutorService.shutdownNow();
		System.out.println("Outter:" + Thread.currentThread().getName() + " is running... ");
	}

}
