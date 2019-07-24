package ac.bram.Address;

public class Person {
	
	private String name;
	private String street;
	private Address liveIn;
	
	Person (String n, String a) {
		this.name = n;
		this.street = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String a) {
		this.street = a;
	}
	
	public void liveIn (Address a) {
		this.liveIn = a;
	}
	
	public Address liveAt () {
		return this.liveIn;
	}
	
	public void printAddress() {
		System.out.println();
		System.out.println(this.name);
		System.out.println(this.street);
		System.out.println(this.liveIn.print());
		System.out.println("-----------------");
	}
	
	public void changeCity(Address a) {
		this.liveIn = a;
	}
	
	public void changeDistrict(Address a) {
		this.liveIn.change(this.liveIn.getPart(), a);
	}
	
	public void changeCountry(Address a) {
		this.liveIn.change(this.liveIn.getPart().getPart(), a);
	}
}