package com.example.multithread.c_tools;

/**
 * 类说明：演示CountDownLatch用法，
 * 共5个初始化子线程，6个闭锁扣除点，扣除完毕后，主线程和业务线程才能继续执行
 */
public class CountDownLatch_3 {


    static java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(2);

    /**
     * 初始化线程
     */
    private static class InitThread implements Runnable {

        @Override
        public void run() {
            // 减一次
            System.out.println("Thread_" + Thread.currentThread().getId()
                    + "_name: InitThread   >>> countDown");
            latch.countDown();
            for (int i = 0; i < 2; i++) {
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " InitThread........continue do its work");
            }
        }
    }

    /**
     * 业务线程等待latch的计数器为0完成
     */
    private static class BusiThread_await implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + "_name: BusiThread_await   >>> await");
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("BusiThread_await" + " do business-----");
            }
        }
    }

    /**
     * 业务线程等待latch的计数器为0完成
     */
    private static class BusiThread implements Runnable {
        @Override
        public void run() {
//            SleepTools.ms(1);
            System.out.println("Thread_" + Thread.currentThread().getId()
                    + "_name: BusiThread_2 first  >>> countDown");
            latch.countDown();
//            SleepTools.ms(1);
            System.out.println("Thread_" + Thread.currentThread().getId()
                    + "_name: BusiThread_2 second   >>> countDown");
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //多次await测试

        new Thread(new BusiThread()).start();
        new Thread(new BusiThread_await()).start();

        for (int i = 0; i <= 3; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        latch.await();
        System.out.println("Main do ites work........");
    }

}

