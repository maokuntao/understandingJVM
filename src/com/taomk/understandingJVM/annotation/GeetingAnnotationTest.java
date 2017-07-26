package com.taomk.understandingJVM.annotation;

/**
 * 业务使用类
 * @author taomk 2017年7月26日 下午8:35:04
 *
 */
@Greeting(message="Test" , sayHi="233333")
public class GeetingAnnotationTest {

	void foo(){
		System.out.println("bar~");
	}
}
