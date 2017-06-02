package com.taomk.understandingJVM.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * http://ifeve.com/talk-concurrency-forkjoin/
 * 
 * @author taomk 2017年6月2日 下午3:21:27
 *
 */
public class ForkJoinStudy extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	// 子任务的最大容纳量
	private static final int THRESHOLD = 10;

	// 计算范围
	private int start, end;

	public ForkJoinStudy(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		
		StringBuffer sb = new StringBuffer(Thread.currentThread().getName() + " compute()...");
		
		int sum = 0;
		boolean canCompute = (end - start) <= THRESHOLD;

		// 达到可计算范围，则进行计算
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
			System.out.println(sb.append(sum));
		} else {
			// 否则，分裂成2个子任务
			int middle = (start + end) / 2;
			ForkJoinStudy leftTask = new ForkJoinStudy(start, middle);
			ForkJoinStudy rightTask = new ForkJoinStudy(middle+1, end);
			// 执行子任务，实际上会调用compute()方法
			leftTask.fork();
			rightTask.fork();
			// 等待子任务执行完成，获取计算结果
			int leftResult = leftTask.join();

			int rightResult = rightTask.join();
			// 合并计算结果
			sum = leftResult + rightResult;
		}
		return sum;
	}

	public static void main(String[] args) {
		// 计算1+2+3+...+100
		ForkJoinStudy task = new ForkJoinStudy(1, 100);
		ForkJoinPool pool = new ForkJoinPool();
		Future<Integer> result = pool.submit(task);

		try {
			System.out.println("Result : " + result.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
