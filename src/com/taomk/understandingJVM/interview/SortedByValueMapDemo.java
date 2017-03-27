package com.taomk.understandingJVM.interview;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 蚂蚁金服面试时提的一个问题：将Map中的value进行排序
 * 
 * @author taomk 2017年3月26日 下午10:22:45
 *
 */
public class SortedByValueMapDemo {

	public static void main(String[] args) {

		Map<String, BuzObj> buzObjMap = new HashMap<String, BuzObj>();
		buzObjMap.put("LA-1", new BuzObj(8, "Kobe"));
		buzObjMap.put("LA-2", new BuzObj(34, "Shaq"));
		buzObjMap.put("LA-3", new BuzObj(13, "Nash"));
		buzObjMap.put("SA-1", new BuzObj(2, "Leonard"));
		buzObjMap.put("SA-2", new BuzObj(8, "Parker"));
		buzObjMap.put("SA-3", new BuzObj(20, "Manu"));
		buzObjMap.put("SA-4", new BuzObj(21, "Duncan"));
		
		System.out.println("初始状态:\n" + buzObjMap);
		
		Map<String, BuzObj> result = sort(buzObjMap);
		
		System.out.println("排序之后:\n" + result);
	}
	
	static Map<String, BuzObj> sort(Map<String, BuzObj> buzObjMap){
		List<Map.Entry<String, BuzObj>> entryList = new LinkedList<Map.Entry<String, BuzObj>>(buzObjMap.entrySet());
		Collections.sort(entryList, new Comparator<Map.Entry<String, BuzObj>>() {
			
			@Override
			public int compare(Map.Entry<String, BuzObj> o1 , Map.Entry<String, BuzObj> o2){
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		Map<String, BuzObj> result = new LinkedHashMap<String, BuzObj>();
		for (Entry<String, BuzObj> entry : entryList) {
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}

}

/**
 * 待比较的业务对象
 * 
 * @author taomk 2017年3月26日 下午10:28:15
 *
 */
class BuzObj implements Comparable<BuzObj>{
	
	private Integer number;
	private String name;
	
	public BuzObj(Integer number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

	@Override
	public String toString() {
		return "\t BuzObj [number=" + number + ", name=" + name + "]\n";
	}

	@Override
	public int compareTo(BuzObj o) {
		
		return this.number - o.number;
		
	}
}