package demo.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import demo.rabbitmq.factory.ConnFactory;

/**
 * channel.basicQos(1);��֤һ��ֻ�ַ�һ�� ��autoAck�Ƿ��Զ��ظ���
 * ���Ϊtrue�Ļ���ÿ��������ֻҪ������Ϣ�ͻ���ڴ���ɾ����
 * ��ô��������߳����쳣�˳�����ô���޷���ȡ���ݣ����ǵ�Ȼ�ǲ�ϣ�����������������
 * ���Բ�ȥ�ֶ��ظ���ÿ���������յ���������ϢȻ����֪ͨ�����ߡ����Ӷ�����ɾ��������Ϣ��
 * ����������쳣�˳�������������������ߣ���ô�ͻ�Ѷ����е���Ϣ���͸����������ߣ����û�У�������������ʱ���ٴη��͡�
 * @author wengqf
 *
 */
public class Worker1 {
	public static final String QUEUE_NAME = "sencondRabbitmq.test";

	public static void main(String[] args) throws IOException, TimeoutException {
		// ����һ��ͨ��
		Channel channel =  ConnFactory.getChannel("10.7.35.228");
		// ����Ҫ��ע�Ķ���
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// ÿ�δӶ��л�ȡ������
		channel.basicQos(1);
		System.out.println("Customer Waiting Received messages");
		// DefaultConsumer��ʵ����Consumer�ӿڣ�ͨ������һ��Ƶ����
		// ���߷�����������Ҫ�Ǹ�Ƶ������Ϣ�����Ƶ��������Ϣ���ͻ�ִ�лص�����handleDelivery
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("worker1 Received :'" + message + "'");
				try {
//					throw new Exception();
					 doWork(message);
				} catch (Exception e) {
					channel.abort();
				} finally {
					System.out.println("Worker1 Done!!!");
					channel.basicAck(envelope.getDeliveryTag(), false);//ȷ����Ϣ
				}
			}
		};
		boolean autoAck = false;
		// ��Ϣ�������ȷ��
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);
	}

	public static void doWork(String task) {
		try {
			Thread.sleep(1000); // ��ͣ1����
		} catch (InterruptedException _ignored) {
			Thread.currentThread().interrupt();
		}
	}
}
