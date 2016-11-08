package com.taomk.understandingJVM.gc;

/**
 * <pre>
 * 引用计数算法的缺陷是不能很好的解决对象之间循环引用的问题。
 * 示例代码证明了，虚拟机并不是采用引用计数算法来判断对象是否存活的。
 * </pre>
 * @author taomk 2016年11月8日 下午7:46:53
 *
 */
public class ReferenceCountingGC {
	
	public Object instance = null;
	
	private static final int _1MB = 1024*1024;
	
	// 这个成员属性的唯一意义就是占点内存，以便在GC日志中能清楚是否被回收过
	private byte[] bigsize = new byte[2*_1MB];
	
	public static void main(String[] args) {
		
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;
		
		objA = null;
		objB = null;
		
		// 执行GC
		System.gc();
		
	}

}
