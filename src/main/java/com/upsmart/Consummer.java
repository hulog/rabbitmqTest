package com.upsmart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by upsmart on 17-1-2.
 */
public class Consummer implements Runnable {
    private final static String QUEUE_NAME = "hello_queue";
    private static ConnectionFactory factory = null;
    private Connection connection = null;
    private Channel channel = null;

        private static int count = 0;
    public Consummer() {
        if (factory == null) {
            factory = new ConnectionFactory();
        }
        factory.setHost("192.168.199.107");
        factory.setPort(5672);
        factory.setUsername("user1");
        factory.setPassword("123456");
    }

    public void run() {
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);

            while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
//                System.out.println("Received '" + message + "'");
//                count++;
//                System.out.println(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}