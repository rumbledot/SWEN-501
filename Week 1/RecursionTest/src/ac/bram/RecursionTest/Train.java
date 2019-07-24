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
		wagon.addNextWagon(head);
		head = wagon;
		this.reIndexWagon(head, 0);
	}
	// 6
	public int size() {
		if (head != null)
			return head.size();
		return 0;
	}
	public void iterativeSize() {
		Wagon<T> w = head;
		int s = 0;
		while( w!= null) {
			s++;
			w = w.getNextWagon();
		}
	}
	// 8
	public void append(Wagon<T> wagon) {
		Wagon<T> lastWagon = head.lastWagon(head);
		lastWagon.addNextWagon(wagon);
		this.reIndexWagon(head, 0);
/*
		if (head != null)
			head.appendWagon(wagon);
		else 
			head = wagon;*/
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
		if (head != null) {
			Wagon<T> w = head;
			int s = 0;
			while(w.getNextWagon() != null) {
				if(w.getValue().equals(value)) {
					return s;
				}
				s++;
				w = w.getNextWagon();
			}
		}
		return 0;
		/*
		int target = head.findWagonWithValue(value);
		return target;*/
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
/*
		if (i == 0) {
			head = head.getNextWagon();
		} else {
			Wagon<T> w = getWagon(i - 1);
			w.addNextWagon(w.getNextWagon().getNextWagon());
		}*/
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
		//train.append(this.head);
		this.append(train.head());
	}
	// 17
	public int lastIndexOf(Wagon<T> x) {
		return 0;
	}
	// 18
	public Train<T> reversed() {
		/*Wagon<T> newHead = new Wagon<T>(head.findWagonWithIndex(head.size() - 1).getValue());
		Wagon<T> currentWagon = newHead;
		for (int i = head.size() - 2; i >= 0; i--) {
			Wagon<T> w = new Wagon<T>(head.findWagonWithIndex(i).getValue());

			if(i == 0) {
				currentWagon.addNextWagon(w);
				w.addNextWagon(null);
			} else {
				currentWagon.addNextWagon(w);
				currentWagon = w;
			}
		}
		Train<T> t = new Train<T>();
		t.prepend(newHead);
		return t;*/
		
		Train<T> ret = new Train<T>();
		Wagon<T> w = head;
		while (w != null) {
			ret.prepend(w.getValue());;
			w = w.getNextWagon();
		}
		return ret;
	}
	public Train<T> reversedRecursize() {
		Train<T> ret = new Train<T>();
		Wagon<T> w = head;
		reversedRecursiveHelper(ret ,w);
		return ret;
	}
	private void reversedRecursiveHelper(Train<T> t, Wagon<T> w) {
		if (w == null) {
			return;
		}
		t.prepend(w.getValue());
		reversedRecursiveHelper(t, w.getNextWagon());
	}
	// 19
	public void reverse() {
		Wagon<T> newHead = head.reverseHelper();
		head.addNextWagon(null);
		head = newHead;
	}
	// 20

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

	public String print() {
		return this.printWagons(this.head);
	}

	private int reIndexWagon(Wagon<T> wagon, int index) {
		if(wagon != null) {
			wagon.setIndex(index++);
			return reIndexWagon(wagon.getNextWagon(), index++);
		}
		return 0;
	}
	
	public Wagon<T> head() {
		return this.head;
	}
	
}