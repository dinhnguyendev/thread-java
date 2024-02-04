package com.example.threadjava.thread3parallell;

import lombok.Data;

@Data
public class Thread2 extends Thread {
    private final ShareData shareData;

    public Thread2(ShareData shareData) {
        this.shareData = shareData;
    }

    public void run() {

        while (this.shareData.checkAvailable()) {
            synchronized (shareData) {
                shareData.notifyAll();
                while (shareData.getIndex() != 2) {
                    try {
                        shareData.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                int rad = this.shareData.getRad();
                if (rad % 3 == 0) {
                    rad *= rad;
                    System.out.println(" T2 squared >>>" + rad);
                }
                shareData.setIndex(1);
            }
        }
        synchronized (shareData){
            shareData.notifyAll();
        }
    }

}
