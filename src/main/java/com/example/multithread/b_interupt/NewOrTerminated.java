package com.example.multithread.b_interupt;

/**
 * @author lancx
 */
public class NewOrTerminated extends Thread {

    @Override
    public void run() {
        System.out.println("线程已启动");
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new NewOrTerminated();
        System.out.println("线程状态: " + thread.getState());

        // 状态为New 时中断线程
        thread.interrupt();
        System.out.println("线程中断标志: " + thread.isInterrupted());

        // 启动线程后将其置为terminated
        thread.start();
        thread.join();
        System.out.println("线程状态: " + thread.getState());

        // 状态为Terminated  时中断线程
        thread.interrupt();
        System.out.println("线程中断标志: " + thread.isInterrupted());
    }
}
