package ac.bram.BugWorld;

import java.io.File;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Animal extends Entity{

	private double x, y;
	private Color c;
	private Circle bug;
	private Scene s;
	private float dx = -1.5f, dy = -1.5f;
	private float speed = 1.5f;
	private float r = 5.0f;
	private int maxFull = 100, hungryLevel = 100;
	private Entity target;
	private double tX, tY;

	public Animal(double x, double y, Color c, String file, float r, float speed) {
		super(x, y);
		bug = new Circle(x, y, r);
		bug.setFill(c);
		try {
			File f = new File(file);
			Image img = new Image(f.toURI().toString());
			bug.setFill(new ImagePattern(img));
		} catch(Exception e) {

		}

		this.x = bug.getTranslateX();
		this.y = bug.getTranslateY();
		this.c = c;
		int ran = (int) (Math.random() * 100 + 1);
		if (ran > 10 && ran < 40) { dx = speed; dy = speed; }
		else if (ran > 50 && ran < 90) { dx = -speed; dy = -speed; }
		else {dx = speed; dy = -speed; }
	}

	public void move() {

		hungryLevel--;
		System.out.println(hungryLevel);

		if (hungryLevel <= 50) gettingHungry();
		if (hungryLevel <= 0) dead();

		if(isAlive()) {
			Scene s = Main.getScene();
			Bounds b = bug().localToScene(bug().getBoundsInLocal());

			if (b.getCenterX() < 0.0f ||
					b.getCenterX() > s.getWidth()) {
				setDx(-dx());
			}
			if (b.getCenterY() < 0.0f ||
					b.getCenterY() > s.getHeight()) {
				setDy(-dy());
			}
		}

	}

	public void update() {
		this.setX(this.X() + dx());
		this.setY(this.Y() + dy());
	}

	public float dx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float dy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public void getScene(Scene s) {
		this.s = s;
	}

	public double X() {
		return bug.getTranslateX();
	}

	public void setX(double x) {
		bug.setTranslateX(x);
	}

	public double Y() {
		return bug.getTranslateY();
	}

	public void setY(double y) {
		bug.setTranslateY(y);
	}

	public float r() {
		return this.r;
	}

	public Circle bug() {
		return bug;
	}

	public Color getColor() {
		return c;
	}

	public int getHungryLevel() {
		return hungryLevel;
	}

	public void getHungry() {
		hungryLevel--;
	}

	public void setHungryLevel(int hungryLevel) {
		this.hungryLevel = hungryLevel;
	}

	public int maxFull() {
		return maxFull;
	}

	public void setTarget(Entity t) {
		target = t;
		gotTarget();
	}
	
	public void setTargetPos(double x, double y) {
		tX = x;
		tY = y;
	}
	
	public double targetX() {
		return tX;
	}
	
	public double targetY() {
		return tY;
	}

	public Entity getTarget() {
		return target;
	}

}