package ac.bram.RecursionTest;

import java.util.ArrayList;

public class Train <T> {

	Wagon<T> head = null;

	public Train () {

	}

	public void prepend(T x) {
		Wagon<T> w = new Wagon<T>(x);
		w.addWagon(head);
		head = w;
		this.reIndexWagon(head, 1);
	}

	public void prepend(Wagon<T> wagon) {
		head = wagon;
		this.reIndexWagon(head, 1);
	}
	
	public void append(Wagon<T> wagon) {
		Wagon<T> lastWagon = head.lastWagon(head);
		lastWagon.addWagon(wagon);
		this.reIndexWagon(head, 1);
	}
	
	public Wagon<T> getWagon(Wagon<T> wagon, int i) {
		
		if(wagon != null) {
			int wagonIndex = wagon.getIndex();
			if (wagonIndex == i) return wagon;
			return getWagon(wagon.getNextWagon(), i);
			}
		
		return null;
	}
	
	public int findWagon(T value) {
		int target = head.findWagon(value) + 1;
		return target;
	}

	public void insert(Wagon<T> wagon, Wagon<T> newWagon) {
		newWagon.addWagon(wagon.getNextWagon());
		wagon.addWagon(newWagon);
		this.reIndexWagon(head, 1);
	}

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

	public int size() {
		if (head != null)
			return head.size();
		return 0;
	}

}