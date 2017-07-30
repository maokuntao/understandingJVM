package com.taomk.understandingJVM.innerClass;

/**
 * @author taomk 2017年7月30日 下午5:02:40
 *
 */
public class Outter {
	
	class Inner {
	}

	public static void foo() {
//		No enclosing instance of type Outter is accessible. Must qualify the allocation with an enclosing instance of type Outter (e.g. x.new A() where x is an instance of Outter).
//		new Inner();
	}

	public static void main(String[] args) {
//		No enclosing instance of type Outter is accessible. Must qualify the allocation with an enclosing instance of type Outter (e.g. x.new A() where x is an instance of Outter).
//		new Inner();
		new Outter().new Inner();
	}

	public void bar() {
		new Inner();
	}
}
