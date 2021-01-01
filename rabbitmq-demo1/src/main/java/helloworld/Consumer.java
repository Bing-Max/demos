package helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import util.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void consumeMsg() throws IOException, TimeoutException {
//        // 创建连接工厂对象
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//
//        connectionFactory.setHost("192.168.246.137");
//        connectionFactory.setVirtualHost("/ems");
//        connectionFactory.setPort(5672);
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("ems");
//
//        Connection connection = connectionFactory.newConnection();

        Connection connection = RabbitmqUtils.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", true, false, false, null);

        //参数1：队列名称
        //参数2：开启自动确认机制
        //参数3：Consumer 回调
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            /**
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println(new String(body));
            }
        });
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        consumeMsg();
    }
}
