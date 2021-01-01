package workque;

import com.rabbitmq.client.*;
import util.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1); // 每次只能消费队列中的一个消息
        channel.queueDeclare("workque", false, false, false, null);
        channel.basicConsume("workque", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("[consumer-2]:" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);

            }

        });
    }
}
