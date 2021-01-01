package routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import util.RabbitmqUtils;

import java.io.IOException;

public class Provider {
    @Test
    public void produceMsg() throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";
        String routingKey = "warning";

        // channel 绑定交换机 Name && type
        channel.exchangeDeclare(exchangeName, "direct");
        channel.basicPublish(exchangeName, routingKey, null, ("directMessage: [" + routingKey + "]").getBytes());

        RabbitmqUtils.closeConnectionAndChannel(channel, connection);
    }
}
