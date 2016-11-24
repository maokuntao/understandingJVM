package com.taomk.understandingJVM.gc;

/**
 * <pre>
 * 验证：对象优先在Eden分配
 * 	
 * VM Args:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 * </pre>
 * 
 * @author taomk 2016年11月24日 下午6:15:46
 *
 */
public class Allocation {

	private static final int _1MB = 1024*1024;
	
	/**
	 * 尝试分配了3个2MB大小和一个4MB大小的对象，
	 * 在运行时通过-Xms20M、-Xmx20M和-Xmn10M这三个参数限制Java堆大小为20MB，且不可扩展。
	 * 其中10MB分配给新生代，剩下的10MB分配给老生代。
	 * -XX:SurvivorRatio=8决定了新生代中Eden区与一个Survivor区的空间大小比例为8:1，
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
			byte[] allocation1 , allocation2, allocation3, allocation4;
			allocation1 = new byte[2*_1MB];
			allocation2 = new byte[2*_1MB];
			allocation3 = new byte[2*_1MB];
			allocation4 = new byte[4*_1MB];// 出现一次Minor GC
			
			/**
			 * 
			 * 
			 */
	}

}
