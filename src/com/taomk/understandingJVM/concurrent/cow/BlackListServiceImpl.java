package com.taomk.understandingJVM.concurrent.cow;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * 使用CopyOnWrite机制来实现黑名单
 * 
 * @author taomk 2017年6月5日 下午7:27:22
 *
 */
public class BlackListServiceImpl {

	private static CopyOnWriteArrayList<String> blackList = new CopyOnWriteArrayList<String>();
	
	public static int size(){
		return blackList.size();
	}
	
	public static boolean isInBlackList(String target){
		return blackList.contains(target);
	}
	
	public static void addIntoBlackList(String target){
		blackList.add(target);
	}
	
	public static void addIntoBlackListBulk(ArrayList<String> targetList){
		blackList.addAll(targetList);
	}
	
	public static void removeFromBlackList(String target){
		blackList.remove(target);
	}
	
	public static void main(String[] args) {

	}

}
