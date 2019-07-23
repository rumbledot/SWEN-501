package ac.bram.Contacts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

	private Map<String, Person> families = new HashMap<String, Person>();
	private Map<String, Person> friends = new HashMap<String, Person>();
	private Map<String, Business> businesses = new HashMap<String, Business>();
	private Map<String, Person> contacts = new HashMap<String, Person>();

	private Scanner input = new Scanner(System.in);

	private String ui = "";
	private int nUI = 0;

	public Main() {
		this.mainMenu();
	}

	private void mainMenu() {

		do {
			System.out.println("----------------------");
			System.out.println("   CONTACTS PLANNER   ");
			System.out.println("----------------------");
			System.out.println("1 > Individual contact");
			System.out.println("2 > Business contact");
			System.out.println("3 > Manage friends");
			System.out.println("4 > Manage family");
			System.out.println("5 > Save");
			System.out.println("6 > Load");
			System.out.println("0 > Exit");
			System.out.println("----------------------");

			ui = input.nextLine();
			nUI = Integer.parseInt(ui);

			switch (nUI) {
			case 1:
				this.menuIndividu();
				break;
			case 2:
				this.menuBusiness();
				break;
			case 3:
				this.menuManageFriend();
				break;
			case 4:
				this.menuManageFamily();
				break;
			case 5:
				this.doSave();
				break;
			case 6:
				this.doLoad();
				break;
			}

		} while (nUI != 0); 
	}

	/**
	 * 
	 * INDIVIDUAL PART
	 * 
	 */

	private void menuIndividu() {
		do {
			System.out.println("----------------------");
			System.out.println("  INDIVIDUAL PLANNER  ");
			System.out.println("----------------------");
			System.out.println("1 > Add new");
			System.out.println("2 > Edit");
			System.out.println("3 > Remove");
			System.out.println("4 > List");
			System.out.println("5 > Back");
			System.out.println("----------------------");

			ui = input.nextLine();
			nUI = Integer.parseInt(ui);

			switch (nUI) {
			case 1:
				this.addIndividu();
				break;
			case 2:
				this.editIndividu();
				break;
			case 3:
				this.removeIndividu();
				break;
			case 4:
				this.listIndividu();
				break;
			}

		} while (nUI != 5);

	}

	private void addIndividu() {
		System.out.println("----------------------");
		System.out.println("  INDIVIDUAL PLANNER  ");
		System.out.println("------------------ADD-");
		System.out.print("Name 		: ");
		String uName = input.nextLine();
		System.out.print("Phone 	: ");
		String uPhone = input.nextLine();
		System.out.print("Email 	: ");
		String uEmail = input.nextLine();
		System.out.print("Address 	: ");
		String uAddress = input.nextLine();
		System.out.print("Birthday (dd/MM/yyyy) :");
		String uBday = input.nextLine();
		System.out.print("Status 	: ");
		String uRel = input.nextLine();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(uBday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Person newContact = new Person(uName, uPhone, uEmail, uAddress, date, uRel);
		contacts.put(uName, newContact);
	}

	private void editIndividu() {
		System.out.print("Get Contact by name : ");
		String name = input.nextLine();
		Person p = this.contacts.get(name);

		if (p != null) {
			do {
				System.out.println("----------------------");
				System.out.println("  INDIVIDUAL PLANNER  ");
				System.out.println("-----------------EDIT-");
				System.out.println("1  > Name");
				System.out.println("2  > Add phone");
				System.out.println("3  > Remove phone");
				System.out.println("4  > Add email");
				System.out.println("5  > Remove email");
				System.out.println("6  > Change address");
				System.out.println("7  > Change status");
				System.out.println("8  > Add GST");
				System.out.println("9  > Add note");
				System.out.println("10 > Add employer");
				System.out.println("11 > Remove employer");
				System.out.println("12 > Add employee");
				System.out.println("13 > Remove employee");
				System.out.println("14 > Back");
				System.out.println("----------------------");

				ui = input.nextLine();
				nUI = Integer.parseInt(ui);

				switch (nUI) {
				case 1:
					System.out.print("Change name to : ");
					String uName = input.nextLine();
					p.setName(uName);
					break;
				case 2:
					System.out.print("Add new phone : ");
					String ph = input.nextLine();
					p.setPhone(ph);
					break;
				case 3:
					int index = 1;
					ArrayList<String> phList = p.getPhone();
					System.out.println("Remove phone");
					for (String phN : phList) {
						System.out.println(index + " > " + phN);
						index++;
					}
					System.out.print("which index to remove : ");
					index = input.nextInt();
					p.removePhone(index - 1); 
					break;
				case 4:
					System.out.print("Add new email : ");
					String em = input.nextLine();
					p.setEmail(em);
					break;
				case 5:
					index = 1;
					ArrayList<String> emList = p.getEmail();
					System.out.println("Remove email");
					for (String emN : emList) {
						System.out.println(index + " > " + emN);
						index++;
					}
					System.out.print("which index to remove : ");
					index = input.nextInt();
					p.removeEmail(index - 1); 
					break;
				case 6:
					System.out.println("Old Address : ");
					System.out.println(p.getAddress());
					System.out.print("Change to : ");
					String nAddress = input.nextLine();
					p.setAddress(nAddress);
					break;
				case 7:
					System.out.println("Old Status : ");
					System.out.println(p.getRelationship());
					System.out.print("Change to : ");
					String nS = input.nextLine();
					p.setRelationship(nS);
					break;
				case 8:
					System.out.print("Add GST : ");
					String nGST = input.nextLine();
					p.setGST(nGST);
					break;
				case 9:
					System.out.println("Old Note : ");
					System.out.println(p.seeNote());
					System.out.print("Change to : ");
					String nNt = input.nextLine();
					p.addNote(nNt);
					break;
				case 10:
					System.out.print("Add new employer : ");
					String employer = input.nextLine();
					Person emp = contacts.get(employer);
					p.setEmployer(emp);
					break;
				case 11:
					System.out.print("Remove employer : ");
					p.setEmployer(null);
					break;
				case 12:
					System.out.print("Add new employee : ");
					String employee = input.nextLine();
					emp = contacts.get(employee);
					p.addEmployee(emp);
					break;
				case 13:
					index = 0;
					System.out.print("Remove employee : ");
					employee = input.nextLine();
					p.removeEmployee(employee);
					break;
				}
			} while (nUI != 14);
		}
	}

	private void removeIndividu() {
		System.out.println("----------------------");
		System.out.println("  INDIVIDUAL PLANNER  ");
		System.out.println("---------------REMOVE-");

		System.out.print("Remove Contact by name : ");
		String name = input.nextLine();
		if (contacts.containsKey(name)) {
			this.contacts.remove(name);
		} else {
			System.out.println("Contact not found");
		}
		System.out.println();
	}

	private void listIndividu() {
		System.out.println("----------------------");
		System.out.println("  INDIVIDUAL PLANNER  ");
		System.out.println("-----------------LIST-");
		int index = 1;
		for (String name : contacts.keySet()) {
			System.out.println(index + " > " + name);
			index++;
		}
		System.out.println("----------------------");
		System.out.println();
	}

	private void menuBusiness() {
		do {
			System.out.println("----------------------");
			System.out.println("   BUSINESS PLANNER   ");
			System.out.println("----------------------");
			System.out.println("1 > Add new");
			System.out.println("2 > Edit");
			System.out.println("3 > Remove");
			System.out.println("4 > List");
			System.out.println("5 > Back");
			System.out.println("----------------------");

			ui = input.nextLine();
			nUI = Integer.parseInt(ui);

			switch (nUI) {
			case 1:
				this.addBusiness();
				break;
			case 2:
				this.editBusiness();
				break;
			case 3:
				this.removeBusiness();
				break;
			case 4:
				this.listBusiness();
				break;
			}

		} while (nUI != 5);
	}

	private void addBusiness() {
		System.out.println("----------------------");
		System.out.println("   BUSINESS PLANNER   ");
		System.out.println("------------------ADD-");
		System.out.print("Name 		: ");
		String uName = input.nextLine();
		System.out.print("Phone 	: ");
		String uPhone = input.nextLine();
		System.out.print("Email 	: ");
		String uEmail = input.nextLine();
		System.out.print("Address 	: ");
		String uAddress = input.nextLine();
		System.out.print("GST	    :");
		String uGST = input.nextLine();

		Business newContact = new Business(uName, uPhone, uEmail, uAddress, uGST);
		businesses.put(uName, newContact);
	}

	private void editBusiness() {
		System.out.print("Get Contact by name : ");
		String name = input.nextLine();
		Business p = this.businesses.get(name);

		if (p != null) {
			do {
				System.out.println("----------------------");
				System.out.println("   BUSINESS PLANNER   ");
				System.out.println("-----------------EDIT-");
				System.out.println("1  > Name");
				System.out.println("2  > Add phone");
				System.out.println("3  > Remove phone");
				System.out.println("4  > Add email");
				System.out.println("5  > Remove email");
				System.out.println("6  > Change address");
				System.out.println("7  > Add GST");
				System.out.println("8  > Add note");
				System.out.println("9 > Back");
				System.out.println("----------------------");

				ui = input.nextLine();
				nUI = Integer.parseInt(ui);

				switch (nUI) {
				case 1:
					System.out.print("Change name to : ");
					String uName = input.nextLine();
					p.setName(uName);
					break;
				case 2:
					System.out.print("Add new phone : ");
					String ph = input.nextLine();
					p.setPhone(ph);
					break;
				case 3:
					int index = 1;
					ArrayList<String> phList = p.getPhone();
					System.out.println("Remove phone");
					for (String phN : phList) {
						System.out.println(index + " > " + phN);
					}
					System.out.print("which index to remove : ");
					index = input.nextInt();
					p.removePhone(index - 1); 
					break;
				case 4:
					System.out.print("Add new email : ");
					String em = input.nextLine();
					p.setEmail(em);
					break;
				case 5:
					index = 1;
					ArrayList<String> emList = p.getEmail();
					System.out.println("Remove email");
					for (String emN : emList) {
						System.out.println(index + " > " + emN);
					}
					System.out.print("which index to remove : ");
					index = input.nextInt();
					p.removeEmail(index - 1); 
					break;
				case 6:
					System.out.println("Old Address : ");
					System.out.println(p.getAddress());
					System.out.print("Change to : ");
					String nAddress = input.nextLine();
					p.setAddress(nAddress);
					break;
				case 7:
					System.out.print("Add GST : ");
					String nGST = input.nextLine();
					p.setGST(nGST);
					break;
				case 8:
					System.out.println("Old Note : ");
					System.out.println(p.seeNote());
					System.out.print("Change to : ");
					String nNt = input.nextLine();
					p.addNote(nNt);
					break;
				}
			} while (nUI != 9);
		}
	}

	private void removeBusiness() {
		System.out.println("----------------------");
		System.out.println("   BUSINESS PLANNER   ");
		System.out.println("---------------REMOVE-");

		System.out.print("Remove Contact by name : ");
		String name = input.nextLine();
		if (businesses.containsKey(name)) {
			this.businesses.remove(name);
		} else {
			System.out.println("Contact not found");
		}
		System.out.println();
	}

	private void listBusiness() {
		System.out.println("----------------------");
		System.out.println("   BUSINESS PLANNER   ");
		System.out.println("-----------------LIST-");
		int index = 1;
		for (String name : businesses.keySet()) {
			System.out.println(index + " > " + name);
			index++;
		}
		System.out.println("----------------------");
		System.out.println();
	}

	private void menuManageFriend() {
		do {
			System.out.println("----------------------");
			System.out.println("     MANAGE FRIEND    ");
			System.out.println("----------------------");
			System.out.println("1 > Add");
			System.out.println("2 > Remove");
			System.out.println("3 > List");
			System.out.println("4 > Back");
			System.out.println("----------------------");

			ui = input.nextLine();
			nUI = Integer.parseInt(ui);

			switch (nUI) {
			case 1:
				System.out.println("Add new friend : ");
				String name = input.nextLine();
				if (contacts.containsKey(name)) {
					Person p = contacts.get(name);
					friends.put(name, p);
				}
				break;
			case 2:
				System.out.println("Remove friend : ");
				name = input.nextLine();
				if (contacts.containsKey(name)) {
					friends.remove(name);
				}
				break;
			case 3:
				System.out.println("---------------");
				System.out.println("  Friend List  ");
				System.out.println("---------------");
				
				for (String n : friends.keySet()) {
					System.out.println(" > " + n);
				}
				
				System.out.println("---------------");
				break;
			}
		} while (nUI != 4);
	}

	private void menuManageFamily() {
		do {
			System.out.println("----------------------");
			System.out.println("     MANAGE FAMILY    ");
			System.out.println("----------------------");
			System.out.println("1 > Add");
			System.out.println("2 > Remove");
			System.out.println("3 > List");
			System.out.println("4 > Back");
			System.out.println("----------------------");

			ui = input.nextLine();
			nUI = Integer.parseInt(ui);

			switch (nUI) {
			case 1:
				System.out.println("Add new family : ");
				String name = input.nextLine();
				if (contacts.containsKey(name)) {
					Person p = contacts.get(name);
					families.put(name, p);
				}
				break;
			case 2:
				System.out.println("Remove family : ");
				name = input.nextLine();
				if (contacts.containsKey(name)) {
					families.remove(name);
				}
				break;
			case 3:
				System.out.println("---------------");
				System.out.println("  Family List  ");
				System.out.println("---------------");
				
				for (String n : families.keySet()) {
					System.out.println(" > " + n);
				}
				
				System.out.println("---------------");
				break;
			}
		} while (nUI != 4);	}

	private void doSave() {
		

	}

	private void doLoad() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new Main();
	}

}
