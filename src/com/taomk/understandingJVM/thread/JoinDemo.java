package com.taomk.understandingJVM.thread;

/**
 * http://blog.csdn.net/javazejian/article/details/50878665
 * 
 * @author taomk 2017年6月21日 下午3:41:51
 *
 */
public class JoinDemo {

	
	public static void main(String[] args) {
		Thread previousThread = Thread.currentThread();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Domino(previousThread), i+"");
			t.start();
			previousThread = t;
		}
		System.out.println(Thread.currentThread().getName() + "线程执行结束。");
	}
}

class Domino implements Runnable{

	private Thread thread;
	Domino(Thread t){
		this.thread = t;
	}
	
	@Override
	public void run() {

		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + "线程执行结束。");
		
	}
	
}