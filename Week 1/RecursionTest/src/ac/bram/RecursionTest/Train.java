package ac.bram.RecursionTest;

import java.util.ArrayList;

public class Train <T> {

	Wagon<T> head = null;

	public Train () {

	}
	// 3
	public void prepend(T x) {
		Wagon<T> w = new Wagon<T>(x);
		w.addNextWagon(head);
		head = w;
		this.reIndexWagon(head, 0);
	}
	// 4 
	public void prepend(Wagon<T> wagon) {
		head = wagon;
		this.reIndexWagon(head, 0);
	}
	// 6
	public int size() {
		if (head != null)
			return head.size();
		return 0;
	}
	// 8
	public void append(Wagon<T> wagon) {
		Wagon<T> lastWagon = head.lastWagon(head);
		lastWagon.addNextWagon(wagon);
		this.reIndexWagon(head, 0);
	}
	// 9
	public Wagon<T> getWagon(int i) {
		if (i > 0) {
			Wagon<T>selected = head.findWagonWithIndex(i);
			if (selected != null) return selected;
		}
		return null;

	}
	// 10
	public int findWagon(T value) {
		
		int target = head.findWagonWithValue(value);
		return target;
		
	}
	// 11
	public T get(int i) {
		return this.getWagon(i).getValue();
		
	}
	// 12
	public void remove(int i) {
		Wagon<T> selected = this.getWagon(i);
		if (selected.getIndex() > 0) {
			Wagon<T> inFrontOfSelected = this.getWagon(i - 1);
			inFrontOfSelected.addNextWagon(selected.getNextWagon());
		} else if(selected.getIndex() == 0) {
			head = null;
		}
		this.reIndexWagon(head, 0);
	}
	// 13
	public void insert(int i, T value) {
		if (this.getWagon(i) != null && i > 0) {
			Wagon<T> newWagon = new Wagon<T>(value);
			Wagon<T> prevWagon = this.getWagon(i - 1);
			newWagon.insertAfter(prevWagon);
		}
		this.reIndexWagon(head, 0);
	}
	// 14
	public void add(T value) {
		Wagon<T> newWagon = new Wagon<T>(value);
		this.append(newWagon);
	}
	// 15
	public void set(int i, T value) {
		Wagon<T> selected = this.getWagon(i);
		selected.changeValue(value);
	}
	// 16
	public void addAll (Train<T> train) {
		train.append(this.head);
	}
	// 17
	
	/*
	public void insertAfter(Wagon<T> wagon, Wagon<T> newWagon) {
		newWagon.addNextWagon(wagon.getNextWagon());
		wagon.addNextWagon(newWagon);
		this.reIndexWagon(head, 0);
	}*/

	public String printWagons(Wagon<T> wagon) {

		if(wagon != null) {
			//System.out.println(wagon.getValue());
			return wagon.getIndex() + " " +  wagon.getValue() + " " + printWagons(wagon.getNextWagon());
		}
		return "End of Train";

	}

	private int reIndexWagon(Wagon<T> wagon, int index) {
		if(wagon != null) {
			wagon.setIndex(index++);
			return reIndexWagon(wagon.getNextWagon(), index++);
		}
		return 0;
	}



}