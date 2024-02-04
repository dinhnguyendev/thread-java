package com.example.threadjava.thread2parallelly;

import java.util.Random;

public class ThreadRandom extends Thread {
    private final ShareDataRandom shareDataRandom;

    public ThreadRandom(ShareDataRandom shareDataRandom) {
        this.shareDataRandom = shareDataRandom;
    }


    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            synchronized (shareDataRandom){
                int rad = random.nextInt(100) + 1;
                this.shareDataRandom.setRandom(rad);
                System.out.println("Rad: " + rad);
                shareDataRandom.notifyAll();
                try {
                    shareDataRandom.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        synchronized (shareDataRandom){
            shareDataRandom.notifyAll();
        }
    }

}
