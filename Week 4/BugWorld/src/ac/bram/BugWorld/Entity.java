package ac.bram.BugWorld;

public class Entity {
	private double x, y;
	
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
	
}
