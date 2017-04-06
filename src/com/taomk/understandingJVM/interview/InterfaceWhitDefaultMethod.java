package com.taomk.understandingJVM.interview;

/**
 * 带有默认方法的接口
 * 
 * @author taomk 2017年4月6日 下午1:34:03
 *
 */
public interface InterfaceWhitDefaultMethod {

	String a = "test";
	
	default void sayHi(){
		System.out.println("InterfaceWhitDefaultMethod says : 'Hi!'");
	}
	
	default void sayBye(){
		System.out.println("InterfaceWhitDefaultMethod says : 'Bye!'");
	}
}
