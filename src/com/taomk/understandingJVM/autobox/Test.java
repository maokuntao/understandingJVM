package com.taomk.understandingJVM.autobox;

/**
 * https://zhuanlan.zhihu.com/p/28645185?group_id=882741314054152192
 * 
 * @author taomk 2017年8月21日 下午6:41:28
 *
 */
public class Test {

	public static long test(long value) {
        return value;
    }

    public static void main(String[] args) {
        Long value = null;
        // ...
        test(value);
    }
}
