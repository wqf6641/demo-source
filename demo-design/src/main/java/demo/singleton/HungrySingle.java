package demo.singleton;

/**
 * ����-����ģʽ
 * 
 * @author wengqf
 * �����ʱ��ʼ��
 */
public class HungrySingle {
	// �����ʱ���أ��̰߳�ȫ
	private static final HungrySingle INSTANCE = new HungrySingle();

	public static HungrySingle netInstance() {
		return INSTANCE;
	}
}
