package com.taomk.understandingJVM.method_interface;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable FutureTask Study
 * @author taomk 2017年3月10日 下午9:20:12
 *
 */
public class CallableAndFutureStudy {

	public static void main(String[] args){
		
		Callable<Integer> call = new Callable<Integer>(){
			@Override
			public Integer call() throws Exception{
				return new Random().nextInt(100);
			}
		};
		
		FutureTask<Integer> task =new FutureTask<Integer>(call);
		new Thread(task).start();
		
		try {
			System.out.println("Other things ...");
			System.out.println(task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
