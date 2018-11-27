package demo.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU算法
 * 
 * @author wengqf
 * LinkedHashMap自身已经实现了顺序存储，默认情况下是按照元素的添加顺序存储，也可以启用按照访问顺序存储，即最近读取的数据放在最前面，
 * 最早读取的数据放在最后面，然后它还有一个判断是否删除最老数据的方法，默认是返回false，即不删除数据，我们使用LinkedHashMap实现LRU缓存的方法就是对LinkedHashMap实现简单的扩展
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {
	private final int MAX_CACHE_SIZE;
	private final float DEFAULT_LOAD_FACTOR = 0.75f;

	private LinkedHashMap<K, V> map = null;

	public LRUCache(int cacheSize) {
		MAX_CACHE_SIZE = cacheSize;
		// 根据cacheSize和加载因子计算hashmap的capactiy，+1确保当达到cacheSize上限时不会触发hashmap的扩容，
		int capacity = (int) Math.ceil((MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) + 1);
		//LinkedHashMap的一个构造函数，当参数accessOrder为true时，即会按照访问顺序排序，最近访问的放在最前，最早访问的放在后面
		map = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTOR, true) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4836115826006436089L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return size() > MAX_CACHE_SIZE;
			}
		};
	}

	public synchronized void put(K key, V value) {
		map.put(key, value);
	}

	public synchronized V get(K key) {
		return map.get(key);
	}

	public synchronized int size() {
		return map.size();
	}

	public synchronized void clear() {
		map.clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<K, V> entry : map.entrySet()) {
			sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
		}
		return sb.toString();
	}
}
