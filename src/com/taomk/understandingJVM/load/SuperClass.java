package com.taomk.understandingJVM.load;

/**
 * @author taomk 2017年6月9日 下午1:59:57
 *
 */
public class SuperClass extends SSClass {

	static{
		System.out.println("SuperClass init. ");
	}
	
	public static String value = "SSClass.value";
	
	public SuperClass(){
		System.out.println("SuperClass constructor invoked. ");
	}
}
