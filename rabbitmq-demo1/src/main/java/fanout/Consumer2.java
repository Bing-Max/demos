package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.RabbitmqUtils;

import java.io.IOException;

public class Consumer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        final Channel channel = connection.createChannel();

//        String queue = channel.queueDeclare().getQueue();
//        channel.exchangeBind(queue, "logs", "fanout");

        channel.exchangeDeclare("logs", "fanout");
        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue, "logs", "");

        channel.basicConsume(queue, false, (s, delivery) -> {
            System.out.println("[consumer2]:" + new String(delivery.getBody()));
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }, s -> {

        });
    }
}
