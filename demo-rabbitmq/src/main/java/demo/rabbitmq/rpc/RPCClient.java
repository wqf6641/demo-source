package demo.rabbitmq.rpc;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import demo.rabbitmq.factory.ConnFactory;

public class RPCClient {
	private Channel channel;
	private String requestQueueName = "rpc_queue";
	private String replyQueueName;
	private DefaultConsumer consumer;

	public RPCClient() throws IOException, TimeoutException {
		channel = ConnFactory.getChannel("10.7.35.228");

		replyQueueName = channel.queueDeclare().getQueue();

	}

	public void call(String message) throws IOException, InterruptedException {
		String corrID = UUID.randomUUID().toString();
		AMQP.BasicProperties props = new AMQP.BasicProperties().builder().correlationId(corrID).replyTo(replyQueueName)
				.build();
		channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
		consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				if (properties.getCorrelationId().equals(corrID)) {
					String response = new String(body, "UTF-8");
					System.out.println("RPCClient  Got '" + response + "'");
				}
			}
		};
		channel.basicConsume(replyQueueName, true, consumer);
	}

	public static void main(String[] args) throws Exception {
		RPCClient rpcClient = null;
		// String response;
		try {
			rpcClient = new RPCClient();
			System.out.println("RPCClient  Requesting fib(20)");
			rpcClient.call("20");
			// System.out.println("RPCClient Got '" + response + "'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// if (rpcClient != null) {
			// rpcClient.close();
			// }
		}
	}
}
