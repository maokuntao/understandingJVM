package com.taomk.understandingJVM.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 成员变量或者是局部变量来实现的本地缓存
 * 
 * https://zhuanlan.zhihu.com/p/27457401
 * 
 * @author taomk 2017年7月20日 下午8:55:16
 *
 */
public class LocalCache {

	/**
	 * 模拟数据库，里面存储的是 com.taomk.understandingJVM.cache.Goods
	 */
	Map<String, Object> goodsInDB = new HashMap<String, Object>();
	
	/**
	 * 缓存
	 */
	Map<String, Object> localCacheStoreMap = new HashMap<String, Object>();

	LocalCache() {

		// init
		Goods nike = new Goods("10001", "20001", "nike");
		goodsInDB.put(nike.getId(), nike);

		Goods adidas = new Goods("10002", "20001", "adidas");
		goodsInDB.put(adidas.getId(), adidas);

		Goods nb = new Goods("10003", "20001", "new balance");
		goodsInDB.put(nb.getId(), nb);

		Goods ua = new Goods("10004", "20001", "under armour");
		goodsInDB.put(ua.getId(), ua);

		Goods armani = new Goods("10005", "20002", "armani");
		goodsInDB.put(armani.getId(), armani);

		Goods gucci = new Goods("10006", "20003", "gucci");
		goodsInDB.put(gucci.getId(), gucci);
	}
	
	public static void main(String[] args) {
		
		LocalCache cacheTest = new LocalCache();
		System.out.println("第一次执行：");
		long startTime = System.currentTimeMillis();
		cacheTest.useLocalCache();
		long endTime = System.currentTimeMillis();
		System.out.println("\t耗时：(ms)" + (endTime-startTime));
		
		System.out.println("第二次执行：");
		startTime = System.currentTimeMillis();
		cacheTest.useLocalCache();
		endTime = System.currentTimeMillis();
		System.out.println("\t耗时：(ms)" + (endTime-startTime));
		
	}

	public Object useLocalCache() {

		List<String> infoList = this.getInfoList();

		Object valueObject = null;
		for (String id : infoList) {
			if (localCacheStoreMap.containsKey(id)) {// 缓存命中，从缓存中获取数据
				System.out.println("\tGet from localCache : ");
				valueObject = localCacheStoreMap.get(id);
				System.out.println("\t\tvalueObject : " + valueObject);
			} else {
				System.out.println("\tGet from DB : ");
				valueObject = this.getInfoFromDB(id);
				System.out.println("\t\tvalueObject : " + valueObject);
				localCacheStoreMap.put(id, valueObject);
			}
		}
		
		return valueObject;
	}

	/**
	 * 从DB中获取数据
	 * 
	 * @return
	 */
	private Object getInfoFromDB(String id) {
		// 可能比较耗时
		try {
			Thread.sleep(1 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return goodsInDB.get(id);
	}

	/**
	 * 获取Info List，有可能是一个ID List
	 * 
	 * @return
	 */
	public List<String> getInfoList() {

		List<String> idList = new ArrayList<String>();
		idList.add("10001");
		idList.add("10002");
		idList.add("10003");

		return idList;
	}
}

class Goods implements Serializable {

	/**
	 * 自动生成的序列号
	 */
	private static final long serialVersionUID = 6572328463371831346L;

	private String id;
	private String shopId;
	private String name;

	public Goods(String id, String shopId, String name) {
		super();
		this.id = id;
		this.shopId = shopId;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", shopId=" + shopId + ", name=" + name + "]";
	}

}