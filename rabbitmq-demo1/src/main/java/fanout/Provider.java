package fanout;

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

        channel.exchangeDeclare("logs", "fanout");
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("logs", "", null, ("fanout message - " + i).getBytes());
        }

        RabbitmqUtils.closeConnectionAndChannel(channel, connection);
    }
}
