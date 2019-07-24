package ac.bram.RecursionTest;

import java.util.ArrayList;

public class Wagon <Q> {
	
	private Q value;
	private Wagon<Q> next = null;
	private int index = 0;
	
	public Wagon(Q val) {
		this.value = val;
	}
	
	public void addNextWagon(Wagon<Q> next) {
		this.next = next;
	}
	
	public Wagon<Q> getNextWagon() {
		return next;
	}
	// 5
	public int size() {
		if (this.getNextWagon() == null) return 1;
		
		return 1 + next.size();
	}
	// 7
	public void insertAfter(Wagon<Q> w) {
		Wagon<Q> oldNextWagon = w.getNextWagon();
		w.addNextWagon(this);
		this.addNextWagon(oldNextWagon);
		/*
		w.addNextWagon(next);
		this.next = w;*/
	}
	// 8
	public Wagon<Q> lastWagon(Wagon<Q> w) {
		if (w.getNextWagon() == null) return w;
		return lastWagon(w.getNextWagon());
	}
	public void appendWagon(Wagon<Q> w) {
		if (next == null) {
			next = w;
		} else {
			next.appendWagon(w);
		}
	}
	// 9
	public Wagon<Q> findWagonWithIndex(int i) {
		if (this != null && this.getIndex() == i) return this;
		//else if (this == null) return null;
		else if (this.getNextWagon() != null) return this.getNextWagon().findWagonWithIndex(i);
		return null;
	}
	// 17
	public void lastIndexOf() {
		
	}
	
	// 19
	public Wagon<Q> reverseHelper() {
		if (next == null) {
			return this;
		}
		Wagon<Q> last = next.reverseHelper();
		next.addNextWagon(this);
		return last;
	}
	
	public int findWagonWithValue(Q value) {
		if (this.getValue().equals(value)) return this.getIndex();
		else if (this.getNextWagon() != null) return this.getNextWagon().findWagonWithValue(value);
		return -1;
	}
	
	public int findWagon(Q val, int index) {
		if (this.value.equals(val)) {
			return index;
		}
		if(next == null) {
			return -1;
		}
		return next.findWagon(val, index++);
	}
	
	public void setIndex(int i) {
		index = i;
	}
	
	public int getIndex() {
		return this.index;
	}
		
	public Q getValue() {
		return value;
	}
	
	public void changeValue(Q value) {
		this.value = value;
	}
}
