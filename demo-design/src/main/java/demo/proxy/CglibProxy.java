package demo.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	private static CglibProxy cglibProxy = new CglibProxy();

	private Object target;// ҵ������󣬹��������н���������ҵ�񷽷�����

	public CglibProxy() {
		super();
	}

	public static CglibProxy getInstance() {
		return cglibProxy;
	}

	public Object getProxy(Object target) {
		this.target = target; // ��ҵ�����ֵ
		// ������̬��������󲢷���  
		return Enhancer.create(this.target.getClass(), this);
	}

	@Override
	public Object intercept(Object obj, Method arg1, Object[] arg2, MethodProxy proxy) throws Throwable {
		Object result = null;
		try {
			before();
			result = proxy.invokeSuper(obj, arg2);
			after();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void before() {
		System.out.println("[cglib] Come to someone.");
	}

	public void after() {
		System.out.println("[cglib] Back to his own corner.");
	}

}
