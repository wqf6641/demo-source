package demo.rabbitmq.exchange.route.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import demo.rabbitmq.factory.ConnFactory;

/**
 * �㲥ģʽ
 * @author wengqf
 *
 */
public class EditLog {
	public static final String EXCHANGE_NAME = "logs";

	public static void main(String[] args) {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		try {
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");// fanout���������ߵõ�ͬ������Ϣ�㲥ģʽ
			// �����߷�����Ϣ��������
			for (int i = 0; i < 100; i++) {
				String message = "Hello RabbitMQ " + i;
				// basicPublish��һ������Ϊ���������ơ��ڶ�������Ϊ����ӳ���·��key������������Ϊ��Ϣ���������ԡ����ĸ�����Ϊ������Ϣ������
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
