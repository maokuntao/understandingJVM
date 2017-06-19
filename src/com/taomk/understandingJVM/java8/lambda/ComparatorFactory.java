package com.taomk.understandingJVM.java8.lambda;

import java.util.Comparator;

/**
 * 函数接口可以用作方法的返回值
 * 
 * @author taomk 2017年6月19日 下午1:41:56
 *
 */
public class ComparatorFactory {

	/**
	 * @return lambda表达式
	 */
	public Comparator<Integer> makeComparator(){
		return Integer::compareUnsigned;
	}
	
	public static void main(String[] args) {
		Comparator<Integer> com = new ComparatorFactory().makeComparator();
		System.out.println(com.compare(233, 332));
	}

}
