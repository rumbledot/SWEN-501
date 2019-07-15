package ac.bram.RecursionTest;

import java.util.ArrayList;

public class Wagon <Q> {
	
	private Q value;
	private Wagon<Q> next = null;
	private int index = 0;
	
	public Wagon(Q val) {
		this.value = val;
	}
	
	public void addWagon(Wagon<Q> next) {
		this.next = next;
	}
	
	public Wagon<Q> getNextWagon() {
		return next;
	}
	
	public void setIndex(int i) {
		index = i;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int size() {
		if (this.getNextWagon() == null) return 1;
		
		return 1 + next.size();
	}
	
	public Wagon<Q> lastWagon(Wagon<Q> w) {
		if (w.getNextWagon() == null) return w;
		return lastWagon(w.getNextWagon());
	}
	
	public int findWagon(Q value) {
		if (this.getNextWagon().getValue().equals(value)) return this.getIndex();
		return this.getNextWagon().findWagon(value);
	}
	
	public void insertAfter(Wagon<Q> prevWagon) {
		Wagon<Q> oldNextWagon = prevWagon.getNextWagon();
		prevWagon.addWagon(this);
		this.addWagon(oldNextWagon);
	}
	
	public Q getValue() {
		return value;
	}
}
