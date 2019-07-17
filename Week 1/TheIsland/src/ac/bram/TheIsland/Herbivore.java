package ac.bram.TheIsland;

public class Herbivore extends Animal {

	Island island;
	
	public Herbivore(int x, int y, int energy, int speedX, int speedY, int r, int type, char symbol, Island island) {
		super(x, y, energy, speedX, speedY, r, type, symbol, island);
	}
	
	public void beingTargeted() {
		isTargeted = true;
	}
}