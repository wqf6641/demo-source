package demo.proxy;

public class CglibProxyTest {
	public static void main(String[] args) {
		CarFactory  caf = (CarFactory) CglibProxy.getInstance().getProxy(new CarCompany());	
		caf.scCar();
		RunCarCompany rc = (RunCarCompany) CglibProxy.getInstance().getProxy(new RunCarCompany());	
		rc.scCar();
	}
}
