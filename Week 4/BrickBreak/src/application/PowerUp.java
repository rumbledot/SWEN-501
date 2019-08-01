package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PowerUp extends Rectangle {
	
	public PowerUp(double x, double y) {
		super(x, y, 20, 11);
		super.setArcHeight(5);
		super.setArcWidth(5);
	}
	
	public void move() {
		super.setY(super.getY() + 2);
	}

}
