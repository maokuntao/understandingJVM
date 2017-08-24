package com.taomk.understandingJVM;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * http://www.cnblogs.com/dolphin0520/p/3949310.html
 * 
 * @author taomk 2017年8月24日 下午7:15:03
 *
 */
public class FutureTest {

	public static void main(String[] args) {
		
		Task task = new Task();

		// 第一种方式来使用Task + Future，通过Executor来使用
//		ExecutorService executors = Executors.newCachedThreadPool();
//		Future<Integer> result = executors.submit(task);
//		executors.shutdown();
		
		// 第二种方式来使用FutureTask，通过Thread来使用
		FutureTask<Integer> result = new FutureTask<Integer>(task);
		Thread taskThread = new Thread(result);
		taskThread.start();
		
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