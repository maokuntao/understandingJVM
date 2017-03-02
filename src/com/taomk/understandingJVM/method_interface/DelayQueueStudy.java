package com.taomk.understandingJVM.method_interface;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * @author taomk 2017年3月2日 下午4:44:08
 *
 */
public class DelayQueueStudy {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Delayed> queue = new DelayQueue<Delayed>();
		
		Delayed element1 = new DelayedElement();
		queue.put(element1);
		
		DelayedElement element2 =  (DelayedElement)queue.take();
		System.out.println(element2.showState());
	}

}

class DelayedElement implements Delayed{

	public String showState(){
		return "OK";
	}
	
	@Override
	public int compareTo(Delayed o) {
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.toSeconds(3*1000);
	}
	
}