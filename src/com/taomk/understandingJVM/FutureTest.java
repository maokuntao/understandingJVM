package com.taomk.understandingJVM;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * http://www.cnblogs.com/dolphin0520/p/3949310.html
 * 
 * @author taomk 2017年8月24日 下午7:15:03
 *
 */
public class FutureTest {

	public static void main(String[] args) {
		ExecutorService executors = Executors.newCachedThreadPool();
		Task task = new Task();
		Future<Integer> result = executors.submit(task);
		executors.shutdown();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main thread is running...");
		
		try {
			System.out.println("Task result : " + result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("All thread is end.");
	}
}

class Task implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {

		System.out.println("Task is running...");
		Thread.sleep(2*1000);
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			sum += i;
		}
		
		return sum;
	}
	
}