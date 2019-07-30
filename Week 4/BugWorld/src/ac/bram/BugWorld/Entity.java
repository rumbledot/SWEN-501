package ac.bram.BugWorld;

public class Entity {
	private double x, y;
	private Boolean haveTarget = false;
	protected Boolean isHungry = false;
	private Boolean isHunting = false;
	private Boolean isTargeted = false;
	private Boolean isAlive = true;
	
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double X() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double Y() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Boolean haveTarget() {
		return haveTarget;
	}
	
	public void gotTarget() {
		haveTarget = true;
	}

	public void noTarget() {
		this.haveTarget = false;
	}

	public Boolean isHungry() {
		return isHungry;
	}

	public void notHungry() {
		this.isHungry = false;
	}
	
	public void gettingHungry() {
		this.isHungry = true;
	}

	public Boolean isHunting() {
		return isHunting;
	}

	public void notHunting() {
		this.isHunting = false;
	}

	public Boolean isTargeted() {
		return isTargeted;
	}

	public void notTargeted() {
		this.isTargeted = false;
	}

	public Boolean isAlive() {
		return isAlive;
	}

	public void dead() {
		this.isAlive = false;
	}
	
}
