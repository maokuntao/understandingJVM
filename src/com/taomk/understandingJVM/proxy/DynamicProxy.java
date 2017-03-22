package com.taomk.understandingJVM.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * proxy study
 * @author taomk 2017年3月22日 下午3:55:28
 *
 */
public class DynamicProxy implements InvocationHandler {
	
	/**
	 * 被代理对象
	 */
	private Object target;
	
	DynamicProxy(Object subject){
		this.target = subject;
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("proxy : " + proxy.getClass().getName());
		
		System.out.println("Before saying my wish...");
		
		System.out.println("Method : " + method);
		
		method.invoke(target, args);
		
		System.out.println("Thank you, that's the end of my speech. \n");
		
		return null;
	}

}
