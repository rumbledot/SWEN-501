package ac.bram.TheIsland;

public class Animal extends Entity {

	private int energy, energyLevel;
	private int dX, dY;
	private int r;
	private int thirstLevel = 50;
	private int waterX = 0, waterY = 0;
	private int lastX, lastY;
	private int targetX = 0, targetY = 0;
	private int worldWidth, worldHeight;
	protected int type = 0;
	protected boolean isAlive = true;
	protected boolean isHungry = false;
	protected boolean isThirsty = false;
	protected boolean haveTarget = false;
	protected boolean isTargeted = false;
	private Island island;
	
	public Animal (int x, int y, int energy, int dX, int dY, int r, int type, char symbol, Island island) {
		super(x, y, symbol);
		this.energy = energy; // max energy
		this.energyLevel = this.energy; // current energy level
		this.dX = dX; // direction and speed
		this.dY = dY;
		this.r = r; // browse radius
		this.type = type; // 0 = land, 1 = bird, 2 = water, 3 = amphibian
		this.island = island; // the place where it lives
		this.worldHeight = island.getHeight(); 
		this.worldWidth = island.getWidth();
		this.lastX = x; this.lastY = y;
	}
	
	public void eat(int quantity) {
		this.energyLevel += quantity;
		if (this.energyLevel >= this.energy) { this.energyLevel = this.energy; }
		this.isHungry = false; this.haveTarget = false;
	}
	
	public void move() {
		//System.out.println(isAlive + " " + isHungry + " " + haveTarget + ":" + getX() + " ,"+ getY() + ":" + targetX + " ," + targetY);
		this.energyLevel--; this.thirstLevel--;
		if (this.energyLevel <= (this.energy / 4) * 3) { // get hungry when energy level drop to 3/4 its time
			isHungry = true;
		} else if (this.energyLevel >= this.energy) { // check if energy level higher then default energy after eating
			isHungry = false; haveTarget = false;
			this.energyLevel = this.energy;
		}
		if (this.thirstLevel <= 10) { 
			this.isThirsty = true;
		}
		if (this.energyLevel <= 0 || this.thirstLevel < 0) { // when energy drop below or 0 then it dies, flag for removal
			isAlive = false;
		}
		if (isHungry & haveTarget) { // movement behavior when the bug is hungry and have targeted a food
			//System.out.println("call 2");
			this.lastX = getX();
			this.lastY = getY();
			if (getX() < targetX) {
				setX(getX() + Math.abs(this.dX));
			} else if (getX() > targetX) {
				setX(getX() - Math.abs(this.dX));
			} else {
				setX(getX() + 0);
			}
			if (getY() < targetY) {
				setY(getY() + Math.abs(this.dY));
			} else if (getY() > targetY) {
				setY(getY() - Math.abs(this.dY));
			} else {
				setY(getY() + 0);
			}

		} else if (this.isThirsty) {
			this.lastX = getX();
			this.lastY = getY();
			if (getX() < waterX) {
				setX(getX() + Math.abs(this.dX));
			} else if (getX() > waterX) {
				setX(getX() - Math.abs(this.dX));
			} else {
				setX(getX() + 0);
			}
			if (getY() < waterY) {
				setY(getY() + Math.abs(this.dY));
			} else if (getY() > waterY) {
				setY(getY() - Math.abs(this.dY));
			} else {
				setY(getY() + 0);
			}
		} else { // movement behavior when the bug is hungry and don't have targeted a food
			//System.out.println("call 1");
			this.lastX = getX();
			this.lastY = getY();
			setX(getX() + this.dX);
			if(getX() < 1 || getX() >= this.worldWidth - 1) {
				this.dX = -this.dX;
				setX(getX() + this.dX);
			}

			setY(getY() + this.dY);
			if(getY() < 1 || getY() >= this.worldHeight - 1) {
				this.dY = -this.dY;
				setY(getY() + this.dY);
			}
			//System.out.println(getX() + ", " + getY());
		}
	}
	
	public void setTarget(int tx, int ty) {
		this.targetX = tx;
		this.targetY = ty;
	}
	
	public void getWater(int tx, int ty) {
		this.waterX = tx;
		this.waterY = ty;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void isDead() {
		this.isAlive = false;
	}

	public boolean isHungry() {
		return isHungry;
	}

	public void notHungry() {
		this.isHungry = false;
	}

	public boolean isThirsty() {
		return isThirsty;
	}

	public void notThirsty() {
		this.isThirsty = false;
		this.thirstLevel = 50;
	}

	public boolean haveTarget() {
		return haveTarget;
	}

	public void noTarget() {
		this.haveTarget = false;
	}

	public boolean isTargeted() {
		return isTargeted;
	}
	
	public int getType() {
		return type;
	}
	
	public int getLastX() {
		return this.lastX;
	}
	
	public int getLastY() {
		return this.lastY;
	}
	
	public void goOtherWay() {
		this.dX = -this.dX;
		this.dY = -this.dY;
		//System.out.println(this.dX +"," +this.dY);
	}
	
	public void printWorld() {
		System.out.println(this.worldHeight + ", " + this.worldWidth);
	}
	
	public int getR() {
		return this.r;
	}

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}
	
}
