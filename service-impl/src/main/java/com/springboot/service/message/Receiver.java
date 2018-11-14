package com.springboot.service.message;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@RabbitListener(queues = "topic.queue")
public class Receiver {

    @RabbitHandler
    public void process(String str, Channel channel, Message message) {
        try {
            System.out.println("HelloReceiver收到  : " + str + "收到时间" + new Date());
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            //重新回到消息头
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
