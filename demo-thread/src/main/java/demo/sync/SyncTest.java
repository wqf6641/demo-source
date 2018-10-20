package demo.sync;

//Synchronized������������Static Synchronized��������������
/**
 * ��������Ե�ǰ�����ǰʵ�������� ����������ʵ��
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
