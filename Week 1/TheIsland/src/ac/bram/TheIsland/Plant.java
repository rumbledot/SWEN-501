package ac.bram.TheIsland;

public class Plant extends Entity {

	private int energy, energyLevel;
	private int growthRate;
	private boolean isAlive = true;
	
	public Plant(int x, int y, int energy, int growthRate, char symbol) {
		super(x, y, symbol);
		this.energy = energy;
		this.energyLevel = energy;
		this.growthRate = growthRate;
	}
	
	public int getEaten(int q) { // called when eaten by a herbivore
		int giveEnergy = this.energyLevel - q;
		if (this.energyLevel <= 0) {
			giveEnergy = 1;
			isAlive = false;
		}
		return giveEnergy;
}
	
	public void grow() {
		this.energyLevel += this.growthRate;
		if (this.energyLevel >= this.energy) this.energyLevel = this.energy;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void isDead() {
		this.isAlive = false;
	}
}