package com.taomk.understandingJVM.jcf;

import java.util.Arrays;
import java.util.List;

/**
 * https://zhuanlan.zhihu.com/p/26019955
 * 
 * @author taomk 2017年8月3日 下午3:48:22
 *
 */
public class ArraysTest {

	public static void main(String[] args) {
		
		// 将一个字符串转换为ArrayList
		String str = "abcdefghijkl";
		List<String> strList = Arrays.asList(str);
		
		System.out.println(strList.getClass());
		strList.forEach(s -> System.out.printf(s + ","));
		
	}

}
