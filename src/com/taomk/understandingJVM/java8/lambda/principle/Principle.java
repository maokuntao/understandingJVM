package com.taomk.understandingJVM.java8.lambda.principle;

/**
 * <pre>
 * Lambda表达式背后的原理:
 * 	http://blog.csdn.net/wwwsssaaaddd/article/details/24212693
 * 
 * </pre>
 * 
 * 
 * 
 * @author taomk 2017年6月19日 下午1:58:29
 *
 */
public class Principle {

	@FunctionalInterface
	interface Action{
		void run(String target);
	}
	
	public void action(Action target){
		target.run("_");
	}
	
	public static void main(String[] args) {
		new Principle().action(s -> System.out.println("^" + s + "^"));
	}
}
