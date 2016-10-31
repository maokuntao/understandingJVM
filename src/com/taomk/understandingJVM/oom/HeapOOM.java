package com.taomk.understandingJVM.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆内存溢出异常测试<br>
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author taomk 2016年10月31日 下午8:40:02
 *
 */
public class HeapOOM {

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}
}
