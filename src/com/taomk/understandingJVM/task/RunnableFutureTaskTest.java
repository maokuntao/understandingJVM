package com.taomk.understandingJVM.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
 * http://blog.csdn.net/bboyfeiyu/article/details/24851847
 * 
 * @author taomk 2017年8月29日 下午2:28:31
 *
 */
public class RunnableFutureTaskTest {

	static ExecutorService fixedService = Executors.newSingleThreadExecutor();
	
	public static void main(String[] args) {
	
		runnableDemo();
		try {
			futureDemo();
			callableDemo();
			futureTaskDemo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 实现runnable接口的方式，无法直接获取返回值
	 */
	static void runnableDemo() {
		new Thread(() -> System.out.println("runnable demo result : " + fibrc(20))).start();
	}
	
	/**
	 * 向ExecutorService 提交runnable则没有返回值, future没有数据 
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	static void futureDemo() throws InterruptedException, ExecutionException {
		Future<?> futureResult = fixedService.submit(new Runnable() {

			@Override
			public void run() {
				fibrc(20);
			}
			
		});
		System.out.println("future demo result : " + futureResult.get());
	}
	
	/**
	 * 向ExecutorService 提交callable则没有返回值, future有数据 
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	static void callableDemo() throws InterruptedException, ExecutionException {
		Future<Integer> futureResult = fixedService.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return fibrc(20);
			}
			
		});
		System.out.println("callable demo result : " + futureResult.get());
	}
	
	static void futureTaskDemo() throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return fibrc(20);
			}
			
		});
		
		fixedService.submit(futureTask);
		System.out.println("futureTask demo result : " + futureTask.get());
	}
	
	/**
	 * 计算斐波那契数列
	 * 
	 * @param num
	 * @return
	 */
	static int fibrc(int num) {
		
		if(num==0 || num==1) {
			return num;
		}
		
		return fibrc(num-1) + fibrc(num-2);
	}
}
