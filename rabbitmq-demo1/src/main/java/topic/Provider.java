package topic;

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

        channel.exchangeDeclare("topics", "topic");
        String routingKey = "user.save.delete";

        channel.basicPublish("topics", routingKey, true, null, ("routingKey:[" + routingKey + "]").getBytes());

        RabbitmqUtils.closeConnectionAndChannel(channel, connection);
    }
}
