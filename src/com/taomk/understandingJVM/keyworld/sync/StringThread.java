package com.taomk.understandingJVM.keyworld.sync;

/**
 * synchronized 关键字学习-String作为lock<br>
 * 
 * <a>
 * http://www.cnblogs.com/xrq730/p/6662232.html
 * </a>
 * 
 * @author taomk 2017年4月18日 下午2:04:30
 *
 */
public class StringThread implements Runnable {
	
	/**
	 * 锁的前缀
	 */
	private static final String PREFFIX = "LOCK_";
	
	/**
	 * 需要加锁的IP
	 */
	private String ip;
	
	public StringThread(String ip){
		this.ip = ip;
	}

	/**
	 * 构建锁
	 * 
	 * @return
	 */
	private String buildLock(){
		StringBuffer sb = new StringBuffer(PREFFIX).append(ip);
//		以下两行代码会导致执行结果不一致
//		String lockString = sb.toString();
		String lockString = sb.toString().intern();
		System.out.println(Thread.currentThread().getName() + " 构建了锁：" + lockString);
		return lockString;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		String lockString = buildLock();
		synchronized(lockString){
			System.out.println(Thread.currentThread().getName() + " 获取了锁");
			try {
				Thread.sleep(3*1000);
				System.out.println(Thread.currentThread().getName() + " 执行业务完毕");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 释放了锁");
		}
	}

	public static void main(String[] args) {

		final int THREAD_COUNT = 5;
		
		// Init
		Thread[] threads = new Thread[THREAD_COUNT];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new StringThread("192.168.1.1"));
		}
		
		// Fire 
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();;
		}
		
		// Ensure Main thread always running. 
		for(;;);
	}

}
