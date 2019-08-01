package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {

	public Brick(double x, double y, Color c) {
		super(x + 2, y + 2, 26, 11);
		super.setFill(c);
	}
	
}
