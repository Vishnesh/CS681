package edu.umb.cs.cs681.hw7;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

class File {
    private boolean changed = false;
    private ReentrantLock lock = new ReentrantLock();

    public void change() {
        lock.lock();
        try {
            changed = true;
        } finally {
            lock.unlock();
        }
    }

    public void save() {
        lock.lock();
        try {
            if (!changed)
                return;
            System.out.format("Saving at %d\n", new Date().getTime());
            changed = false;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        File f = new File();
        Editor e = new Editor(f);
        AutoSaver as = new AutoSaver(f);
        Thread t1 = new Thread(e);
        Thread t2 = new Thread(as);
        t1.start();
        t2.start();
        Thread.sleep(10000);
        e.setDone();
        as.setDone();
    }
}
