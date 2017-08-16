package com.taomk.understandingJVM.lock.count;

/**
 * 共享变量测试
 * 
 * @author taomk 2017年8月14日 上午10:31:42
 *
 */
public class SharedVariableTest {
	
	private int count = 0;

	public static void main(String[] args) {
		
		SharedVariableTest svt1 = new SharedVariableTest();
		SharedVariableTest svt2 = new SharedVariableTest();
		
		// 为不同对象创建的线程，线程之间不共享实例变量
//		T1 t1 = svt1.new T1();
//		t1.start();
//		
//		T2 t2 = svt2.new T2();
//		new Thread(t2).start();
		
		// 相同对象创建的线程，线程之间不共享实例变量
//		T1 t1 = svt1.new T1();
//		t1.start();
//		
//		T2 t2 = svt1.new T2();
//		new Thread(t2).start();
		
	}

	class T1 extends Thread{
		
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
				System.out.println(Thread.currentThread().getName() + " count = " + count);
			}
		}
	}
	
	class T2 implements Runnable{

		@Override
		public void run() {
			for(;;) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " count = " + count);
			}
		}
		
	}
}
