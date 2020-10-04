package edu.umb.cs681.hw01;

public class DJIAEvent {

	private float djia;
	
	public DJIAEvent() {
		// TODO Auto-generated constructor stub
		djia = 0;
		System.out.println("New DJIEvent created");
	}

	public DJIAEvent(float g) {
		// TODO Auto-generated constructor stub
		djia = g;
	}

	public float getValue() {
		// TODO Auto-generated method stub
		return djia;
	}
}
