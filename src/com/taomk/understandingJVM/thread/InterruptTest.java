package com.taomk.understandingJVM.thread;

/**
 * 测试 Thread.interrupt() 方法
 * 
 * @see Thread#interrupt()
 * 
 * @author taomk 2017年6月16日 上午9:49:41
 *
 */
public class InterruptTest {

	public static void main(String[] args) {
		Thread clientThread = new Thread(new ClientThread(), "clientThread");
		// 启动线程
		clientThread.start();

		// interrupt线程
		clientThread.interrupt();
	}

}

/**
 * 业务线程
 * 
 * @author taomk 2017年6月16日 上午9:56:09
 *
 */
class ClientThread implements Runnable {

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName() + " running...");

		try {
			Thread.sleep(10_1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println(Thread.currentThread().getName() + " interrupted...");
			System.err.println(Thread.currentThread().getName() + " cause : " + e.getMessage());
		}
	}

}