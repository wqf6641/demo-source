package demo.rabbitmq.exchange.route.topic;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import demo.rabbitmq.factory.ConnFactory;

public class RoutingTopicReceive {
	public static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] args) {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		try {
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");// 声明交换机  生产方已声明 此处可声明 可不声明
			// 获取匿名队列名称
			String queueName = channel.queueDeclare().getQueue();
			//路由关键字
	        String[] routingKeys = new String[]{"*.orange.*"};
			// 根据路由绑定队列
			for (String routingKey : routingKeys) {
				channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
				System.out.println("RoutingTopicReceive exchange:" + EXCHANGE_NAME + ", queue:" + queueName
						+ ", BindRoutingKey:" + routingKey);
			}
			System.out.println("RoutingReceiveError Waiting for messages");
			DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
				//消息派发
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					System.out.println("RoutingTopicReceive Received :'" + envelope.getRoutingKey() + "|"
							+ new String(body, "UTF-8") + "'");
				}
			};
			channel.basicConsume(queueName, true, defaultConsumer);// autoack=true
																	// 队列会自动删除
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
