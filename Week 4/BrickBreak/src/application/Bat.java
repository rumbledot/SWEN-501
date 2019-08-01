package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bat extends Rectangle {
	
	public Bat(double x, double y) {
		super(x, y, 50, 10);
		super.setFill(Color.BLUE);
	}

}
