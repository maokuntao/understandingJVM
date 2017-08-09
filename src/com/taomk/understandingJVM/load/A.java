package com.taomk.understandingJVM.load;

/**
 * @author taomk 2017年7月30日 下午5:10:50
 *
 */
public class A {
	
	{
		System.out.println("非静态代码块A");
	}

	static {
		System.out.println("静态代码块A");
	}
	
	A(){
		System.out.println("无参构造函数A");
	}
	
	A(int i){
		System.out.println("有参构造函数A,i=" + i);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		A ab = new B();
		ab = new B();
//		ab = new B(233);
	}
}

class B extends A{
	
	{
		System.out.println("非静态代码块B");
	}
	
	static {
		System.out.println("静态代码块B");
	}
	
	static void bar() {
		System.out.println("静态方法bar()");
	}
	
	B(){
//		super(2);
		System.out.println("无参构造函数B");
	}
	
	B(int j){
		super(j);
		System.out.println("有参构造函数B,j=" + j);
	}
	
	void foo() {
		System.out.println("普通函数foo()");
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		A ab = new B();
		ab = new B();
		ab = new B(233);
	}
}

