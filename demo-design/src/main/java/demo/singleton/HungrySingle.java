package demo.singleton;

/**
 * 单例-饿汉模式
 * 
 * @author wengqf
 * 类加载时初始化
 */
public class HungrySingle {
	// 类加载时加载，线程安全
	private static final HungrySingle INSTANCE = new HungrySingle();

	public static HungrySingle netInstance() {
		return INSTANCE;
	}
}
