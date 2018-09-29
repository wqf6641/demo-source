package demo.rabbitmq.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注意amqp-client与spring-rabbit版本兼容问题
 * @author wengqf
 *
 */
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
