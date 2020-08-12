package com.example.multithread.c_tools.samaphore;

import java.util.concurrent.Semaphore;

public class Samaphore_1 {
    public static void main(String[] args) {
        //1、信号量为1时 相当于普通的锁  信号量大于1时 共享锁
        Output o = new Output();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> o.output()).start();
        }
    }
}

class Output {
    Semaphore semaphore = new Semaphore(1);

    public void output() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " start at " + System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " stop at " + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
