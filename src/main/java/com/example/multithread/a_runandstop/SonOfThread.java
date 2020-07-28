package com.example.multithread.a_runandstop;

/**
 *
 * @author
 */
public class SonOfThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Tread 子类" + i);
        }
    }
    public static void main(String[] args) {
        // 直接调用子类
        SonOfThread son = new SonOfThread();
        son.start();
    }
}
