package demo.integer;

import java.lang.reflect.Field;

public class IntegerTest {

	public static void main(String[] args) {
		Integer a = 1, b = 2;
		System.out.println("a=" + a + ",b=" + b);//a=1,b=2
		swap(a, b);
		System.out.println("a=" + a + ",b=" + b);//a=2,b=1
	}

	private static void swap(Integer a, Integer b) {
		Field f = null;
		try {
			f = Integer.class.getDeclaredField("value");
			f.setAccessible(true);
			f.set(a, 2);
			f.set(b, new Integer(1));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			if (f != null) {
				f.setAccessible(false);
			}
		}
	}
}
