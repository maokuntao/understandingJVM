package com.taomk.understandingJVM.jmm;

/**
 * 测试方法（栈帧允许的调用深度）
 * 
 * @see http://www.cnblogs.com/paddix/p/5309550.html
 * 
 * @author taomk 2017年5月7日 下午10:00:05
 *
 */
public class StackErrorMock {

	private static int loopCount = 1;

	private void call() {
		loopCount++;
		// 递归调用
		call();
	}

	public static void main(String[] args) {

		StackErrorMock mock = new StackErrorMock();
		try {
			mock.call();
		} catch (Throwable e) {// catch的不是Error，而是Throwable
			// 每次运行的结果不一样
			System.out.println("Stack deep : " + loopCount);
			e.printStackTrace();
		}
	}

}
