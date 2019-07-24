package ac.bram.Address;

import java.util.ArrayList;

public class City extends State {

	private State part;
	
	public City(String n) {
		super(n);
	}
	
	public void partOf(State s) {
		this.part = s;
	}
}
