package demo.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import demo.rabbitmq.factory.ConnFactory;

public class Worker2 {
	public static final String QUEUE_NAME = "sencondRabbitmq.test";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建一个通道
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		// 声明要关注的队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 每次从队列获取的数量
		channel.basicQos(1);
		System.out.println("Customer Waiting Received messages");
		// DefaultConsumer类实现了Consumer接口，通过传入一个频道，
		// 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("worker2 Received :'" + message + "'");
				try {
					// throw new Exception();
					doWork(message);
				} catch (Exception e) {
					channel.abort();
				} finally {
					System.out.println("Worker2 Done!!!");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		boolean autoAck = false;
		// 消息消费完成确认
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);
	}

	public static void doWork(String task) {
		try {
			Thread.sleep(1000); // 暂停1秒钟
		} catch (InterruptedException _ignored) {
			Thread.currentThread().interrupt();
		}
	}
}
