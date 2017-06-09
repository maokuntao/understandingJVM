package com.taomk.understandingJVM.load;

/**
 * @author taomk 2017年6月9日 下午2:02:02
 *
 */
public class SubClass extends SuperClass{

	static{
		System.out.println("SubClass init. ");
	}
	
	static int a;
	
//	public static String value = "SubClass.value";
	
	public SubClass(){
		System.out.println("SubClass constructor invoked. ");
	}
}
