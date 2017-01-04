package com.upsmart;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 16; i++) {
            es.execute(new Producer());

        }
//                    new Thread(new Producer()).start();
//        ExecutorService es1 = Executors.newFixedThreadPool(1);
//        for (int i = 0; i < 1; i++) {
//            es1.execute(new Consummer());
//
//        }

        new Thread(new Consummer()).start();
    }
}
