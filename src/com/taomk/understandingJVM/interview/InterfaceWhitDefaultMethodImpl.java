package com.taomk.understandingJVM.interview;

/**
 * @author taomk 2017年4月6日 下午1:36:23
 *
 */
public class InterfaceWhitDefaultMethodImpl implements InterfaceWhitDefaultMethod {

	public static void main(String[] args) {
		InterfaceWhitDefaultMethod interfaceInstance = new InterfaceWhitDefaultMethodImpl();
		interfaceInstance.sayBye();
	}
}
