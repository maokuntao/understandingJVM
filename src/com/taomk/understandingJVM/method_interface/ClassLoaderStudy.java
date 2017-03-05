package com.taomk.understandingJVM.method_interface;

/**
 * @author taomk 2017年3月5日 下午5:47:46
 *
 */
public class ClassLoaderStudy {

	public static void main(String[] args) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println("Current Loader : " + loader);
		System.out.println("Parent Loader : " + loader.getParent());
		System.out.println("Grandparent Loader : " + loader.getParent().getParent());
	}
}
