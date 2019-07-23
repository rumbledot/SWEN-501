package ac.bram.Contacts;

import java.util.*;

public class Person extends Contact {

	private ArrayList<String> phone = new ArrayList<>();
	private ArrayList<String> email = new ArrayList<>();
	private String address;
	private Date bday;
	private String GST;
	private String note;
	private String relationship;
	private Contact employer;
	private Map<String, Person> employees = new HashMap<String, Person>();

	public Person(String n, String phone, String email, String address, Date bday, String relationship) {
		super(n);
		this.phone.add(phone);
		this.email.add(email);
		this.address = address;
		this.bday = bday;
		this.relationship = relationship;
	}

	public ArrayList<String> getPhone() {
		return phone;
	}

	public void setPhone(String p) {
		this.phone.add(p);
	}

	public void removePhone(int i) {
		this.phone.remove(i);
	}

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(String e) {
		this.email.add(e);
	}

	public void removeEmail(int i) {
		this.email.remove(i);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGST() {
		return GST;
	}

	public void setGST(String gST) {
		GST = gST;
	}

	public String seeNote() {
		return note;
	}

	public void addNote(String note) {
		this.note = note;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Contact getEmployer() {
		return employer;
	}

	public void setEmployer(Contact employer) {
		this.employer = employer;
	}

	public Person getEmployees(String name) {
		if (employees.containsKey(name)) {
			Person toGet = employees.get(name);
			return toGet;
		}
		return null;
	}
	
	public Map<String, Person> listEmployee() {
		return employees;
	}

	public void addEmployee(Person e) {
		this.employees.put(e.name(), e);
	}

	public void removeEmployee(String e) {
		this.employees.remove(e);
	}

	public Date getBday() {
		return bday;
	}

	public void setBday(Date bday) {
		this.bday = bday;
	}
}