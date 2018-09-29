package demo.rabbitmq.exchange.route.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import demo.rabbitmq.factory.ConnFactory;

/**
 * 模糊匹配模式
 * 
 * @author wengqf * ：可以替代一个词 #：可以替代0或者更多的词
 */
public class RoutingTopicSend {
	public static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] args) {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		try {
			// 声明交换机
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");// 一方声明即可
			// 生产者发送消息到交换机
			// 待发送的消息
			String[] routingKeys = new String[] { "quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
					"lazy.brown.fox", "quick.brown.fox", "quick.orange.male.rabbit", "lazy.orange.male.rabbit" };
			for (String routingKey : routingKeys) {
				String message = "RoutingTopicSend Send the message :" + routingKey;
				// basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、第三个参数为消息的其他属性、第四个参数为发送信息的主体
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
