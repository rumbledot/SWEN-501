package ac.bram.HelloFX;

public class Student {
	
	private static int uniq = 1;
	private int ID;
	private String name;

	public Student(String n) {
		this.ID = uniq++;
		this.name = n;
	}
	
	public int ID() {
		return this.ID;
	}
	
	public String name() {
		return this.name;
	}
	
}
