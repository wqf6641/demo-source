package demo.rabbitmq.exchange.route.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import demo.rabbitmq.factory.ConnFactory;

/**
 * ��ȫƥ��ģʽ
 * 
 * @author wengqf
 *
 */
public class RoutingSend {
	public static final String EXCHANGE_NAME = "direct_logs";
	// ·�ɹؼ���
	private static final String[] routingKeys = new String[] { "info", "warning", "error" };

	public static void main(String[] args) {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		try {
			// ����������
			channel.exchangeDeclare(EXCHANGE_NAME, "direct");//һ����������
			// �����߷�����Ϣ��������
			// ������Ϣ
			for (String routingKey : routingKeys) {
				String message = "RoutingSendDirect Send the message level:" + routingKey;
				for (int i = 0; i < 10; i++) {
					message += i;
					// basicPublish��һ������Ϊ���������ơ��ڶ�������Ϊ����ӳ���·��key������������Ϊ��Ϣ���������ԡ����ĸ�����Ϊ������Ϣ������
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
