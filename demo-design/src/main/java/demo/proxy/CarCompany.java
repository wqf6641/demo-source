package demo.proxy;

public class CarCompany implements CarFactory {

	@Override
	public void scCar() {
		System.out.println("汽车公司 生产汽车！！！");
	}

}
