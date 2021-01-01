package topic;

import com.rabbitmq.client.*;
import util.RabbitmqUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {

        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();

        String queue = channel.queueDeclare().getQueue();
        String routingKey = "user.*";
        channel.exchangeDeclare("topics", "topic");
        channel.queueBind(queue, "topics", routingKey);

        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer1 : " + new String(body));
            }
        });
    }
}
