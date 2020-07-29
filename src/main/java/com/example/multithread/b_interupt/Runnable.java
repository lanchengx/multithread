package com.example.multithread.b_interupt;

/**
 * @author lancx
 */
public class Runnable extends Thread{
    @Override
    public void run() {
        while (true){

        }

    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Runnable();
        thread.start();
        System.out.println("线程状态: " + thread.getState());

        thread.interrupt();
        // sleep到thread线程被中断之后
        Thread.sleep(1000);
        System.out.println("线程中断标志: " + thread.isInterrupted());

        System.out.println("线程状态: " + thread.getState());
    }

}
