package demo.rabbitmq.spring.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageReceiver implements MessageListener {
	
	
	@Override
	public void onMessage(Message message) {
		String str = "";
		try {
			str = new String(message.getBody(), "UTF-8");
			System.out.println("=====获取消息" + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
