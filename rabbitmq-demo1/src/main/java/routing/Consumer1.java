package routing;

import com.rabbitmq.client.*;
import util.RabbitmqUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";
        // 绑定channel
        String queue = channel.queueDeclare().getQueue();
        channel.exchangeDeclare(exchangeName, "direct");

        channel.queueBind(queue, exchangeName, "info");

        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("[consumer1]:" + new String(body));
            }
        });

    }
}
