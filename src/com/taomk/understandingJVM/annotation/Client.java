package com.taomk.understandingJVM.annotation;

/**
 * 测试类
 * @author taomk 2017年7月26日 下午8:38:18
 *
 */
public class Client {

	public static void main(String[] args) {
		getGreetingMessage(GeetingAnnotationTest.class);
	}

	public static void getGreetingMessage(Class<?> clazz) {

		if(clazz.isAnnotationPresent(Greeting.class)){
			Greeting greetingMsg = clazz.getAnnotation(Greeting.class);
			System.out.println("message:" + greetingMsg.message());
			System.out.println("sayHi:" + greetingMsg.sayHi());
		}
	}

}
