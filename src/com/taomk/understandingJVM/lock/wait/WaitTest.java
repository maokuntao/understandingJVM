package com.taomk.understandingJVM.lock.wait;

/**
 * https://zhuanlan.zhihu.com/p/25474331
 * 
 * @author taomk 2017年8月11日 下午4:17:52
 *
 */
public class WaitTest {

	public static String a = "";// 作为监视器对象

	public static void main(String[] args) throws InterruptedException {
		
		TestTask task = new WaitTest().new TestTask();
		
		Thread t = new Thread(task);
		
		t.start();
		
		Thread.sleep(12*1000);
		
		for (int i = 5; i > 0; i--) {
			System.out.println("快唤醒挂起的线程************");
			Thread.sleep(1000);
		}
		
		System.out.println("收到，马上！唤醒挂起的线程************");
		
		synchronized (a) {
			a.notifyAll();
		}
	}

	class TestTask implements Runnable {

		@Override
		public void run() {
			synchronized (a) {
				try {
					
					for (int i = 10; i > 0; i--) {
						Thread.sleep(1000);
						System.out.println("我在运行 ***************");
					}
					
					a.wait();
					
					for (int i = 10; i > 0; i--) {
						System.out.println("谢谢唤醒**********又开始运行了*******");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
