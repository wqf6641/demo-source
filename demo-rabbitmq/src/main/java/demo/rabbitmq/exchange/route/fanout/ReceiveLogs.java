package demo.rabbitmq.exchange.route.fanout;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import demo.rabbitmq.factory.ConnFactory;

public class ReceiveLogs {
	public static final String EXCHANGE_NAME = "logs";

	public static void main(String[] args) {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		try {
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//声明交换机
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, EXCHANGE_NAME, "");// 绑定队列
			System.out.println("ReceiveLogs1 Waiting for messages");
			DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					System.out.println("ReceiveLogs1 Received '" + new String(body, "UTF-8") + "'");
				}
			};
			channel.basicConsume(queueName, true, defaultConsumer);// autoack=true
																	// 队列会自动删除
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
