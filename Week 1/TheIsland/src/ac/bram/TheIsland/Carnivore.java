package ac.bram.TheIsland;

public class Carnivore extends Animal {

	Herbivore prey = null;
	
	public Carnivore(int x, int y, int energy, int speedX, int speedY, int r, int type, char symbol, Island island) {
		super(x, y, energy, speedX, speedY, r, type, symbol, island);
	}
	
	public void noTarget() {
		haveTarget = false;
		this.prey = null;
	}
	
	public void setPrey(Herbivore bug) {
		this.prey = bug;
	}
	
	public Herbivore getPrey() {
		return this.prey;
	}

}
