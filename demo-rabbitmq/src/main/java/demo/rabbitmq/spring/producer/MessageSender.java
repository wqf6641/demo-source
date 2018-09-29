package demo.rabbitmq.spring.producer;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	// @Autowired按byType自动注入，而@Resource默认按 byName自动注入
	@Resource(name = "amqpTemplate")
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message) {
		System.out.println("=========发送消息开始=============消息：" + message.toString());
		amqpTemplate.convertAndSend("hello", message);
	}
}
