package ac.bram.RecursionTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//System.out.println("result " + fib(3));
		
		/*Tree t = new Tree("Tree 1");
		Tree t1 = new Tree("Tree 2");
		t.addChild(t1);
		Tree t2 = new Tree("Tree 3");
		t1.addChild(t2);
		Tree t3 = new Tree("Tree 4");
		t2.addChild(t3);
		printTree(t, ">");*/
		
		/*List<Integer> numbers = new ArrayList<>( Arrays.asList( 
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ));

		System.out.println(indexInSortedList(1, numbers, 0, 9));*/
		
		/*Wagon<Integer> newCabin = new Wagon<>(15);
		
		Wagon<Integer> cabin1 = new Wagon<Integer>(1);
		newCabin.addWagon(cabin1);
		Wagon<Integer> cabin2 = new Wagon<Integer>(2);
		cabin1.addWagon(cabin2);
		Wagon<Integer> cabin3 = new Wagon<Integer>(3);
		cabin2.addWagon(cabin3);
		Wagon<Integer> cabin4 = new Wagon<Integer>(4);
		cabin3.addWagon(cabin4);
		Wagon<Integer> newCabin1 = new Wagon<>(6);
		newCabin1.addWagon(newCabin);*/
		
		/**
		 * 
		 * TRAIN AND WAGON EXERCISE
		 * 
		 */
		
		Wagon<String> cabin1 = new Wagon<>("A");
		Wagon<String> cabin2 = new Wagon<>("B");
		cabin1.addNextWagon(cabin2);
		Wagon<String> cabin3 = new Wagon<>("C");
		cabin2.addNextWagon(cabin3);
		Wagon<String> cabin4 = new Wagon<>("D");
		cabin3.addNextWagon(cabin4);
		Wagon<String> newCabin = new Wagon<>("w");
		newCabin.addNextWagon(cabin1);
		Wagon<String> newCabin1 = new Wagon<>("x");
		newCabin1.insertAfter(cabin2);
		
		System.out.println("Cabin1 size " + cabin1.size());
		System.out.println("NewCabin size " + newCabin.size());
		System.out.println("NewCabin1 size " + newCabin1.size());
		
		Train<String> train = new Train<String>();
		train.prepend(newCabin);
		System.out.println("Train size : " + train.size());
		System.out.println(train.printWagons(newCabin));
		
		Wagon<String> newCabin2 = new Wagon<>("y");
		newCabin2.insertAfter(cabin2);
		
		Wagon<String> newCabin3 = new Wagon<>("z");
		train.append(newCabin3);
		
		System.out.println(train.printWagons(newCabin));
		
		Wagon<String> selectedWagon = train.getWagon(7);
		if (selectedWagon != null) {
			System.out.println(selectedWagon.getValue());
		} else {
			System.out.println("Wagon not found at index");
		}
		
		System.out.println(train.findWagon("E"));
		//System.out.println(train.findWagon("A"));
		//System.out.println(train.getWagon(2));
		//System.out.println(train.getWagon(train.findWagon("A")));
		System.out.println(train.get(2));
		train.remove(2);
		System.out.println(train.printWagons(newCabin));
		
		train.insert(6, "f");
		System.out.println(train.printWagons(newCabin));
		
	}

	public static int fib(int n)
	{
		System.out.println(n);

		if(n <= 1) 
			return 1;
		int x = fib(n - 1);
		System.out.println("fib 1 " + x);
		int y = fib(n - 2);
		System.out.println("fib 2 " + y);

		System.out.println("N:" + n + " x:" + x + ", " + y);

		return x + y;
	}

	static void printTree(Tree t, String prefix)
	{
		for (Tree x : t.getChildren()) 
		{
			printTree(x, prefix + ">");
		}
		System.out.println(prefix + t.toString());
	}
	
	static <T extends Comparable<T>> int indexInSortedList(T needle, List<T> haystack, int start, int end) 
	{
		if(start == end) return -1;
		int mid = start + (end - start) / 2;
		T val =haystack.get(mid);
		if (needle.compareTo(val) == 0) return mid;
		if (needle.compareTo(val) < 0) return indexInSortedList(needle, haystack, start, mid);
		return indexInSortedList(needle, haystack, mid + 1, end);
	}

}