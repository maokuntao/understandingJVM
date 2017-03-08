package com.taomk.understandingJVM.oom;

/**
 * @author taomk 2017年3月6日 下午7:13:29
 *
 */
public class Test {

	public static void main(String[] args) {

		Integer i1 = 123;
		Integer i2 = 123;
		Integer i3 = new Integer(123);
		Integer i4 = 128;
		Integer i5 = 128;
		
		int i6 = 123;
		int i7 = 1234;
		
		System.out.println(i1 == i2);
		System.out.println(i1.equals(i2));
		
		System.out.println(i1 == i3);
		System.out.println(i1.equals(i3));
		
		System.out.println(i1 == i6);
		System.out.println(i1.equals(i6));
		
		System.out.println(i4 == i5);
		System.out.println(i4.equals(i5));
		

		System.out.println(i3 == i7);
//		System.out.println(i5.equals(i4));
	}

}
