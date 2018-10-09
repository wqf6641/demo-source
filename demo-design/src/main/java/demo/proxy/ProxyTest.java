package demo.proxy;

public class ProxyTest {

	public static void main(String[] args) {

		// 被代理对象
		CarFactory fac = new CarCompany();
		BusFactory busFac = new BusCompany();
		// 代理对象
		ProxyCompany proxy = new ProxyCompany();
		//代理汽车
		proxy.setFactory(fac);
		CarFactory proxyCarFac = (CarFactory) proxy.getProxy();
		proxyCarFac.scCar();

		//代理公共汽车
		proxy.setFactory(busFac);
		BusFactory proxyBusFac = (BusFactory) proxy.getProxy();
		proxyBusFac.scBus();

	}
}
