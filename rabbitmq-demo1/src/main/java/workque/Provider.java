package workque;

import com.rabbitmq.client.BasicProperties;
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

        channel.queueDeclare("workque", false, false, false, null);

        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", "workque", null, ("work - " + i).getBytes());
        }

        RabbitmqUtils.closeConnectionAndChannel(channel, connection);
    }
}
