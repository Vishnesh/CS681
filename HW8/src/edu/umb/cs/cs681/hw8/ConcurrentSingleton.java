package edu.umb.cs.cs681.hw8;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton{

    private ConcurrentSingleton(){};
    private static ConcurrentSingleton instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    public static ConcurrentSingleton getInstance(){
        lock.lock();
        try{
            if(instance==null){ instance = new ConcurrentSingleton(); }
            return instance;
        }finally{
            lock.unlock();
        }
    }

    private static class Tester extends Thread {
        public void run() {
            System.out.println(ConcurrentSingleton.getInstance());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Tester();
        Thread t2 = new Tester();
        Thread t3 = new Tester();
        Thread t4 = new Tester();
        Thread t5 = new Tester();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }
}
