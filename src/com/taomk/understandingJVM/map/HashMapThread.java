package com.taomk.understandingJVM.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HashMap在高并发下可能导致的并发异常
 * 
 * @author taomk 2017年8月7日 下午2:33:55
 *
 */
public class HashMapThread extends Thread{

	private static AtomicInteger ai = new AtomicInteger();
	private static Map<Integer, Integer> m = new HashMap<Integer, Integer>(1);
	
	@Override
	public void run() {
		while(ai.get()<1000*1000){
			m.put(ai.get(), ai.get());
			ai.incrementAndGet();
		}
	}
	
	public static void main(String[] args) {
		
		HashMapThread hmt0 = new HashMapThread();
	    HashMapThread hmt1 = new HashMapThread();
	    HashMapThread hmt2 = new HashMapThread();
	    HashMapThread hmt3 = new HashMapThread();
	    HashMapThread hmt4 = new HashMapThread();
	    
	    hmt0.start();
	    hmt1.start();
	    hmt2.start();
	    hmt3.start();
	    hmt4.start();
		
	}

}
