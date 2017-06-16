package com.taomk.understandingJVM.thread;

/**
 * 测试 Thread.join() 方法
 * 
 * @see Thread#join()
 * 
 * @author taomk 2017年6月16日 上午9:49:41
 *
 */
public class JoinTest {

	public static void main(String[] args) {
		Thread clientThread = new Thread(new ClientThread2(), "clientThread2");
		// 启动线程
		clientThread.start();

		// join线程
		try {
			clientThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " terminated...");
	}

}

/**
 * 业务线程
 * 
 * @author taomk 2017年6月16日 上午9:56:09
 *
 */
class ClientThread2 implements Runnable {

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName() + " running...");

		try {
			
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + " sleeping(s)..." + i);
				Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println(Thread.currentThread().getName() + " interrupted...");
			System.err.println(Thread.currentThread().getName() + " cause : " + e.getMessage());
		}
	}

}