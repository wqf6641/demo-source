package demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理公司 jdk原生
 * 
 * @author wengqf 动态代理 遵循开闭原则 对外扩展开放，对内修改关闭 弊端：依赖与“被代理的对象需要实现接口”
 */
public class ProxyCompany implements InvocationHandler {

	private Object factory;// 代理对象

	
	public ProxyCompany() {
		super();
	}

	public ProxyCompany(Object factory) {
		super();
		this.factory = factory;//接收业务实现类对象参数
	}

	public void setFactory(Object factory) {
		this.factory = factory;
	}

	public Object getProxy() {
		//接收业务实现类对象参数
		//创建代理对象时，需要传递该业务类的类加载器（用来获取业务实现类的元数据，在包装方法是调用真正的业务方法）、接口、handler实现类
		Object obj = Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(),
				this);
		return obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 前置增强
		System.out.println("代理开始...");// 切面思想 AOP 事务处理
		Object invoke = method.invoke(factory, args);
		System.out.println("代理结束...");
		// 后置增强
		return invoke;
	}

}
