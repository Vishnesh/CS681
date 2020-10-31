package edu.umb.cs.cs681.hw4;

import java.util.LinkedList;

class MCTest{
  public static void main(String[] args) throws Exception {
    LinkedList<Thread> threads = new LinkedList<Thread>();

    long nTimes  = Long.parseLong(args[0]);
    int nThreads = Integer.parseInt(args[1]);

    for (int i = 0; i < nThreads; i++) {
    	Thread t = new Thread( ()->{for (long j = 0; j < nTimes; j++) {} } );
    	threads.add(t);
    	t.start();
    }
  }
}
