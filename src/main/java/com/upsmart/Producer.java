package com.upsmart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;

/**
 * @author Norman
 * Created by upsmart on 17-1-2.
 */

public class Producer implements Runnable
{
    private static final String QUEUE_NAME = "hello_queue";
    private static ConnectionFactory FACTORY = null;
    private static String MESSAGE = "Hello World!";
    private Connection connection = null;
    private Channel channel = null;

    public Producer() {
        if (FACTORY == null) {
            FACTORY = new ConnectionFactory();
        }
        FACTORY.setHost("192.168.199.107");
//        FACTORY.setHost("127.0.0.1");
        FACTORY.setPort(5672);
//        FACTORY.setUsername("guest");
        FACTORY.setUsername("user1");
        FACTORY.setPassword("123456");

    }

//    private static int count = 0;
//    Logger logger = LoggerFactory.getLogger(Producer.class);

    public void run() {
//        ConnectionFactory FACTORY = new ConnectionFactory();
//        FACTORY.setHost("127.0.0.1");
//        FACTORY.setHost("192.168.199.101");

//        FACTORY.setPort(5672);
//        FACTORY.setUsername("guest");
//        FACTORY.setPassword("guest");
        try {
            connection =  FACTORY.newConnection();
            channel = connection.createChannel();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Random randomIntNumber = new Random();

        try {
            for (int i = 0; i < 10000; i++) {

//            while (true) {
//                connection = FACTORY.newConnection();
//                channel = connection.createChannel();

                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//                String MESSAGE = "Hello World!";
                Long ThreadID = Thread.currentThread().getId();
                MESSAGE = MESSAGE + " Thread ID: " + ThreadID.toString() + " Random Num: " + randomIntNumber.nextInt();
                channel.basicPublish("", QUEUE_NAME, null, MESSAGE.getBytes());
//                System.out.println("Sent '" + MESSAGE + "'");
//                System.out.println("");
                channel.close();
                connection.close();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
