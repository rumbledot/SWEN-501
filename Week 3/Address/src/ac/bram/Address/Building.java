package ac.bram.Address;

import java.util.ArrayList;

public class Building extends Suburb {
	
	private Suburb part;
	
	public Building(String n) {
		super(n);
	}
	
	public void partOf(Suburb s) {
		this.part = s;
	}
}
