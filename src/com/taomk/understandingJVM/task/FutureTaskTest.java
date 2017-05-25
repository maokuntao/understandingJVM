package com.taomk.understandingJVM.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTask Study
 * 
 * @author taomk 2017年5月16日 下午9:55:40
 *
 */
public class FutureTaskTest {

	public static void main(String[] args) {

		// 创建任务集合
		List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
		
		// 创建线程池
		ExecutorService exec = Executors.newFixedThreadPool(5);
		
		for (int i = 0; i < 10; i++) {
			// 传入Callable对象创建FutureTask对象
			FutureTask<Integer> ft = new FutureTask<Integer>(new ComputeTask(i, "" + i));
			taskList.add(ft);
			// 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务;
			exec.submit(ft);
		}

		System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！" + Thread.currentThread().getName());

		// 开始统计各计算线程计算结果
		Integer totalResult = 0;
		for (FutureTask<Integer> ft : taskList) {
			try {
				// FutureTask的get方法会自动阻塞,直到获取计算结果为止
				totalResult = totalResult + ft.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		// 关闭线程池
		exec.shutdown();
		System.out.println("多任务计算后的总结果是:" + totalResult);
		System.out.println("主线程运行结束！" + Thread.currentThread().getName());
	}

}

class ComputeTask implements Callable<Integer> {
	
	private Integer result = 0;
	private String taskName = "";
	
	public ComputeTask(Integer iniResult, String taskName) {
		result = iniResult;
		this.taskName = taskName;
		System.out.println("生成子线程计算任务: " + taskName);
	}
	
	public String getTaskName() {
		return this.taskName;
	}
	
	@Override
	public Integer call() throws Exception {
		for (int i = 0; i < 100; i++) {
			result = +i;
		}
		// 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果等待直至完成。
		Thread.sleep(1000*3);
		System.out.println("子线程计算任务: " + taskName + " 执行完成!当前结果为：" + result);
		return result;
	}
}