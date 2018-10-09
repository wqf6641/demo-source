package demo.proxy;

public class ProxyTest {

	public static void main(String[] args) {

		// ���������
		CarFactory fac = new CarCompany();
		BusFactory busFac = new BusCompany();
		// �������
		ProxyCompany proxy = new ProxyCompany();
		//��������
		proxy.setFactory(fac);
		CarFactory proxyCarFac = (CarFactory) proxy.getProxy();
		proxyCarFac.scCar();

		//����������
		proxy.setFactory(busFac);
		BusFactory proxyBusFac = (BusFactory) proxy.getProxy();
		proxyBusFac.scBus();

	}
}
