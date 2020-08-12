package com.example.multithread.c_tools;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier_0 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2);
        System.out.println("等待线程数：" + barrier.getNumberWaiting());
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(() -> {
            try {
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " is running 1!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(1);
        System.out.println("等待线程数：" + barrier.getNumberWaiting());
        executorService.execute(() -> {
            try {
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " is running 2!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
