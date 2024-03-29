package ac.bram.MapExercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {
	
	private String name;
	
	private ArrayList<Student> enrolledStudents = new ArrayList<>();
	private Map<Student, ArrayList<String>> passedStudents = new HashMap<Student, ArrayList<String>>();
	
	public Course(String name) {
		this.name = name;
	}
	
	public String name() {
		return this.name;
	}
	
	public void enrolled(Student s) {
		this.enrolledStudents.add(s);
	}
	
	public void passed(Student s, String grade) {
		//this.enrolledStudents.remove(s);
		if(this.passedStudents.get(s) == null) { 
			this.passedStudents.put(s, new ArrayList<String>()); 
			}
		this.passedStudents.get(s).add(grade);
	}
	
	public void deEnroll(Student s) {
		this.enrolledStudents.remove(s);
	}
	
	public void printStudents() {
		int size = enrolledStudents.size();
		ArrayList<String> g = new ArrayList<String>();
		System.out.println(size + " students enrolled in " + this.name);
		System.out.println("-----------------------------------------");
		for (Student s : enrolledStudents) {
			System.out.println("> " + s.ID() + " : " + s.name() );
			g = this.passedStudents.get(s);
			if (g == null) { 
				System.out.println("  n/a"); 
			} else {
				System.out.println("  " + g);
			}
		}
	}
	
	public void print() {
		System.out.println("> " + this.name);
	}
	
	public void listStudent() {
		System.out.println("Active enrolled students :");
		for(Student s : this.enrolledStudents) {
			s.print();
		}
	}
	
}
