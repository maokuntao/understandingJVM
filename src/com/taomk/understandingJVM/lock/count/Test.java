package com.taomk.understandingJVM.lock.count;

/**
 * 测试类
 * 
 * @author taomk 2017年6月14日 上午11:22:26
 *
 */
public class Test {

	public static void main(String[] args) {

		Counter c = new Counter();
		
		new Thread("T-1"){
		
			@Override
			public void run() {
				
				Counter c1 = new Counter();
				for (int i = 0; i < 100; i++) {
					
//					System.out.println(Thread.currentThread().getName() + " : " + c1.inc());
					System.out.println(Thread.currentThread().getName() + " : " + c.inc());
				}
			}
		}.start();;
		
		new Thread("T-2"){
		
			Counter c2 = new Counter();
			
			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					
//					System.out.println(Thread.currentThread().getName() + " : " + c2.inc());
					System.out.println(Thread.currentThread().getName() + " : " + c.inc());
					
				}
			}
		}.start();;
	}

}
