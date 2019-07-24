package ac.bram.Address;

public class Address {
	
	private String name;
	private Address part = null;
	
	public Address(String n) {
		this.name = n;
	}
	
	public String name() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPart(Address a) {
		this.part = a;
	}
	
	public Address getPart() {
		return this.part;
	}

	public String print() {
		Address a = this;
		if (a.part != null) {
			return a.name() + ", " + a.getPart().print();
		}
		return a.name();
	}
	
	public void change(Address n, Address o) {
		if (this.part.equals(o)) {
			this.part = n;
		} else {
			this.part.change(n, o);
		}
	}
}