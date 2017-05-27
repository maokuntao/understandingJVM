package com.taomk.understandingJVM.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>http://ifeve.com/atomic-operation/</pre>
 * @author taomk 2017年5月27日 下午4:16:30
 *
 */
public class AtomicStudy {
	
	private AtomicInteger atomicI = new AtomicInteger();
	private int commonI;
	private volatile int volatileI;

	private static final int THREAD_COUNT = 100;
	
	public static void main(String[] args) {
		AtomicStudy counter = new AtomicStudy();
		List<Thread> threadList = new ArrayList<Thread>(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 10*1000; j++) {
//						counter.commonCount();
						counter.safeCount();
						counter.volatileCount();
						counter.atomicCount();
					}
				}
			});
			threadList.add(t);
		}
		
		for (Thread t : threadList) {
			t.start();
		}
		
		for (Thread t : threadList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("commonI : " + counter.commonI);
		System.out.println("volatileI : " + counter.volatileI);
		System.out.println("atomicI : " + counter.atomicI.get());
		
	}
	
	/**
	 * 普通计数方式
	 */
	private void commonCount(){
		commonI++;
	}

	/**
	 * volatile计数方式
	 */
	private void volatileCount(){
		volatileI++;
	}
	
	/**
	 * atomic计数方式
	 */
	private void atomicCount(){
		atomicI.incrementAndGet();
	}
	
	/**
	 * 安全的计数方式
	 */
	private void safeCount(){
		while(true){
			int i = atomicI.get();
			boolean suc = atomicI.compareAndSet(i, ++i);
			if(suc){
				break;
			}
		}
	}
}
