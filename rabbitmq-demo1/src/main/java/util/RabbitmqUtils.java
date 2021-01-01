package util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqUtils {

    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    static {
        connectionFactory.setHost("192.168.246.137");
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
    }


    public static Connection getConnection() {

        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void closeConnectionAndChannel(Channel channel, Connection connection) {

        try {
            if (null != channel)
                channel.close();
            if (null != connection)
                connection.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
