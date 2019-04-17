package hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class HelloConsumer {

    private final static String QUEUE_NAME = "hello-rabbitmq";

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [...] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliver = (consumerTag, delivery) ->  {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [<--] Received: '" + message + "'");
        };

        channel.basicConsume(QUEUE_NAME, true, deliver, consumerTag -> {});
    }
}
