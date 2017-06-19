package com.taomk.understandingJVM.java8.lambda;

import java.io.Serializable;
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
		// 认为作为返回结果的Comparator 实例对象也是Serializable类型的。
		// Serializable接口也可以称作ZAM类型（ZAM即Zero Abstract Methods）。
		return (Comparator<Integer> & Serializable)Integer::compareUnsigned;
	}
	
	public static void main(String[] args) {
		Comparator<Integer> com = new ComparatorFactory().makeComparator();
		System.out.println(com.compare(233, -332));
	}

}
