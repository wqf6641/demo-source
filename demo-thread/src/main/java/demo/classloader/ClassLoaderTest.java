package demo.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * ������������Ψһ��
 * @author wengqf
 * ���������Ȼֻ����ʵ����ļ��ض��������Ƕ�������һ���࣬����Ҫ�ɼ��������������������౾��ͬȷ������Java������е�Ψһ��
 */
public class ClassLoaderTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// �Զ��������
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
