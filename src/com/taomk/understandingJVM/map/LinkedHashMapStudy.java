package com.taomk.understandingJVM.map;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * <pre>
 * 	LinkedHashMap 学习
 * </pre>
 * 
 * @see LinkedHashMap
 * @author taomk 2017年6月16日 上午10:17:09
 *
 */
public class LinkedHashMapStudy {

	public static void main(String[] args) {

//		默认按照插入顺序排序（accessOrder默认为faslse）
		LinkedHashMap<String, Integer> lmap = new LinkedHashMap<String, Integer>();
		lmap.put("语文", 1);
		lmap.put("数学", 2);
		lmap.put("英语", 3);
		lmap.put("历史", 4);
		lmap.put("政治", 5);
		lmap.put("地理", 6);
		lmap.put("生物", 7);
		lmap.put("化学", 8);
		lmap.put("物理", 9);

		System.out.println("成绩列表：");
		// 可以看出是按照元素的插入顺序来迭代输出的
		for (Entry<String, Integer> entry : lmap.entrySet()) {
			System.out.println("\t" + entry.getKey() + ": " + entry.getValue());
		}
		
		System.out.println("_________________________LRU_________________________");
		
		
		LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>(16, 0.75F, true);
		linkedHashMap.put("语文", 1);
		linkedHashMap.put("数学", 2);
		linkedHashMap.put("英语", 3);
		linkedHashMap.put("历史", 4);
		linkedHashMap.put("政治", 5);
		linkedHashMap.put("地理", 6);
		linkedHashMap.put("生物", 7);
		linkedHashMap.put("化学", 8);
		linkedHashMap.put("物理", 9);
		System.out.println("副科成绩：");
		System.out.println("\t历史："+linkedHashMap.get("历史"));
		System.out.println("\t政治："+linkedHashMap.get("政治"));
		System.out.println("\t地理："+linkedHashMap.get("地理"));
		System.out.println("\t生物："+linkedHashMap.get("生物"));
		System.out.println("\t化学："+linkedHashMap.get("化学"));
		System.out.println("\t物理："+linkedHashMap.get("物理"));
		
		System.out.println("主科成绩：");
		System.out.println("\t语文："+linkedHashMap.get("语文"));
		System.out.println("\t数学："+linkedHashMap.get("数学"));
		System.out.println("\t英语："+linkedHashMap.get("英语"));
		
		System.out.println("成绩列表：");
		// 可以看出是按照元素的插入顺序来迭代输出的
		for (Entry<String, Integer> entry : linkedHashMap.entrySet()) {
			System.out.println("\t" + entry.getKey() + ": " + entry.getValue());
		}
	}

}
