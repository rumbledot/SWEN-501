package ac.bram.TheIsland;

public abstract class Entity {
	
	int x, y;
	char symbol;
	
	public Entity (int x, int y, char symbol) {
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public char getSymbol() {
		return this.symbol;
	}

}
