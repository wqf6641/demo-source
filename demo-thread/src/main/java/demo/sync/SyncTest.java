package demo.sync;

//Synchronized（对象锁）和Static Synchronized（类锁）的区别
/**
 * 对象锁针对当前对象或当前实例，类锁 针对所有类的实例
 * @author Administrator
 *
 */
public class SyncTest {

	int count = 0;
	static int s_count = 0;

//	private Object obj = new Object();
	public synchronized int get(int i) {
		count += i;
		return count;
	}
	
	public static synchronized int getStatic(int i) {
		s_count += i;
		return s_count;
	}
	
	public static void main(String[] args) {
//		SyncTest sy = new SyncTest();
//		for (int i = 0; i < 10; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					System.out.println(sy.get(1));
//				}
//			}).start();
//		}
		
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					SyncTest sy = new SyncTest();
					System.out.println(sy + "|" + sy.getStatic(1));
				}
			}).start();
		}
	}
}
