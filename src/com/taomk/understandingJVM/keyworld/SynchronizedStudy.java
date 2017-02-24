package com.taomk.understandingJVM.keyworld;

/**
 * <code>Synchronized</code> 关键字学习
 * 
 * @author taomk 2017年2月24日 下午9:19:20
 *
 */
public class SynchronizedStudy {

	public static void main(String[] args) {

		TxtThread t = new TxtThread();
		new Thread(t , "Thtead-1").start();
		new Thread(t , "Thtead-2").start();
		new Thread(t , "Thtead-3").start();
		new Thread(t , "Thtead-4").start();
		new Thread(t , "Thtead-5").start();
	}

	
}

class TxtThread implements Runnable{

	static Object o = new Object();
//	String str = "";
	@Override
	public void run() {
		int num = 100;
		synchronized(o){
			while (num > 0) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "'num is " + num--);
			}
		}
	}
	
}
