package com.taomk.understandingJVM.load;

/**
 * <pre>
 * 
 * http://blog.csdn.net/u013256816/article/details/50829596
 * 
 * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。 
 * </pre>
 * 

 * @author taomk 2017年6月9日 下午2:03:46
 *
 */
public class Test {

	public static void main(String[] args) {
		System.out.println(SubClass.value);
	}
}
