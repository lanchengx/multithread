package com.example.multithread.b_interupt;

public class WaitingOrTimeWaiting extends Thread {

    @Override
    public void run() {
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("i am waiting but facing interruptexception now");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new WaitingOrTimeWaiting();
        thread.start();

        Thread.sleep(500);
        System.out.println("线程状态: " + thread.getState());
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println("线程中断标志: " +thread.isInterrupted());

    }
}
