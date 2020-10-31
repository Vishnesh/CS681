package edu.umb.cs.cs681.hw7;

import java.util.concurrent.locks.ReentrantLock;

class Editor implements Runnable {

    public Editor(File f) {
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
                    System.out.println("Stopping an editor");
                    return;
                }
            } finally {
                lock.unlock();
            }
            aFile.change();
            aFile.save();
            try {
				Thread.sleep(1000);
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
