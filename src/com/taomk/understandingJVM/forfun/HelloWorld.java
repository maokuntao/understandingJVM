package com.taomk.understandingJVM.forfun;

import java.util.Random;

/**
 * Random传入seed，伪随机
 * @author taomk 2017年6月19日 上午11:12:39
 *
 */
public class HelloWorld {

	public static void main(String... args) {
		System.out.println(randomString(-229985452) + ' ' + randomString(-147909649));
	}

	public static String randomString(int seed) {
		Random rand = new Random(seed);
		StringBuilder sb = new StringBuilder();
		while (true) {
			int n = rand.nextInt(27);
			if (n == 0)
				break;
			sb.append((char) ('`' + n));
		}
		return sb.toString();
	}

}
