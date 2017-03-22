package com.taomk.understandingJVM.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 客户端-测试类
 * 
 * @author taomk 2017年3月22日 下午4:02:12
 *
 */
public class Client {

	public static void main(String[] args) {

		// 要代理的真实对象
		Subject realSubject = new SubjectImpl();

		// 通过真实对象来调用其方法
		InvocationHandler handler = new DynamicProxy(realSubject);

		Subject proxySubject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
//				realSubject.getClass().getInterfaces(), 
				new Class[]{Subject.class},
				handler);

		System.out.println(proxySubject.getClass().getName());
		System.out.println();
		
		proxySubject.sayHello("taomk");
		System.out.println();
		
		proxySubject.wish();
	}

}
