package ac.bram.TheIsland;

public class Kiwi extends Herbivore {

	Island island;

	public Kiwi(int x, int y, int energy, int dX, int dY, int r, int type, char symbol, Island island) {
		super(x, y, energy, dX, dY, r, 0, 'k', island);
	}

	@Override
	public void move() {
		//System.out.println(isAlive + ":" + getX() + " ,"+ getY());
		//Kiwi just roaming around
		setLastX(getX());
		setLastY(getY());

		setX(getX() + getdX());
		if(getX() < 1 || getX() >= getWorldWidth() - 1) {
			setdX(-getdX());
			setX(getX() + getdX());
		}

		setY(getY() + getdY());
		if(getY() < 1 || getY() >= getWorldHeight() - 1) {
			setdY(-getdY());
			setY(getY() + getdY());
		}
		//System.out.println(getX() + ", " + getY());
	}

}
