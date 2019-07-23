package ac.bram.Contacts;

import java.util.ArrayList;

public class Business extends Contact {

	private ArrayList<String> phone = new ArrayList<>();
	private ArrayList<String> email = new ArrayList<>();
	private String address;
	private String GST;
	private String note;
	
	public Business(String n, String phone, String email, String address, String GST) {
		super(n);
		this.phone.add(phone);
		this.email.add(email);
		this.address = address;
		this.GST = GST;
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

}
