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

	private static int count = 1;
	
	private void call(){
		count++;
		call();
	}
	
	public static void main(String[] args) {

		StackErrorMock mock = new StackErrorMock();
		try {
			mock.call();
		} catch (Throwable e) {
			System.out.println("Stack deep : " + count);
			e.printStackTrace();
		}
	}

}
