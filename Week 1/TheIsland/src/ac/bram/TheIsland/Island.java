package ac.bram.TheIsland;

import java.util.ArrayList;
import java.util.List;

public class Island {

	private int width, height;
	private Entity[][] islandEntity;
	private char[][] islandSpace;
	private char[][] islandFeatures;
	private List<Animal> animals = new ArrayList<>();
	private List<Plant> plants = new ArrayList<>();

	public Island(int width, int height) {
		this.width = width;
		this.height = height;
		this.islandEntity = new Entity[width][height];
		this.islandSpace = new char[width][height];
		this.islandFeatures = new char[width][height];
		this.clearIsland();
	}

	public void createStage(char[][] stage) {
		this.islandFeatures = stage;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void addEntity (Entity e) {
		int eX = e.getX();
		int eY = e.getY();
		Entity eAtLocation = this.islandEntity[eX][eY];
		char featureAtLocation = this.islandFeatures[eX][eY];

		if (e instanceof Animal) {
			switch(((Animal) e).getType()) {
			case 0: // land
			case 1: // bird
				if (featureAtLocation == '.' && eAtLocation == null) {
					this.islandEntity[eX][eY] = e;
					this.animals.add((Animal) e);}
				break;
			case 2: // amphibian
			case 3: // water
				if (featureAtLocation == '~' && eAtLocation == null) {
					this.islandEntity[eX][eY] = e;
					this.animals.add((Animal) e);}
				break;
			}
		} else if (e instanceof Plant) {
			if (featureAtLocation == '.' && eAtLocation == null) {
				this.islandEntity[eX][eY] = e;
				this.plants.add((Plant) e);}
		}

	}

	public void updateIsland() {
		this.clearIsland();
		this.updateEntities();
		this.draw();
	}

	private void clearIsland() {
		for (int x = 0; x < width; x++)
			for(int y = 0; y < height; y++) {
				this.islandSpace[x][y] = ' ';
			}
	}

	private void updateEntities() {

		this.removeDeadEntity();

		this.putFeatures();

		this.putPlant();

		this.putHerbivore();
	}

	private void draw() {
		for (int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				System.out.print(this.islandSpace[x][y]);
			}
			System.out.println();
		}
	}

	private void removeDeadEntity() {
		//remove dead bug
		int index = 0;
		ArrayList<Integer> deadIndex = new ArrayList<Integer>();
		for(Animal b : animals) {
			if (!b.isAlive()) {
				deadIndex.add(index);
			}
			index++;
		}
		index = 0;
		if (deadIndex.size() > 0) {
			for (Integer i : deadIndex) {
				index = i.intValue();
				animals.remove(index);
			}
		}

		//remove dead plant
		index = 0;
		deadIndex = new ArrayList<Integer>();
		for(Plant b : plants) {
			if(!b.isAlive()) {
				deadIndex.add(index);
			}
			index++;
		}
		index = 0;
		if (deadIndex.size() > 0) {
			for (Integer i : deadIndex) {
				index = i.intValue();
				plants.remove(index);
			}
		}
	}

	private void putFeatures() {
		for (int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				this.islandSpace[x][y] = this.islandFeatures[x][y];
			}
		}
	}

	private void putPlant() {
		for(Plant e : plants) {
			if (e instanceof Plant) {
				((Plant) e).grow();
				this.islandSpace[e.getX()][e.getY()] = e.getSymbol();
				this.islandEntity[e.getX()][e.getY()] = e;
			}
		}

	}

	private void putHerbivore() {
		for (Animal b : animals) {
			if (b instanceof Herbivore) {
				//System.out.println("Herbi :" + animals.size() + " " + b.getX() + ", " + b.getY());
				if (b.isHungry() && !b.haveTarget()) {
					if (plants.size() > 0) {
						//System.out.println("call");
						smellForPlant((Herbivore) b);
					}
				}
				//System.out.println("call");
				b.move();
				//System.out.println(islandSpace[b.getX()][b.getY()]);
				if (islandSpace[b.getX()][b.getY()] == '.' && 
						(b.getType() == 0 || b.getType() == 1)) {
					//System.out.println("call 1");
					islandSpace[b.getX()][b.getY()] = b.getSymbol();
					islandEntity[b.getX()][b.getY()] = b;
				} else if (this.islandEntity[b.getX()][b.getY()] instanceof Plant && b.isHungry()) {
					//System.out.println("call 3");
					Plant p = checkForPlantAt(b.getX(), b.getY());
					b.eat(p.getEaten(b.getEnergy()));

					islandSpace[b.getLastX()][b.getLastY()] = b.getSymbol();
					islandEntity[b.getLastX()][b.getLastY()] = b;
					islandSpace[b.getX()][b.getY()] = '*';
					b.setX(b.getLastX());
					b.setY(b.getLastY());
					b.noTarget();
					b.notHungry();
					b.goOtherWay();
				} else if (islandSpace[b.getX()][b.getY()] == '~' && b.isThirsty()) {
					b.notThirsty();
					islandSpace[b.getLastX()][b.getLastY()] = b.getSymbol();
					islandEntity[b.getLastX()][b.getLastY()] = b;
					b.setX(b.getLastX());
					b.setY(b.getLastY());
					b.goOtherWay();
				} else if (islandSpace[b.getX()][b.getY()] == '~' && 
						(b.getType() == 2 || b.getType() == 3)) {
					islandSpace[b.getX()][b.getY()] = b.getSymbol();
					islandEntity[b.getX()][b.getY()] = b;
				} else {
					islandSpace[b.getLastX()][b.getLastY()] = b.getSymbol();
					islandEntity[b.getLastX()][b.getLastY()] = b;
					b.setX(b.getLastX());
					b.setY(b.getLastY());
					b.goOtherWay();
				}
			}
		}
	}

	private void smellForPlant(Herbivore b) {
		int r = b.getR();
		int sX = b.getX() - r;
		if(sX <= 1) {sX = 1;}
		int sY = b.getY() - r;
		if(sY <= 1) {sY = 1;}
		int eX = b.getX() + r;
		if(eX >= this.width - 1) {eX = this.width - 1;}
		int eY = b.getY() + r;
		if(eY >= this.height - 1) {eY = this.height - 1;}

		for (Plant p : plants) {

			if (p.getX() > sX && p.getX() < eX &&
					p.getY() > sY && p.getY() < eY) {
				b.setTarget(p.getX(), p.getY());
				//System.out.println("Target:" + p.getX() + ", " + p.getY());
				break;
			}
		}
	}

	private Plant checkForPlantAt(int x, int y) {
		for(Plant p : plants) {
			if (p.getX() == x && p.getY() == y) {
				return p;
			}
		}
		return null;
	}

	private void searchWater(Animal b) {
		int r = b.getR();
		int sX = b.getX() - r;
		if(sX <= 1) {sX = 1;}
		int sY = b.getY() - r;
		if(sY <= 1) {sY = 1;}
		int eX = b.getX() + r;
		if(eX >= this.width - 1) {eX = this.width - 1;}
		int eY = b.getY() + r;
		if(eY >= this.height - 1) {eY = this.height - 1;}

		for (int x = sX; x < eX; x++) {
			for (int y = sY; y < eY; y++) {
				if (this.islandFeatures[x][y] == '~')
					b.getWater(x, y);
				break;
			}
		}
	}

	private void putCarnivore() {
		for (Animal b : animals) {
			if (b instanceof Carnivore) {
				//System.out.println("Herbi :" + animals.size() + " " + b.getX() + ", " + b.getY());
				if (b.isHungry() && !b.haveTarget()) {
					if (animals.size() > 0) {
						//System.out.println("call");
						smellForPrey((Carnivore) b);
					}
				}
				//System.out.println("call");
				b.move();
				//System.out.println(islandSpace[b.getX()][b.getY()]);
				if (islandSpace[b.getX()][b.getY()] == '.' && 
						(b.getType() == 0 || b.getType() == 1)) {
					//System.out.println("call 1");
					islandSpace[b.getX()][b.getY()] = b.getSymbol();
					islandEntity[b.getX()][b.getY()] = b;
				} else if (this.islandEntity[b.getX()][b.getY()] instanceof Plant && b.isHungry()) {
					//System.out.println("call 3");
					Plant p = checkForPlantAt(b.getX(), b.getY());
					b.eat(p.getEaten(b.getEnergy()));

					islandSpace[b.getLastX()][b.getLastY()] = b.getSymbol();
					islandEntity[b.getLastX()][b.getLastY()] = b;
					islandSpace[b.getX()][b.getY()] = '*';
					b.setX(b.getLastX());
					b.setY(b.getLastY());
					b.noTarget();
					b.notHungry();
					b.goOtherWay();
				} else if (islandSpace[b.getX()][b.getY()] == '~' && b.isThirsty()) {
					b.notThirsty();
					islandSpace[b.getLastX()][b.getLastY()] = b.getSymbol();
					islandEntity[b.getLastX()][b.getLastY()] = b;
					b.setX(b.getLastX());
					b.setY(b.getLastY());
					b.goOtherWay();
				} else if (islandSpace[b.getX()][b.getY()] == '~' && 
						(b.getType() == 2 || b.getType() == 3)) {
					islandSpace[b.getX()][b.getY()] = b.getSymbol();
					islandEntity[b.getX()][b.getY()] = b;
				} else {
					islandSpace[b.getLastX()][b.getLastY()] = b.getSymbol();
					islandEntity[b.getLastX()][b.getLastY()] = b;
					b.setX(b.getLastX());
					b.setY(b.getLastY());
					b.goOtherWay();
				}
			}
		}
	}

	private void smellForPrey (Carnivore b) {
		int sX = b.getX() - b.getR();
		if(sX <= 1) {sX = 1;}
		int sY = b.getY() - b.getR();
		if(sY <= 1) {sY = 1;}
		int eX = b.getX() + b.getR();
		if(eX >= this.width - 1) {eX = this.width - 1;}
		int eY = b.getY() + b.getR();
		if(eY >= this.height - 1) {eY = this.height - 1;}

		for (Animal p : animals) {

			if (p.getX() > sX && p.getX() < eX &&
					p.getY() > sY && p.getY() < eY) {
				b.setTarget(p.getX(), p.getY());
				b.isTargeted();
				//System.out.println("Target:" + p.getX() + ", " + p.getY());
				break;
			}
		}
	}

private Herbivore checkForPreyAt(int x, int y) {
	for(Animal p : animals) {
		if (p.getX() == x && p.getY() == y && p instanceof Herbivore) {
			return (Herbivore) p;
		}
	}
	return null;
}
}