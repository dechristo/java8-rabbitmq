package hello;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


public class HelloPublisher {
    private static final String QUEUE_NAME = "hello-rabbitmq";

    public static void main(String[] args) throws  Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Look! There goes the rabbit!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [-->] Sent: '" + message + "'");
        }
    }
}
