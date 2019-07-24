package ac.bram.Address;

import java.util.ArrayList;

public class Suburb extends City {
	
	private City part;
	
	public Suburb(String n) {
		super(n);
	}
	
	public void partOf(City s) {
		this.part = s;
	}
}
