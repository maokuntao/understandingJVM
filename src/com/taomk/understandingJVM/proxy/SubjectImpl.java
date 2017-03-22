package com.taomk.understandingJVM.proxy;

/**
 * 被代理对象-实现类
 * @author taomk 2017年3月22日 下午3:52:20
 *
 */
public class SubjectImpl implements Subject {

	/* (non-Javadoc)
	 * @see com.taomk.understandingJVM.proxy.Sublect#rent()
	 */
	@Override
	public void wish() {

		System.out.println("I wish a big house in beijing. ");
	}

	/* (non-Javadoc)
	 * @see com.taomk.understandingJVM.proxy.Sublect#sayHello(java.lang.String)
	 */
	@Override
	public void sayHello(String myName) {
		System.out.println("Hi, everyone. My name is " + myName);
	}

}
