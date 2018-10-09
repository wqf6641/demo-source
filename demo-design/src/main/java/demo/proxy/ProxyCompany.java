package demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ����˾ jdkԭ��
 * 
 * @author wengqf ��̬���� ��ѭ����ԭ�� ������չ���ţ������޸Ĺر� �׶ˣ������롰������Ķ�����Ҫʵ�ֽӿڡ�
 */
public class ProxyCompany implements InvocationHandler {

	private Object factory;// �������

	
	public ProxyCompany() {
		super();
	}

	public ProxyCompany(Object factory) {
		super();
		this.factory = factory;
	}

	public void setFactory(Object factory) {
		this.factory = factory;
	}

	public Object getProxy() {
		Object obj = Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(),
				this);
		return obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// ǰ����ǿ
		System.out.println("����ʼ...");// ����˼�� AOP ������
		Object invoke = method.invoke(factory, args);
		System.out.println("�������...");
		// ������ǿ
		return invoke;
	}

}
