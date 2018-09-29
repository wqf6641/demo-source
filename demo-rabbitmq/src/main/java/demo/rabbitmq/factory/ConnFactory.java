package demo.rabbitmq.factory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnFactory {

	// 创建连接工厂
	private static ConnectionFactory connectionFactory = new ConnectionFactory();
	private static Connection conn = null;
	private static Channel channel = null;

	public static Channel getChannel(String hostName) {
		try {
			connectionFactory.setHost(hostName);
			// fac.setPort(5672);// {tcp_listeners, [5672]},
			// 创建一个连接
			if (conn == null) {
				conn = connectionFactory.newConnection();
			}
			// 创建一个通道
			channel = conn.createChannel();
			return channel;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 关闭通道和连接
	public static void close() {
		try {
			if (null != channel) {
				channel.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
