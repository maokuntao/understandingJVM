package com.taomk.understandingJVM.keyworld.sync;

/**
 * <code>Synchronized</code> 关键字学习
 * 
 * @author taomk 2017年2月24日 下午9:19:20
 *
 */
public class SynchronizedStudy {

	public static void main(String[] args) {

		TxtThread t1 = new TxtThread();
		new Thread(t1 , "T1-1").start();
		new Thread(t1 , "T1-2").start();
		new Thread(t1 , "T1-3").start();
		
//		TxtThread t2 = new TxtThread();
//		new Thread(t2 , "T2-1").start();
//		new Thread(t2 , "T2-2").start();
//		new Thread(t2 , "T2-3").start();
	}

	
}

class TxtThread implements Runnable{

	static Object o = new Object();
	int num = 100;
	
	@Override
	public void run() {
//		synchronized(this){
			while (num > 0) {
//				try {
//					Thread.sleep(100);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				reduce();
			}
//		}
	}
	
	public synchronized void reduce(){
		System.out.println(Thread.currentThread().getName() + "'num is " + num--);
	}
	
}
