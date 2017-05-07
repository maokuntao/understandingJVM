package com.taomk.understandingJVM.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * OutOfMemoryError mock
 * @see http://www.cnblogs.com/paddix/p/5309550.html
 * @author taomk 2017年5月7日 下午10:08:16
 *
 */
public class OOMMock {

	public static void main(String[] args) {

		List<byte[]> list = new ArrayList<byte[]>();
		int loopCount = 0;
		boolean flag = true;
		while(flag){
			try {
				// 每次申请1M内存
				list.add(new byte[1024*1024]);
				loopCount++;
			} catch (Throwable e) {
				e.printStackTrace();
				flag = false;
				// 结果与设定的堆内存大小有关，不是固定的
				System.out.println("Count : " + loopCount);
			}
		}
		
	}

}
