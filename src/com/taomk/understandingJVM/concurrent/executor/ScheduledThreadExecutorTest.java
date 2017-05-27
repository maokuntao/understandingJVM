package com.taomk.understandingJVM.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 测试: {@link java.util.concurrent.Executors.newScheduledThreadPool()}
 * 
 * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
 * </pre>
 * @author taomk 2017年4月27日 下午6:34:37
 *
 */
public class ScheduledThreadExecutorTest {

	public static void main(String[] args) {

		// 延迟执行
		java.util.concurrent.ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
		scheduledExecutorService.schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " is running , delay 1 second. ");
			}
		}, 1, TimeUnit.SECONDS);
		
		// 定期执行
//		java.util.concurrent.ScheduledExecutorService scheduledAtFixedRateExecutorService = Executors.newScheduledThreadPool(5);
//		scheduledAtFixedRateExecutorService.scheduleAtFixedRate(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println(Thread.currentThread().getName() + " is running , every 3 seconds. ");
//			}
//		}, 0, 3, TimeUnit.SECONDS);
	}

}
