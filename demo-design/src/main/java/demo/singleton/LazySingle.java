package demo.singleton;

/**
 * ����-����ģʽ
 * 
 * @author wengqf
 * ������ص�ʱ�򲻱���ʼ��
 *
 */
public class LazySingle {

	private static LazySingle instance = null;

	// ������֤�̰߳�ȫ ˫���ж�
	public static LazySingle newInstance() {
		if (instance == null) {
			synchronized (LazySingle.class) {
				if (instance == null) {
					instance = new LazySingle();
				}
			}
		}
		return instance;
	}
}
