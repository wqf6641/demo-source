package demo.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与类的唯一性
 * @author wengqf
 * 类加载器虽然只用于实现类的加载动作，但是对于任意一个类，都需要由加载它的类加载器和这个类本身共同确立其在Java虚拟机中的唯一性
 */
public class ClassLoaderTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// 自定义加载器
		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					System.out.println(fileName);
					InputStream in = getClass().getResourceAsStream(fileName);
					if (in == null) {
						return super.loadClass(fileName);
					}
					byte[] b = new byte[in.available()];
					in.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		};
		Object newInstance = ClassLoaderTest.class.getClassLoader().loadClass("demo.classloader.ClassLoaderTest").newInstance();
		System.out.println(newInstance.getClass());
		System.out.println(newInstance instanceof demo.classloader.ClassLoaderTest);
		
		Object newInstance2 = myLoader.loadClass("demo.classloader.ClassLoaderTest").newInstance();
		System.out.println(newInstance2.getClass());
		System.out.println(newInstance2 instanceof demo.classloader.ClassLoaderTest);

	}
}
