package routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.RabbitmqUtils;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";
        String queue = channel.queueDeclare().getQueue();
        channel.exchangeDeclare(exchangeName, "direct");

        channel.queueBind(queue, exchangeName, "info");
        channel.queueBind(queue, exchangeName, "error");
        channel.queueBind(queue, exchangeName, "warning");

        channel.basicConsume(queue, true, (s, delivery) -> {
            System.out.println("[consumer2]:" + new String(delivery.getBody()));
        }, s -> {

        });
    }
}
