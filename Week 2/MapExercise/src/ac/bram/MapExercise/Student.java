package ac.bram.MapExercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
	
	private static int uniq = 1000000;
	private int ID;
	private String name;
	
	private ArrayList <Course> activeCourses = new ArrayList<Course>();
	private Map <Course, ArrayList<String>> finishedCourses = new HashMap<Course,ArrayList<String>>();
	
	public Student (String name) {
		this.name = name;
		this.ID = uniq++;
	}

	public int ID() {
		return ID;
	}

	public String name() {
		return name;
	}
	
	public void enrollTo(Course c) {
		if(!this.activeCourses.contains(c)) {
			this.activeCourses.add(c); 
		}
	}
	
	public void printActiveInCourse() {
		System.out.println("> " + this.ID + " : " + this.name + " enrolled in:");
		for (Course c : this.activeCourses) {
			c.print();
		}
	}
	
	public void finishedCourses() {
		System.out.println("Finished courses:");
		System.out.println("-----------------");
		System.out.println(" Name      Grade ");
		System.out.println("-----------------");
		for (Course c : this.finishedCourses.keySet()) {
			System.out.println(c.name() + " : " + this.finishedCourses.get(c) );
		}
		System.out.println("-----------------");
	}
	
	public void print() {
		System.out.println("> " + this.ID + " : " + this.name);
	}
	
	public void finishACourse(Course c, String grade) {
		/*if (this.activeCourses.contains(c)) {
			this.activeCourses.remove(c);
			this.finishedCourses.put(c, grade);
		} else {
			System.out.println("Student not enrolled in " + c.name());
		}*/
		//this.finishedCourses.put(c, grade);
		if(this.finishedCourses.get(c) == null) { 
			this.finishedCourses.put(c, new ArrayList<String>()); 
			}
		this.finishedCourses.get(c).add(grade);
	}
}
