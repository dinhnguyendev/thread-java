package com.example.threadjava.thread2parallelly;


import lombok.Data;

@Data
public class ThreadSquare extends Thread {

    private final ShareDataRandom shareDataRandom;

    public ThreadSquare(ShareDataRandom shareDataRandom) {
        this.shareDataRandom = shareDataRandom;
    }


    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            synchronized (shareDataRandom){
                shareDataRandom.notifyAll();
                try {
                    shareDataRandom.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int rad = this.shareDataRandom.getRandom();
                rad *= rad;
                System.out.println("PT: " + rad);
            }

        }
        synchronized (shareDataRandom){
            shareDataRandom.notifyAll();
        }
    }
}
