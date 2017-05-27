package com.taomk.understandingJVM.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * 测试: {@link java.util.concurrent.Executors.newSingleThreadExecutor()}
 * 
 * 返回的结果表明每次执行的线程都是同一个。
 * </pre>
 * 
 * @author taomk 2017年4月25日 下午10:08:50
 *
 */
public class SingleThreadExecutorTest {

	public static void main(String[] args) {

		ExecutorService singleThreadExecurot = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 10; i++) {
			singleThreadExecurot.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " is running... ");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});;
		}
		
		System.out.println("Outter:" + Thread.currentThread().getName() + " is running... ");
	}

}
