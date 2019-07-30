package ac.bram.BugWorld;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Plant extends Entity {

	private int maxGrow = 100;
	private int levelGrow = 0;
	private Circle plant;
	
	private Scene s;
	
	public Plant(double x, double y) {
		super(x, y);
		plant = new Circle(x, y, 15);
		setX(plant.getTranslateX());
		setY(plant.getTranslateY());
		File f = new File("C:/Users/Abram/Documents/MSwDev 2019/grass.png");
		Image img = new Image(f.toURI().toString());
		plant.setFill(new ImagePattern(img));
	}
	
	public void grow() {
		levelGrow++;
		if (levelGrow >= maxGrow) { levelGrow = maxGrow; }
	}
	
	public int getGrow() {
		return levelGrow;
	}
	
	public Circle plant() {
		return plant;
	}
	
	public void getScene(Scene s) {
		this.s = s;
	}
	
	public void getEaten(float e) {
		this.levelGrow -= e;
		if (levelGrow <= 0) this.dead();
	}

	public int getEnergy() {
		if (levelGrow < 0) return 0;
		return levelGrow;
	}
	
}
