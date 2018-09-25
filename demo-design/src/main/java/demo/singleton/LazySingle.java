package demo.singleton;

/**
 * 单例-懒汉模式
 * 
 * @author wengqf
 * 在类加载的时候不被初始化
 *
 */
public class LazySingle {

	private static LazySingle instance = null;

	// 加锁保证线程安全 双重判断
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
