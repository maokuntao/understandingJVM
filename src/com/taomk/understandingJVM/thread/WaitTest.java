package com.taomk.understandingJVM.thread;

import java.util.Date;

/**
 * 
 * http://www.jasongj.com/java/multi_thread/
 * 
 * @author taomk 2017年9月29日 上午9:55:28
 *
 */
public class WaitTest {

	public static void main(String[] args) {

		Thread thread1 = new Thread(() -> {
			synchronized (WaitTest.class) {
				try {
					System.out.println(new Date() + " Thread1 is running");
					
					// wait for notify
					WaitTest.class.wait();
					System.out.println(new Date() + " Thread1 ended");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		thread1.start();

		Thread thread2 = new Thread(() -> {
			synchronized (WaitTest.class) {
				try {
					System.out.println(new Date() + " Thread2 is running");
					WaitTest.class.notify();
					// Don't use sleep method to avoid confusing
					for (long i = 0; i < 200000; i++) {
						for (long j = 0; j < 100000; j++) {
						}
					}
					System.out.println(new Date() + " Thread2 release lock");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			for (long i = 0; i < 200000; i++) {
				for (long j = 0; j < 100000; j++) {
				}
			}
			System.out.println(new Date() + " Thread2 ended");
		});

		// Don't use sleep method to avoid confusing
		for (long i = 0; i < 200000; i++) {
			for (long j = 0; j < 100000; j++) {
			}
		}
		thread2.start();
	}

}
