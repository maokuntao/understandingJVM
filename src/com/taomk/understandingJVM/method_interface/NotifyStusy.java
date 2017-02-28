package com.taomk.understandingJVM.method_interface;

import java.util.Date;

/**
 * <code>notify()</a>、<code>wait()</code>研究
 * 
 * @author taomk 2017年2月26日 下午10:03:22
 *
 */
public class NotifyStusy {

	
	public static void main(String[] args) {
		
		final Object lock = new Object();
		
		new Thread(){
			@Override
			public void run(){
				try {
					synchronized (lock) {
						System.out.println(Thread.currentThread() + " get the lock @ " + new Date());
						Thread.sleep(8 * 1000);
						System.out.println(Thread.currentThread() + " end @ " + new Date());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		try {
			
			System.out.println(Thread.currentThread() + " start run @ " + new Date());
			Thread.sleep(3 * 1000);
			System.out.println(Thread.currentThread() + " end run @ " + new Date());
			
			synchronized(lock){
				System.out.println(Thread.currentThread() + ", get the lock @ " + new Date());  
                lock.notify();  
                System.out.println("lock.notify();");  
                Thread.sleep(5 * 1000);  
                System.out.println(Thread.currentThread() + " end @ " + new Date()); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
