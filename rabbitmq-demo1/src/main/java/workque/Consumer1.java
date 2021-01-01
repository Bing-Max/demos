package workque;

import com.rabbitmq.client.*;
import util.RabbitmqUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("workque", false, false, false, null);
        channel.basicConsume("workque", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("[consumer-1]:" + new String(body));
                // 参数2： multiple 一次确认多个消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
