package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle{

	public Ball(double x, double y) {
		super(x, y, 8);
		super.setFill(Color.RED);
	}
}
