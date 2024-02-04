package com.example.threadjava.thread3parallell;


import lombok.Data;

import java.util.Random;

@Data
public class Thread1 extends Thread {

    private final ShareData shareData;

    public Thread1(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        while (this.shareData.checkAvailable()) {
            synchronized (shareData) {
                int rad = random.nextInt(99) + 1;
                this.shareData.setRad(rad);
                this.shareData.plus(rad);
                System.out.println("T1 Random >>>" + rad);
                System.out.println("T1 total >>>" + this.shareData.getTotal());
                if (rad % 3 == 0) {
                    this.shareData.setIndex(2);
                } else {
                    this.shareData.setIndex(3);
                }
                shareData.notifyAll();
                try {
                    shareData.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        synchronized (shareData) {
            shareData.notifyAll();
        }

    }
}
