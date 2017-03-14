package com.taomk.understandingJVM.method_interface;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <code>Map</code> Study
 * 
 * @author taomk 2017年3月14日 下午9:55:31
 *
 */
public class MapStudy {

	public static void main(String[] args) {

		System.out.println("accessOrder为false时（默认为false），基于插入顺序来获取元素");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String , String>(12, 0.75F, false);
		fillData(lhm);
		lhm.get("24");
		lhm.get("23");
		lhm.get("21");
		lhm.get("7");
		lhm.get("6");
		lhm.get("3");
		lhm.get("2");
		showData(lhm);
		
		System.out.println("accessOrder为true时，get一个元素后，这个元素被加到最后(使用了LRU 最近最少被使用的调度算法)");
		
		lhm = new LinkedHashMap<String , String>(12, 0.75F, true);
		fillData(lhm);
		lhm.get("24");
		lhm.get("23");
		lhm.get("21");
		lhm.get("7");
		lhm.get("6");
		lhm.get("3");
		showData(lhm);
	}

	private static void fillData(Map<String, String> lhm){
		lhm.put("0", "McGrady");
		lhm.put("2", "Irving");
		lhm.put("3", "Ai");
		lhm.put("6", "James");
		lhm.put("7", "Anthony");
		lhm.put("21", "TD");
		lhm.put("23", "Jordan");
		lhm.put("24", "Kobe");
	}
	
	private static void showData(Map<String, String> lhm){
		for (Iterator<Entry<String, String>> iterator = lhm.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> entry =  iterator.next();
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
