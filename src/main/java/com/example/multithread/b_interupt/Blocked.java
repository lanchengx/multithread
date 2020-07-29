package com.example.multithread.b_interupt;

public class Blocked extends  Thread{

    public synchronized static void doSomething(){
        while(true){
            //do something
        }
    }
    @Override
    public void run(){
        doSomething();
    }


    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Blocked();
        thread1.start();

        Thread thread2 = new Blocked();
        thread2.start();

        Thread.sleep(1000);
        System.out.println("线程状态: " + thread1.getState());
        System.out.println("线程状态: " + thread2.getState());

        thread2.interrupt();
        System.out.println("线程状态: " + thread2.isInterrupted());
        System.out.println("线程状态: " + thread2.getState());
    }
}
