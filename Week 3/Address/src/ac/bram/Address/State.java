package ac.bram.Address;

import java.util.ArrayList;

public class State extends Country{
	
	private Country part;
	
	public State(String n) {
		super(n);
	}
	
	public void partOf(Country s) {
		this.part = s;
	}
	
}
