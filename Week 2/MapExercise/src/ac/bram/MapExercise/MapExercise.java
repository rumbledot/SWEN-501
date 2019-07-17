package ac.bram.MapExercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapExercise {

	private Map<String, Course> courses = new HashMap<String, Course>();
	private Map<Integer, Student> students = new HashMap<Integer, Student>();
	private Map<String, Student> byFirstNameStudents = new HashMap<String, Student>();
	private Map<String, Student> byLastNameStudents = new HashMap<String, Student>();
	private Map<String, Student> byFullNameStudents = new HashMap<String, Student>();

	Scanner input = new Scanner(System.in);

	public MapExercise() {
		int opt;
		do {
			System.out.println();
			System.out.println("------Main menu-------");
			System.out.println("======================");
			System.out.println("1 -> Add a new course");
			System.out.println("2 -> Add a new student");
			System.out.println("3 -> List students");
			System.out.println("4 -> List courses");
			System.out.println("5 -> Enrolled a student to a course");
			System.out.println("6 -> Input student's grade");
			System.out.println("7 -> Inquire a student");
			System.out.println("8 -> Inquire a course");
			System.out.println("9 -> Load data");
			System.out.println("Choose a number :");
			opt = input.nextInt();
			switch(opt) {
			case 1:
				this.addCourse();
				break;
			case 2:
				this.addStudent();
				break;
			case 3:
				this.listStudents();
				break;
			case 4:
				this.listCourses();
				break;
			case 5:
				this.enrolledAStudent();
				break;
			case 6:
				this.studentGrade();
				break;
			case 7:
				this.inquireStudent();
				break;
			case 8:
				this.inquireCourse();
				break;
			case 9:
				this.doLoad();
				break;
			}
		} while (opt != 0);
	}

	private void addCourse() {
		this.listCourses();
		System.out.println("Add a new course :");
		System.out.println("Course's name :");
		String name = input.next();
		Course c = new Course(name);
		courses.put(c.name().toLowerCase(), c);
	}

	private void addStudent() {
		System.out.println("Add a new student :");
		System.out.println("Student first name :");
		String nameF = input.next();
		System.out.println("Student last name :");
		String nameL = input.next();
		String name = nameF + " " + nameL;
		Student s = new Student(name);
		System.out.println("New student added");
		s.print();
		System.out.println();
		students.put(s.ID(), s);
		nameF = nameF.toLowerCase();
		nameL = nameL.toLowerCase();
		this.byFirstNameStudents.put(nameF, s);
		this.byLastNameStudents.put(nameL, s);
		this.byFullNameStudents.put(name, s);
	}

	private void listStudents() {
		System.out.println("Active students");
		System.out.println("---------------");
		for (Map.Entry<Integer, Student> s : students.entrySet()) {
			s.getValue().print();
		}
	}

	private void listCourses() {
		System.out.println("Available courses");
		System.out.println("-----------------");
		for (Map.Entry<String, Course> c : courses.entrySet()) {
			c.getValue().print();
		}
	}

	private void enrolledAStudent() {
		this.listStudents();
		System.out.println("Enrolled a student by ID");
		int ID = input.nextInt();
		if (students.containsKey(ID)){
			Student s = students.get(ID);
			s.printActiveInCourse();
			System.out.println("Enroll this student to");
			String name = input.next();
			if(courses.containsKey(name)) {
				Course c = courses.get(name);
				c.enrolled(s);
				s.enrollTo(c);
			} else {
				System.out.println("Course not found");
			}
		} else {
			System.out.println("Student not found");
		}
	}

	private void studentGrade() {
		this.listStudents();
		System.out.println("Give grade a student by ID");
		int ID = input.nextInt();
		if (students.containsKey(ID)){
			Student s = students.get(ID);
			s.print();
			System.out.println("Which course student have finished");
			String name = input.next();
			if(courses.containsKey(name)) {
				Course c = courses.get(name);
				System.out.println("Student's grade:");
				String grade = input.next();
				grade = grade.toUpperCase();
				s.finishACourse(c, grade);
				c.passed(s, grade);
			} else {
				System.out.println("Course not found");
			}
		} else {
			System.out.println("Student not found");
		}
	}

	private void inquireStudent() {
		int ID = 0;
		this.listStudents();
		System.out.println("Student by ID / Name");
		System.out.println("--------------------");
		String i = input.next();
		if(i.matches("[0-9]+") && i.length() > 2) {
			ID = Integer.parseInt(i);
			if (students.containsKey(ID)){
				Student s = students.get(ID);
				s.printActiveInCourse();
				s.finishedCourses();
			} else {
				System.out.println("Student not found");
			}
		} else {
			i = i.toLowerCase();
			if (byFullNameStudents.containsKey(i)) {
				Student s = byFullNameStudents.get(i);
				s.printActiveInCourse();
				s.finishedCourses();
			} else if (byLastNameStudents.containsKey(i)) {
				Student s = byLastNameStudents.get(i);
				s.printActiveInCourse();
				s.finishedCourses();
			} else if (byFirstNameStudents.containsKey(i)){
				Student s = byFirstNameStudents.get(i);
				s.printActiveInCourse();
				s.finishedCourses();
			} else {
				System.out.println("Student not found");
			}

		}

	}

	private void inquireCourse() {
		this.listCourses();
		System.out.println("Course's name");
		System.out.println("-------------");
		String name = input.next();
		name = name.toLowerCase();
		if(courses.containsKey(name)) {
			Course c = courses.get(name);
			c.printStudents();
		} else {
			System.out.println("Course not found");
		}
	}

	private void doLoad() {
		Student s = null;
		Course c = null;
		//int total = 0;
		System.out.println("Load a data file");
		System.out.println("----------------");
		String name = input.next();

		try {
			String fileName = "C:/Users/Abram/Documents/MSwDev 2019/SWEN 501/Week 2/MapExercise/src/ac/bram/MapExercise/data.txt";

			Scanner sc = new Scanner(new File(fileName));

			while (sc.hasNext()) {

				String lines = sc.nextLine().trim();
				//System.out.println(lines);
				String[] line = lines.split(" ");
				//System.out.println(line[0] + " " + line[1] + " " + line[2] + " " + line[3]);
					
				// GET THE COURSE NAME
				String course = line[0].toLowerCase();
				if (!courses.containsKey(course)) {
					c = new Course(line[0]);
					courses.put(course, c);
				} else {
					c = courses.get(course);
					/*if(c.name().equalsIgnoreCase("WRIT101")) {
						System.out.println(s.name());
					}*/
				}
				
				//System.out.println(c.name());

				// GET THE NAME
				String i = line[2].toLowerCase();
				String j = line[3].toLowerCase();
				String f = i + " " + j;
				
				/*if(c.name().equalsIgnoreCase("WRIT101")) {
					System.out.println(i + " " + j + " " + f);
				}*/

				if (byFullNameStudents.containsKey(f)){
					s = byFullNameStudents.get(f);
					/*if(c.name().equalsIgnoreCase("WRIT101")) {
						System.out.println(s.name());
					}*/
				} else if (byFirstNameStudents.containsKey(i) && byLastNameStudents.containsKey(j)) {
					s = byLastNameStudents.get(j);
				} else {
					s = new Student(line[2] + " " + line[3]);
					//System.out.println("New student");
					//total++;
					students.put(s.ID(), s);
					byFullNameStudents.put(f, s);
					byFirstNameStudents.put(i, s);
					byLastNameStudents.put(j, s);
				}
				/*
				if(f.equalsIgnoreCase("alan butler") && course.equalsIgnoreCase("writ101")) {
					System.out.println(c.name());
				}*/
				
				//System.out.println(s.name());
				//System.out.println(line[1]);
				
				// GET THE MARK
				if (line[1].contentEquals("-")) {
					s.enrollTo(c);
					c.enrolled(s);
					/*if(c.name().equalsIgnoreCase("WRIT101")) {
						System.out.println(s.name());
					}*/
				} else { 
					//System.out.println(line[1]);
					s.enrollTo(c);
					c.enrolled(s);
					s.finishACourse(c, line[1]);
					c.passed(s, line[1]);
					/*if(c.name().equalsIgnoreCase("WRIT101")) {
						System.out.println(s.name());
					}*/
				}
				/*
				if(f.equalsIgnoreCase("alan butler")) {
					s.printActiveInCourse();
					s.finishedCourses();
				}
				*/
				//System.out.println(line[0] + " " + line[1] + " " + line[2] + " " + line[3]);
				
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not exist");
		}
		//System.out.println(students.size() + " " + total);
	}

	public static void main(String[] args) {
		new MapExercise();
	}

}