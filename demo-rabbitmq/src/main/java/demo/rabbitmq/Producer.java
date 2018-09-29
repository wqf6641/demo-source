package demo.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Producer {

	public static final String QUEUE_NAME = "fristRabbitmq.test";

	public static void main(String[] args) throws IOException, TimeoutException {
		// �������ӹ���
		ConnectionFactory fac = new ConnectionFactory();
		fac.setHost("10.7.35.228");
		// fac.setPort(5672);// {tcp_listeners, [5672]},
		// ����һ������
		Connection conn = fac.newConnection();
		// ����һ��ͨ��
		Channel channel = conn.createChannel();
		// ��������
		// queueDeclare
		// ��һ��������ʾ�������ơ�
		// �ڶ�������Ϊ�Ƿ�־û���true��ʾ�ǣ����н��ڷ���������ʱ���棩��
		// ����������Ϊ�Ƿ��Ƕ�ռ���У������߿���ʹ�õ�˽�ж��У��Ͽ����Զ�ɾ������
		// ���ĸ�����Ϊ�����������߿ͻ������ӶϿ�ʱ�Ƿ��Զ�ɾ�����С����������Ϊ���е���������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// ������Ϣ
		String message = "Hello RabbitMQ ";
		// basicPublish��һ������Ϊ���������ơ��ڶ�������Ϊ����ӳ���·��key������������Ϊ��Ϣ���������ԡ����ĸ�����Ϊ������Ϣ������
		channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
		System.out.println("Producer Send +'" + message + "'");
		// �ر�ͨ��������
		channel.close();
		conn.close();
	}
}
