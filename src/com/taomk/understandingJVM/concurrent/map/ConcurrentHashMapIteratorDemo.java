package com.taomk.understandingJVM.concurrent.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * https://mp.weixin.qq.com/s?__biz=MzIxOTI1NTk5Nw%3D%3D&mid=2650047468&idx=1&sn=6fc28065f97cc27698839c945c892cd8&chksm=8fde263eb8a9af28e7d03ff19433615e8121e4b898c51c4c60b26917bf130c9fcd1e783fcd12&scene=21
 * 
 * @author taomk 2017年9月5日 下午2:50:41
 *
 */
public class ConcurrentHashMapIteratorDemo {

	public static void main(String[] args) {
		test();
	}

	public static void test() {

		ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<String, String>();
		chm.put("a", "C++");
		chm.put("b", "C");
		chm.put("c", "python");

		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (Map.Entry<String, String> entry : chm.entrySet()) {
					// 弱一致性
//					try {
//						Thread.sleep(1_000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					System.out.println(entry.getKey() + " : " + entry.getValue());
				}
			}
		};

		t1.start();

		// 确保线程t1启动
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		chm.put("d", "javascript");
	}
}
