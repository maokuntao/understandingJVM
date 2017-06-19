package com.taomk.understandingJVM.java8.lambda.defaultMethod;

import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * http://blog.csdn.net/wwwsssaaaddd/article/details/24213525
 * </pre>
 * 
 * @author taomk 2017年6月19日 下午2:30:21
 *
 */
public class DefaultMethod {

	public static void main(String[] args) {

		// init
		List<String> list = new LinkedList<String>();
		list.add("Kobe");
		list.add("Duncan");
		list.add("Iverson");
		list.add("James");

		// forEach方法使用函数接口java.util.function.Consumer作为参数，
		// 该参数使我们能传递一个Lambda表达式或者方法引用到forEach中
		list.forEach(System.out::println);
	}
}
