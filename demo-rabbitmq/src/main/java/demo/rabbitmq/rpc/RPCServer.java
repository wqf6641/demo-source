package demo.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import demo.rabbitmq.factory.ConnFactory;

public class RPCServer {
	private static final String RPC_QUEUE_NAME = "rpc_queue";

	private static int fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fib(n - 1) + fib(n - 1);
	}

	public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
		Channel channel = ConnFactory.getChannel("10.7.35.228");
		channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
						.correlationId(properties.getCorrelationId()).build();
				String message = new String(body, "UTF-8");
				int n = Integer.parseInt(message);

				System.out.println("RPCServer fib(" + message + ")");
				String response = "" + fib(n);
				channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes());
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
		System.out.println("RPCServer Awating RPC request");
	}
}
