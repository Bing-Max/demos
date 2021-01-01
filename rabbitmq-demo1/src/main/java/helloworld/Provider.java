package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import util.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    @Test
    public void produceMsg() throws IOException, TimeoutException {
//        // 创建连接工厂对象
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//
//        // 设置rabbitmq主机
//        connectionFactory.setHost("192.168.246.137");
//        // 设置端口,默认的端口为5672
//        connectionFactory.setPort(5672);
//        // 设置虚拟主机
//        connectionFactory.setVirtualHost("/ems");
//        // 设置用户名密码
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("ems");
//
//        // 创建连接
//        Connection connection = connectionFactory.newConnection();

        Connection connection = RabbitmqUtils.getConnection();
        // 获取连接中的channel
        Channel channel = connection.createChannel();

        //
        // 参数1：队列名称
        // 参数2：队列是否持久化，true 持久化， false 不持久化
        // 参数3：是否独占队列， true 独占队列， false 不独占队列
        // 参数4：消息传递完成后是否自动删除队列， true 自动删除， false 不自动删除
        // 参数5：额外附加参数
        channel.queueDeclare("hello", true, false, false, null);

        // 交换机名称、队列名称、额外参数、具体的消息内容
        channel.basicPublish("", "hello", null, "hellow rabbitmq".getBytes());

        RabbitmqUtils.closeConnectionAndChannel(channel, connection);
    }
}
