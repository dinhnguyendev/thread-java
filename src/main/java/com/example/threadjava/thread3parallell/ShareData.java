package com.example.threadjava.thread3parallell;


import lombok.Data;

@Data
public class ShareData {
    int rad;
    int total;
    int index = 1;

    public ShareData() {
        this.total = 0;
    }

    public synchronized void plus(int value) {
        this.total += value;
    }

    public boolean checkAvailable() {
        return this.total < 200;
    }
}
