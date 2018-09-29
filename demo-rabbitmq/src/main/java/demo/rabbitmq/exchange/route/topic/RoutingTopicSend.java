package demo.rabbitmq.exchange.route.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import demo.rabbitmq.factory.ConnFactory;

/**
 * ģ��ƥ��ģʽ
 * 
 * @author wengqf * ���������һ���� #���������0���߸���Ĵ�
 */
public class RoutingTopicSend {
	public static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] args) {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		try {
			// ����������
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");// һ����������
			// �����߷�����Ϣ��������
			// �����͵���Ϣ
			String[] routingKeys = new String[] { "quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
					"lazy.brown.fox", "quick.brown.fox", "quick.orange.male.rabbit", "lazy.orange.male.rabbit" };
			for (String routingKey : routingKeys) {
				String message = "RoutingTopicSend Send the message :" + routingKey;
				// basicPublish��һ������Ϊ���������ơ��ڶ�������Ϊ����ӳ���·��key������������Ϊ��Ϣ���������ԡ����ĸ�����Ϊ������Ϣ������
				channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,
						message.getBytes("UTF-8"));
				System.out.println("RoutingTopicSend Send" + routingKey + "':'" + message);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnFactory.close();
	}
}
