package demo.lru;

public class LRUCacheTest {

	public static void main(String[] args) {
		lruCache();
	}
	
	private static void lruCache() {
		System.out.println();
		System.out.println("===========================LRU LinkedHashMap(delegation)й╣ож===========================");
		LRUCache<Integer, String> lru = new LRUCache<>(5);
		lru.put(1, "11");
		lru.put(2, "12");
		lru.put(3, "13");
		lru.put(4, "14");
		lru.put(5, "15");
		System.out.println("1====="+lru.toString());
		lru.put(6, "66");
		System.out.println("2====="+lru.toString());
		lru.get(2);
		System.out.println("3====="+lru.toString());
		lru.put(7, "77");
		System.out.println("4====="+lru.toString());
		lru.get(4);
		System.out.println("5====="+lru.toString());
		lru.put(2, "22");
		System.out.println("6====="+lru.toString());
	}

}
