package com.taomk.understandingJVM.lock.wait;

/**
 * {@link https://segmentfault.com/q/1010000009461368}
 * 
 * @author taomk 2017年5月18日 上午10:48:08
 *
 */
public class DeadLock {

	// 锁对象，之所以这么创建是为了尽量减少内存消耗
	private byte[] lock = new byte[0];

	// 生产开关flag
	private volatile boolean isProduct = false;

	// 计数
	private int count = 0;

	/**
	 * 生产动作
	 */
	public void produce() {
		synchronized (lock) {
			if (isProduct) {
				try {
					System.out.println("produce loop...");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				count++;
				System.out.println("P ---> " + count);
				lock.notify();
				isProduct = true;
			}
		}
	}

	/**
	 * 消费动作
	 */
	public void consume() {
		synchronized (lock) {
			if (isProduct) {
				System.out.println("C ---> " + count);
				lock.notify();
				isProduct = false;
			} else {
				try {
					System.out.println("consume loop...");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {

		DeadLock d = new DeadLock();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				d.consume();
			}
		}).start();
		
		new Thread(){
			@Override
			public void run() {
				try {
					System.out.println("Sleeping");
					sleep(1*1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				for (int i = 0; i < 10; i++) {
					d.produce();
				}
			}
		}.start();
		
	}

}
