package demo.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU�㷨
 * 
 * @author wengqf
 * LinkedHashMap�����Ѿ�ʵ����˳��洢��Ĭ��������ǰ���Ԫ�ص����˳��洢��Ҳ�������ð��շ���˳��洢���������ȡ�����ݷ�����ǰ�棬
 * �����ȡ�����ݷ�������棬Ȼ��������һ���ж��Ƿ�ɾ���������ݵķ�����Ĭ���Ƿ���false������ɾ�����ݣ�����ʹ��LinkedHashMapʵ��LRU����ķ������Ƕ�LinkedHashMapʵ�ּ򵥵���չ
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {
	private final int MAX_CACHE_SIZE;
	private final float DEFAULT_LOAD_FACTOR = 0.75f;

	private LinkedHashMap<K, V> map = null;

	public LRUCache(int cacheSize) {
		MAX_CACHE_SIZE = cacheSize;
		// ����cacheSize�ͼ������Ӽ���hashmap��capactiy��+1ȷ�����ﵽcacheSize����ʱ���ᴥ��hashmap�����ݣ�
		int capacity = (int) Math.ceil((MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) + 1);
		//LinkedHashMap��һ�����캯����������accessOrderΪtrueʱ�����ᰴ�շ���˳������������ʵķ�����ǰ��������ʵķ��ں���
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
