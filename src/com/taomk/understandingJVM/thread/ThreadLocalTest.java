package com.taomk.understandingJVM.thread;

/**
 * http://blog.csdn.net/hudashi/article/details/7076880
 * 
 * @author taomk 2017年10月10日 下午2:14:57
 *
 */
public class ThreadLocalTest {
	
	public ThreadLocalTest() {
		// do nothing
	}

	ThreadLocal<Content> tl = new ThreadLocal<Content>();

	void start() {
		System.out.println("begin");
		Content content = tl.get();
		if (content == null) {
			content = new Content();
			tl.set(content);
		}
		
		System.out.println("try to release content data");
		// tl.set(null);//@1
		// tl.remove();//@2
		
		tl = null;// @3
		content = null;// @4
		
		System.out.println("request gc");
		System.gc();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
}

class Content {
	
	// 10M
	byte data[] = new byte[1024 * 1024 * 10];

	protected void finalize() {
		System.out.println("I am released");
	}
}
