package edu.umb.cs.cs681.hw7;

import java.util.concurrent.locks.ReentrantLock;

class AutoSaver implements Runnable {

    public AutoSaver(File f) {
        aFile = f;
    }

    private boolean done = false;
    private File aFile;
    private ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
        while (true) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Stopping an autosaver");
                    return;
                }
            } finally {
                lock.unlock();
            }
            aFile.save();
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
                return;
			}
        }
	}

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }
    
}
