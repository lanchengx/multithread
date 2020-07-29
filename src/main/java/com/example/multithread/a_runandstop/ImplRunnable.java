package com.example.multithread.a_runandstop;

public class ImplRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("implements runnable " + i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ImplRunnable());
        thread.start();
    }
}
