package demo.rabbitmq.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext ctx;
		try {
			ctx = new ClassPathXmlApplicationContext("ApplicationContext-consumer.xml");
			ctx.start();
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
