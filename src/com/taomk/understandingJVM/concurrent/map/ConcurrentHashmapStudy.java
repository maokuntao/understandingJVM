package com.taomk.understandingJVM.concurrent.map;

import java.util.HashMap;
import java.util.UUID;

/**
 * <pre>
 * 
 * http://ifeve.com/concurrenthashmap/
 * </pre>
 * 
 * @author taomk 2017年5月27日 下午1:47:55
 *
 */
public class ConcurrentHashmapStudy {

	public static void main(String[] args) {
		try {
			deadLoop();
		} catch (InterruptedException e) {
			System.err.println(e.toString());
		}
	}

	
	/**
	 * 演示HashMap引起的死循环
	 * @throws InterruptedException 
	 */
	public static void deadLoop() throws InterruptedException{
		
//		初始容量为2
		final HashMap<String, String> map = new HashMap<String, String>(2);

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                            System.out.println("HashMap dead loop demo : inner .");
                        }

                    }, "HashMap dead loop demo : " + i).start();

                }
                System.out.println("HashMap dead loop demo : outter .");
            }

        }, "HashMap Dead Loop Demo. ");

        t.start();

        t.join();
	}
}
