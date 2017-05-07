package com.taomk.understandingJVM.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * String OOM Mock
 * @see http://www.cnblogs.com/paddix/p/5309550.html
 * 
 * @author taomk 2017年5月7日 下午10:34:49
 *
 */
public class StringOOMMock {

	// 不会出现JDK6中“PermGen Space”的错误信息，而是“Java heap space”
	// 说明在JDK8（本机Java版本是JDK8）中，字符串常量池是存放在堆（heap）中，其实JDK7也是
	public static void main(String[] args) {
		String base = "String";
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String temp = base+base;
			base = temp;
			list.add(temp.intern());
		}
		
	}

}
