package com.taomk.understandingJVM.load;

/**
 * <pre>
 * 
 * http://blog.csdn.net/u013256816/article/details/50829596
 * 
 * </pre>
 * 
 * @author taomk 2017年6月12日 上午11:08:49
 *
 */
public class StaticTest {

	public static void main(String[] args) {
		staticFunction();
	}

	static StaticTest st = new StaticTest();

	static {
		System.out.println("1");
	}

	{
		System.out.println("2");
	}

	StaticTest() {
		System.out.println("3");
		System.out.println("a=" + a + ",b=" + b);
	}

	public static void staticFunction() {
		System.out.println("4");
	}

	int a = 110;
	static int b = 112;
}
