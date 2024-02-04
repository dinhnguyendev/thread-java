package com.example.threadjava.thread3parallell;


import lombok.Data;

@Data
public class Thread3 extends Thread {
    private final ShareData shareData;

    public Thread3(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        while (this.shareData.checkAvailable()) {
            synchronized (shareData) {
                shareData.notifyAll();
                while (shareData.getIndex() != 3) {
                    try {
                        shareData.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                int rad = this.shareData.getRad();
                if (rad % 2 == 0) {
                    String message = rad % 4 == 0 ? "device for 4" : "does not device for 4";
                    System.out.println("T3 >>>" + message);
                } else {
                    System.out.println("T3 >>>  So le");
                }
                shareData.setIndex(1);
            }
        }
        synchronized (shareData){
            shareData.notifyAll();
        }
    }
}
