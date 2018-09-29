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
		// 创建连接工厂
		ConnectionFactory fac = new ConnectionFactory();
		fac.setHost("10.7.35.228");
		// fac.setPort(5672);// {tcp_listeners, [5672]},
		// 创建一个连接
		Connection conn = fac.newConnection();
		// 创建一个通道
		Channel channel = conn.createChannel();
		// 声明队列
		// queueDeclare
		// 第一个参数表示队列名称、
		// 第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
		// 第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、
		// 第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 发送消息
		String message = "Hello RabbitMQ ";
		// basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、第三个参数为消息的其他属性、第四个参数为发送信息的主体
		channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
		System.out.println("Producer Send +'" + message + "'");
		// 关闭通道和连接
		channel.close();
		conn.close();
	}
}
