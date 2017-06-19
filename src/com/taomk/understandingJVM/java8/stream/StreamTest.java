package com.taomk.understandingJVM.java8.stream;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 
 * <pre>http://blog.csdn.net/wwwsssaaaddd/article/details/24214219</pre>
 * @author taomk 2017年6月19日 下午3:01:30
 *
 */
public class StreamTest {

	public static void main(String[] args) {
		
		IntStream range =IntStream.range(0, 50).limit(10);
		range.forEach(System.out::println);

		System.out.println("_________________________________\n");
		
		new Random()
		  .ints()// 随机生成一条的整数数据流
		  .limit(10)// 我们只要10个随机整数
		  .forEach(System.out::println); 
	}
}
