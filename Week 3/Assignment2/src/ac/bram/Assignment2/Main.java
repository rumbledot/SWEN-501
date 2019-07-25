package ac.bram.Assignment2;

import java.util.Iterator;
import java.util.List;

public class Main {

	public Main() {
		stringData();
		//doubleData();
	}

	public static void main(String[] args) {
		new Main();
	}

	private static void doubleData() {
		ArrayList<Double> data = new ArrayList<Double>();
		java.util.ArrayList<Double> data2 = new java.util.ArrayList<Double>();
		java.util.ArrayList<Double> data3 = new java.util.ArrayList<Double>();

		data.add(1.01);
		data.add(1.02);
		data.add(1.03);
		data.add(1.04);

		System.out.print("Initial data : ");
		data.print();
		System.out.println();

		System.out.print("Data ");
		if (data.isEmpty()) {
			System.out.println("is empty");
		} else {
			System.out.println("have something");
		}
		System.out.println();

		System.out.println("Data size : " + data.size());
		System.out.println();

		System.out.println("Whats in index  2 : " + data.get(2));
		System.out.println("Whats in index 10 : " + data.get(10));
		System.out.println();

		data.set(3, 10.04);

		System.out.println("Change index 3 to 10.04 ");
		data.print();
		System.out.println();

		data.clear();

		data.add(11.1);
		data.add(12.1);
		data.add(13.1);
		data.add(14.1);

		System.out.println("New data size: " + data.size());
		System.out.print("New data : ");
		data.print();

		data2.add(20.1);
		data2.add(20.2);
		data2.add(20.3);
		data2.add(20.4);

		System.out.print("New data to be added : ");
		for(Double s : data2) {
			System.out.print(s + " ");
		}
		System.out.println();

		data.addAll(data2);

		System.out.print("New data content : ");
		data.print();
		System.out.println();

		data.clear();

		data.add(101.00);		
		data.add(102.00);
		data.add(103.00);
		data.add(104.00);
		data.add(105.00);
		data.add(106.00);

		System.out.println("New data set : ");
		data.print();

		data3.add(102.00);
		data3.add(102.00);
		data3.add(104.00);
		data3.add(105.00);

		System.out.print("Data to check againts : ");
		for(Double s : data3) {
			System.out.print(s + " ");
		}
		System.out.println();

		System.out.println("Is data contains above data ?");
		if (data.containsAll(data3)) {
			System.out.println("Yep");
		} else {
			System.out.println("Nope");
		}
		System.out.println();

		data3.clear();
		data3.add(102.00);
		data3.add(102.00);
		data3.add(104.00);


		System.out.print("Original data : ");
		data.print();
		System.out.print("Entry to delete in data : ");
		for(Double s : data3) {
			System.out.print(s + " ");
		}
		System.out.println();

		data.removeAll(data3);

		System.out.println("Data after deletion : ");
		data.print();
		System.out.println();

		System.out.print("Original data : ");
		data.print();
		System.out.print("Entry to add to data at index 2 : ");
		for(Double s : data2) {
			System.out.print(s + " ");
		}
		System.out.println();
		data.addAll(2, data2);
		System.out.print("New data : ");
		data.print();
		System.out.println();

		data.add(1, 1.01);
		data.add(5, 1.01);

		System.out.print("Original data : ");
		data.print();

		System.out.print("Location of 2 : ");
		System.out.println(data.indexOf(1.01));
		System.out.print("Location of the last 2 : ");
		System.out.println(data.lastIndexOf(1.01));
		System.out.println();

		System.out.print("Original data : ");
		data.print();

		Object[] newData = new Object[data.size()];
		newData = data.toArray();

		System.out.print("Cloned data : ");
		for (int i = 0; i < newData.length; i++) {
			System.out.print(newData[i] + " ");
		}
		System.out.println();
		System.out.println();

		System.out.println("Iterator : ");
		Iterator<Double> it = data.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		System.out.println();

		System.out.print("Original data : ");
		data.print();
		List<Double>newData1 = new java.util.ArrayList<Double>();
		newData1 = data.subList(2, 6);
		System.out.print("A copy of data from index 2 - 6: ");
		for (Double v : newData1) {
			System.out.print(v + " ");
		}
		System.out.println();

	}

	private static void stringData() {
		ArrayList<String> data = new ArrayList<String>();
		java.util.ArrayList<String> data2 = new java.util.ArrayList<String>();
		java.util.ArrayList<String> data3 = new java.util.ArrayList<String>();

		data.add("1");
		data.add("2");
		data.add("3");
		data.add("4");
		data.add("5");
		data.add("7");

		System.out.print("Initial data : ");
		data.print();
		System.out.println();

		System.out.print("Data ");
		if (data.isEmpty()) {
			System.out.println("is empty");
		} else {
			System.out.println("have something");
		}
		System.out.println();

		System.out.println("Data size : " + data.size());
		System.out.println();

		System.out.println("Whats in index  2 : " + data.get(2));
		System.out.println("Whats in index 10 : " + data.get(10));
		System.out.println();

		data.set(3, "6");

		System.out.println("Change index 3 to 6 ");
		data.print();
		System.out.println();

		data.clear();

		data.add("a");
		data.add("b");
		data.add("c");
		data.add("d");

		System.out.println("New data size: " + data.size());
		System.out.print("New data : ");
		data.print();
		
		data.add(2, "z");
		System.out.print("Insert z at index 2 : ");
		data.print();
		
		System.out.println("Is data contain z ?");
		if (data.contains("z")) {
			System.out.println("Yep");
		} else {
			System.out.println("Nope");
		}
		System.out.println();
		
		data.remove("y");
		System.out.print("y is removed : ");
		data.print();
		data.remove("z");
		System.out.print("z is removed : ");
		data.print();
		
		data2.add("8");
		data2.add("9");
		data2.add("10");
		data2.add("11");

		System.out.print("New data to be added : ");
		for(String s : data2) {
			System.out.print(s + " ");
		}
		System.out.println();

		data.addAll(data2);

		System.out.print("New data content : ");
		data.print();
		System.out.println();

		data.clear();

		data.add("one");		
		data.add("two");
		data.add("three");
		data.add("four");
		data.add("five");
		data.add("six");
		data.add("seven");

		System.out.println("New data set : ");
		data.print();

		data3.add("three");
		data3.add("four");
		data3.add("five");
		data3.add("seven");
		data3.add("seven");

		System.out.print("Data to check againts : ");
		for(String s : data3) {
			System.out.print(s + " ");
		}
		System.out.println();

		System.out.println("Is data contains above data ?");
		if (data.containsAll(data3)) {
			System.out.println("Yep");
		} else {
			System.out.println("Nope");
		}
		System.out.println();

		data3.clear();
		data3.add("1");
		data3.add("2");
		data3.add("2");
		data3.add("3");

		System.out.print("Data to check againts : ");
		for(String s : data3) {
			System.out.print(s + " ");
		}
		System.out.println();

		System.out.println("Is data contains above data ?");
		if (data.containsAll(data3)) {
			System.out.println("Yep");
		} else {
			System.out.println("Nope");
		}
		System.out.println();

		data3.clear();
		data3.add("one");
		data3.add("two");
		data3.add("one");
		data3.add("three");


		System.out.print("Original data : ");
		data.print();
		System.out.print("Entry to delete in data : ");
		for(String s : data3) {
			System.out.print(s + " ");
		}
		System.out.println();

		data.removeAll(data3);

		System.out.println("Data after deletion : ");
		data.print();
		System.out.println();

		System.out.print("Original data : ");
		data.print();
		System.out.print("Entry to add to data at index 2 : ");
		for(String s : data2) {
			System.out.print(s + " ");
		}
		System.out.println();
		data.addAll(2, data2);
		System.out.print("New data : ");
		data.print();
		System.out.println();

		data.add(1, "2");
		data.add(5, "2");

		System.out.print("Original data : ");
		data.print();

		System.out.print("Location of 2 : ");
		System.out.println(data.indexOf("2"));
		System.out.print("Location of the last 2 : ");
		System.out.println(data.lastIndexOf("2"));
		System.out.println();

		System.out.print("Original data : ");
		data.print();

		Object[] newData = new Object[data.size()];
		newData = data.toArray();
		String[] newData1 = data.toArray(new String[data.size()]);

		System.out.print("Cloned data : ");
		for (int i = 0; i < newData.length; i++) {
			System.out.print(newData[i] + " ");
		}
		System.out.println();
		System.out.print("Second clone : ");
		for (int i = 0; i < newData1.length; i++) {
			System.out.print(newData1[i] + " ");
		}
		System.out.println();
		System.out.println();

		System.out.println("Iterator : ");
		Iterator<String> it = data.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		System.out.println();

		System.out.print("Original data : ");
		data.print();
		List<String>newData2 = new java.util.ArrayList<String>();
		newData2 = data.subList(2, 6);
		System.out.print("A copy of data from index 2 - 6: ");
		for (String v : newData2) {
			System.out.print(v + " ");
		}
		System.out.println();
		
		data3.clear();
		data3.add("BLACK");
		data3.add("BROWN");
		data3.add("YELLOW");
		data3.add("GREEN");
		
		data.addAll(5, data3);
		data.print();
		System.out.println();
		
		data3.remove(1);
		System.out.print("Original set : ");
		data.print();
		System.out.println("To be keep in data : ");
		for (String v : data3) {
			System.out.print(v + " ");
		}
		System.out.println();
		data.retainAll(data3);
		System.out.print("After : ");
		data.print();
	}
}
