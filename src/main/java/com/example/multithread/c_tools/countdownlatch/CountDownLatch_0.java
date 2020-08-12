package com.example.multithread.c_tools.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch_0 {
    /**
     * 初始化需要等待的 4 个事件
     */
    private static CountDownLatch latch = new CountDownLatch(4);

    public static void main(String[] args) throws InterruptedException {
        // 创建 4 个线程分别代表 4 个玩家
        new Thread(() -> {
            System.out.println("玩家 1 已就绪");
            latch.countDown();
        }).start();
        new Thread(() -> {
            System.out.println("玩家 2 已就绪");
            latch.countDown();
        }).start();
        new Thread(() -> {
            System.out.println("玩家 3 已就绪");
            latch.countDown();
        }).start();
        new Thread(() -> {
            System.out.println("玩家 4 已就绪");
            latch.countDown();
        }).start();

        // 所有玩家就绪前一直阻塞
        latch.await();
        System.out.println("所有玩家已就绪，请发牌");
    }

}
