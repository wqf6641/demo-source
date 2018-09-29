package demo.rabbitmq.exchange.route.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import demo.rabbitmq.factory.ConnFactory;

/**
 * 完全匹配模式
 * 
 * @author wengqf
 *
 */
public class RoutingSend {
	public static final String EXCHANGE_NAME = "direct_logs";
	// 路由关键字
	private static final String[] routingKeys = new String[] { "info", "warning", "error" };

	public static void main(String[] args) {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		try {
			// 声明交换机
			channel.exchangeDeclare(EXCHANGE_NAME, "direct");//一方声明即可
			// 生产者发送消息到交换机
			// 发送信息
			for (String routingKey : routingKeys) {
				String message = "RoutingSendDirect Send the message level:" + routingKey;
				for (int i = 0; i < 10; i++) {
					message += i;
					// basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、第三个参数为消息的其他属性、第四个参数为发送信息的主体
					channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,
							message.getBytes("UTF-8"));
					System.out.println("RoutingSendDirect Send" + routingKey + "':'" + message);
					Thread.sleep(100);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnFactory.close();
	}
}
