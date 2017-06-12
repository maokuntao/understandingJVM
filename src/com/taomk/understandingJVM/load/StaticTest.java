package com.taomk.understandingJVM.load;

/**
 * <pre>
 * 
 * http://blog.csdn.net/u013256816/article/details/50829596
 * 
 * http://blog.csdn.net/wangzi11322/article/details/44560243
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

	// 静态变量
	// static StaticTest st = new StaticTest();

	// 普通变量
	StaticTest st = new StaticTest();

	// 静态代码块
	static {
		System.out.println("1");
	}

	// 代码块
	{
		System.out.println("2");
	}

	// 构造函数
	StaticTest() {
		System.out.println("3");
		System.out.println("a=" + a + ",b=" + b);
	}

	// 静态方法
	public static void staticFunction() {
		System.out.println("4");
	}

	// 全局变量
	int a = 110;

	// 静态全局变量
	static int b = 120;
}
