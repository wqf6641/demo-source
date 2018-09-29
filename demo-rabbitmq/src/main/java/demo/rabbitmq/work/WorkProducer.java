package demo.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import demo.rabbitmq.factory.ConnFactory;

public class WorkProducer {

	public static final String QUEUE_NAME = "sencondRabbitmq.test";

	public static void main(String[] args) throws IOException, TimeoutException {
		// ����һ��ͨ��
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		// ��������
		// queueDeclare
		// ��һ��������ʾ�������ơ�
		// �ڶ�������Ϊ�Ƿ�־û���true��ʾ�ǣ����н��ڷ���������ʱ���棩��
		// ����������Ϊ�Ƿ��Ƕ�ռ���У������߿���ʹ�õ�˽�ж��У��Ͽ����Զ�ɾ������
		// ���ĸ�����Ϊ�����������߿ͻ������ӶϿ�ʱ�Ƿ��Զ�ɾ�����С����������Ϊ���е���������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// ������Ϣ
		for (int i = 0; i < 100; i++) {
			String message = "Hello RabbitMQ " + i;
			// basicPublish��һ������Ϊ���������ơ��ڶ�������Ϊ����ӳ���·��key������������Ϊ��Ϣ���������ԡ����ĸ�����Ϊ������Ϣ������
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
			System.out.println("Producer Send +'" + message + "'");
		}
		// �ر�ͨ��������
		ConnFactory.close();
	}
}
