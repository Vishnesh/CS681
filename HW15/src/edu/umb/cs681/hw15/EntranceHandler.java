package edu.umb.cs681.hw15;

class EntranceHandler implements Runnable {
	private AdmissionMonitor admisionmonitor;

	public void run() {
		admisionmonitor.enter();
	}
}
