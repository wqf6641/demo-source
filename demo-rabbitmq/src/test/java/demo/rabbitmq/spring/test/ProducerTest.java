package demo.rabbitmq.spring.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.rabbitmq.spring.producer.MessageSender;

public class ProducerTest {
	private ApplicationContext context = null;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("ApplicationContext-producer.xml");
	}

	@Test
	public void should_send_a_amq_message() throws Exception {
		MessageSender messageSender = (MessageSender) context.getBean("messageSender");
		for (int i = 0; i < 100; i++) {
			messageSender.sendMessage("Hello, I am amq sender:" + i);
			Thread.sleep(1000);
		}
		System.in.read();
	}
}
