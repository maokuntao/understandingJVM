package com.taomk.understandingJVM.oom;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * <pre>
 * 	使用unsafe分配本机内存<br>
 * VM Args:-Xmx20M -XX:MaxDirectMemorySize=10M
 * 通过反射直接使用Unsafe类提供的allocateMemory()方法请求内存。
 * </pre>
 * 
 * @author taomk 2016年11月7日 下午2 :24:44
 *
 */
@SuppressWarnings("restriction")
public class DirectMemoryOOM {

	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		try {
			Unsafe unsafe = (Unsafe) unsafeField.get(null);
			while(true){
				unsafe.allocateMemory(_1MB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
