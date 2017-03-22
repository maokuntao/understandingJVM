package com.taomk.understandingJVM.proxy;

/**
 * proxy study
 * 被代理对象-接口定义
 * @author taomk 2017年3月22日 下午3:49:58
 *
 */
public interface Subject {

	default void a(){}
	
	default void b(){}
	
	public void wish();
	
	public void sayHello(String myName);
}
