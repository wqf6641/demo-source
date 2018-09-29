package demo.rabbitmq.exchange.route.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import demo.rabbitmq.factory.ConnFactory;

/**
 * 广播模式
 * @author wengqf
 *
 */
public class EditLog {
	public static final String EXCHANGE_NAME = "logs";

	public static void main(String[] args) {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		try {
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");// fanout所有消费者得到同样的信息广播模式
			// 生产者发送消息到交换机
			for (int i = 0; i < 100; i++) {
				String message = "Hello RabbitMQ " + i;
				// basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、第三个参数为消息的其他属性、第四个参数为发送信息的主体
				channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN,
						message.getBytes("UTF-8"));
				System.out.println("EditLog Send +'" + message + "'");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnFactory.close();
	}
}
