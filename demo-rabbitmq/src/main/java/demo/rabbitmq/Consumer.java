package demo.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Consumer {
	public static final String QUEUE_NAME = "fristRabbitmq.test";

	public static void main(String[] args) throws IOException, TimeoutException {
		// �������ӹ���
		ConnectionFactory fac = new ConnectionFactory();
		fac.setHost("10.7.35.228");
//		fac.setPort(5672);// {tcp_listeners, [5672]},
		// ����һ������
		Connection conn = fac.newConnection();
		// ����һ��ͨ��
		Channel channel = conn.createChannel();
		// ����Ҫ��ע�Ķ���
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println("Customer Waiting Received messages");
		// DefaultConsumer��ʵ����Consumer�ӿڣ�ͨ������һ��Ƶ����
		// ���߷�����������Ҫ�Ǹ�Ƶ������Ϣ�����Ƶ��������Ϣ���ͻ�ִ�лص�����handleDelivery
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Customer Received :'" + message + "'");
			}
		};
		// �Զ��ظ�����Ӧ�� -- RabbitMQ�е���Ϣȷ�ϻ���
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
