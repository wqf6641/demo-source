package demo.proxy;

public class BusCompany implements BusFactory {

	@Override
	public void scBus() {
		System.out.println("公共汽车公司 生产公共汽车！！！");
	}

}
