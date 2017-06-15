package com.taomk.understandingJVM.concurrent.cow;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * 自定义实现一个CopyOnWrite的Map
 * 
 * http://ifeve.com/java-copy-on-write/comment-page-1/#comment-27735
 * </pre>
 * 
 * @author taomk 2017年6月15日 下午3:55:30
 *
 */
public class CopyOnWriteMap<K,V> implements Map<K,V>, Cloneable {

	private Map<K,V> internalMap;
	
	public CopyOnWriteMap(){
		internalMap = new HashMap<K,V>();
	}
	
	@Override
	public int size() {
		return internalMap.size();
	}

	@Override
	public boolean isEmpty() {
		return internalMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return internalMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return internalMap.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return internalMap.get(key);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 * 
	 * 当put元素时，遵守 Copy-On-Write 的原理，按照以下步骤进行：
	 * 1，加锁
	 * 2，创建有一个新的map
	 * 3，将原map内容复制到新的map
	 * 4，将元素put进入新的map
	 * 5，将原map指向新map
	 * 6，返回添加的value
	 * 7，解锁
	 */
	@Override
	public V put(K key, V value) {
		synchronized (this) {
			Map<K,V> newMap = new HashMap<K,V>(internalMap);
			V val = newMap.put(key, value);
			internalMap = newMap;
			return val;
		}
	}

	@Override
	public V remove(Object key) {
		synchronized (this) {
			Map<K,V> newMap = new HashMap<K,V>(internalMap);
			V val = newMap.remove(key);
			internalMap = newMap;
			return val;
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		synchronized (this) {
			Map<K,V> newMap = new HashMap<K,V>(internalMap);
			newMap.putAll(m);
			internalMap = newMap;
		}
	}

	@Override
	public void clear() {
		synchronized (this) {
			Map<K,V> newMap = new HashMap<K,V>(internalMap);
			newMap.clear();
			internalMap = newMap;
		}
	
	}

	@Override
	public Set<K> keySet() {
		return internalMap.keySet();
	}

	@Override
	public Collection<V> values() {
		return internalMap.values();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return internalMap.entrySet();
	}
	
}
