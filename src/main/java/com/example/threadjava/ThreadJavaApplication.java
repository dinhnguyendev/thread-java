package com.example.threadjava;

import com.example.threadjava.thread2parallelly.ShareDataRandom;
import com.example.threadjava.thread2parallelly.ThreadRandom;
import com.example.threadjava.thread2parallelly.ThreadSquare;
import com.example.threadjava.thread3parallell.ShareData;
import com.example.threadjava.thread3parallell.Thread1;
import com.example.threadjava.thread3parallell.Thread2;
import com.example.threadjava.thread3parallell.Thread3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadJavaApplication {

    public static void main(String[] args) {


        ShareData shareData = new ShareData();
        Thread1 thread1 = new Thread1(shareData);
        Thread2 thread2 = new Thread2(shareData);
        Thread3 thread3 = new Thread3(shareData);
        thread1.start();
        thread2.start();
        thread3.start();

        SpringApplication.run(ThreadJavaApplication.class, args);
    }

}
