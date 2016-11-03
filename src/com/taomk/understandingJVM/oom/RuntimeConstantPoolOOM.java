package com.taomk.understandingJVM.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 运行时常量池导致的内存溢出异常<br>
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M<br>
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=10M; support was removed in 8.0
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=10M; support was removed in 8.0
 * </pre>
 * @author taomk 2016年11月3日 上午10:57:11
 *
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		// 使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		// 10MB的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}

}
