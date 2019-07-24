package ac.bram.Address;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	private Map<String, Address> addresses = new HashMap<String, Address>();
	
	public static void main(String[] args) {
		Address nz = new Address("New Zealand");
		
		Address wellington = new Address("Wellington");
		wellington.setPart(nz);
		
		Address auckland = new Address("Auckland");
		auckland.setPart(nz);
		
		Address mountcook = new Address("Mount Cook");
		mountcook.setPart(wellington);
		Address mountvic = new Address("Mount Victoria");
		mountvic.setPart(wellington);
		Address karori = new Address("Karori");
		karori.setPart(wellington);
		Address brooklyn = new Address("Brooklyn");
		brooklyn.setPart(wellington);
		Address newtown = new Address("Newtown");
		newtown.setPart(wellington);
		
		Address newmarket = new Address("New Market");
		newmarket.setPart(auckland);
		Address ponsonby = new Address("Ponsonby");
		ponsonby.setPart(auckland);
		Address parnell = new Address("Parnell");
		parnell.setPart(auckland);
		
		Person abraham = new Person("Abraham", "QBA Apartments");
		abraham.liveIn(mountcook);
		Person natsuki = new Person("Natsuki", "Ranchood Apartments");
		natsuki.liveIn(mountcook);
		Person samuel = new Person("Samuel", "Flat");
		samuel.liveIn(mountvic);
		Person jason = new Person("Jason", "House");
		jason.liveIn(karori);
		
		abraham.printAddress();
		
		abraham.setStreet("Broadway street");
		abraham.changeCity(newmarket);
		
		abraham.printAddress();
		
	}

}
