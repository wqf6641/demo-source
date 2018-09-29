package demo.rabbitmq.spring.producer;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	// @Autowired��byType�Զ�ע�룬��@ResourceĬ�ϰ� byName�Զ�ע��
	@Resource(name = "amqpTemplate")
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message) {
		System.out.println("=========������Ϣ��ʼ=============��Ϣ��" + message.toString());
		amqpTemplate.convertAndSend("hello", message);
	}
}
