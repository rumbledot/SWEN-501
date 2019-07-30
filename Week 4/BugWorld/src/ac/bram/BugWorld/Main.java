package ac.bram.BugWorld;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;

import java.awt.Label;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class Main extends Application {

	private int width = 400, height = 300;
	private static Scene s;
	private BorderPane r;
	private Group g;

	private String[] herbiFiles = new String[] 
			{ "C:/Users/Abram/Documents/MSwDev 2019/cow.png",
			"C:/Users/Abram/Documents/MSwDev 2019/Sheep.png" };
	private float[] herbiSize = new float[]
			{ 25, 15 };

	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Herbivore> herbivores = new ArrayList<>();
	private static ArrayList<Plant> plants = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {

		HBox v = new HBox();
		g = new Group();
		r = new BorderPane();
		s = new Scene(g, width, height);
		File f = new File("C:/Users/Abram/Documents/MSwDev 2019/Field.png");
		Image img = new Image(f.toURI().toString());
		s.setFill(new ImagePattern(img));

		Button addBug = new Button();
		addBug.setText("Add a bug");
		v.getChildren().add(addBug);

		Text lblInfo = new Text();
		lblInfo.setText("Population : ");
		v.getChildren().add(lblInfo);

		Text lblPopulation = new Text();
		lblPopulation.setText("0");
		v.getChildren().add(lblPopulation);

		g.getChildren().add(v);

		/*		r.setTop(v);
		r.setCenter(g);*/

		//this.bug(100, 100, Color.RED, "C:/Users/Abram/Documents/MSwDev 2019/Charmander.png");
		//this.bug(150, 150, Color.GREEN, "C:/Users/Abram/Documents/MSwDev 2019/Squirtle.png");
		//this.bug(200, 200, Color.BLUE, "C:/Users/Abram/Documents/MSwDev 2019/Bulbasaur.png");

		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				removeDeadEntity();

				for (Plant b : plants) {
					b.grow();
				}
				
				for (Herbivore b : herbivores) {

					/*
					 * Bounds bound = b.bug().localToScene(b.bug().getBoundsInLocal());
					 * 
					 * if (bound.getCenterX() < 0.0f || bound.getCenterX() > s.getWidth()) {
					 * b.setDx(-b.dx()); } if (bound.getCenterY() < 0.0f || bound.getCenterY() >
					 * s.getHeight()) { b.setDy(-b.dy()); }
					 * 
					 * b.setX(b.X() + b.dx()); b.setY(b.Y() + b.dy());
					 */
					b.move();
					b.graze();
					b.update();

				}

				int ran = (int)(Math.random() * 100);
				if (ran > 10 && ran < 12) {
					double bx = Math.random() * s.getWidth() + 1;
					double by = Math.random() * s.getHeight() + 1;
					int r = (int)(Math.random() * herbiFiles.length);
					Plant b = new Plant(bx, by);
					g.getChildren().add(b.plant());
					b.getScene(s);
					plants.add(b);
				}

			}

			private void removeDeadEntity() {
				ArrayList<Herbivore> toBeRemoved = new ArrayList<>();

				for (Herbivore b : herbivores) {
					if (!b.isAlive()) {
						toBeRemoved.add(b);
					}
				}
				lblPopulation.setText(String.valueOf(herbivores.size()));

				if (toBeRemoved.size() > 0) {
					for (Herbivore b : toBeRemoved) {
						herbivores.remove(b);
						if (b.bug().getParent() instanceof Group) {
							((Group) b.bug().getParent()).getChildren().remove(b.bug());
						}
					}
				}

				ArrayList<Plant> toBeRemoved1 = new ArrayList<>();

				for (Plant b : plants) {
					if (!b.isAlive()) {
						toBeRemoved1.add(b);
					}
				}

				if (toBeRemoved1.size() > 0) {
					for (Plant b : toBeRemoved1) {
						plants.remove(b);
						if (b.plant().getParent() instanceof Group) {
							((Group) b.plant().getParent()).getChildren().remove(b.plant());
						}
					}
				}
			}
		});

		addBug.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				double bx = Math.random() * s.getWidth() + 1;
				double by = Math.random() * s.getHeight() + 1;
				int r = (int)(Math.random() * herbiFiles.length);
				Herbivore b = new Herbivore(bx, by, Color.BLUE, herbiFiles[r], herbiSize[r], 1.5f);
				g.getChildren().add(b.bug());
				b.getScene(s);

				herbivores.add(b);
				lblPopulation.setText(String.valueOf(herbivores.size()));
			}

		});

		s.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				double mX = e.getSceneX();
				double mY = e.getSceneY();
				Entity toBeRemove = null;

				for (Herbivore b : herbivores) {

					Bounds pos = (b.bug().localToScene(
							b.bug().getBoundsInLocal() ));

					double aa = mX - pos.getCenterX();
					double bb = mY - pos.getCenterY();
					double cc = Math.sqrt(aa*aa + bb*bb);

					if (cc <= b.r() * 3) {

						if (b.bug().getParent() instanceof Group) {
							((Group) b.bug().getParent()).getChildren().remove(b.bug());
							toBeRemove = b;
						}
					}


					if (toBeRemove != null) {
						herbivores.remove(toBeRemove);
						lblPopulation.setText(String.valueOf(herbivores.size()));
					}
				}
			}

		});

		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.getKeyFrames().add(frame);
		timeline.play();

		primaryStage.setTitle("Bug World");
		primaryStage.setScene(s);
		primaryStage.show();
	}

	private void herbi(double x, double y, Color c, String f) {
		Herbivore b = new Herbivore(x, y, c, f, 30, 1.5f);
		g.getChildren().add(b.bug());
		b.getScene(s);
		entities.add(b);
	}

	public static ArrayList<Plant> getPlants() {
		return plants;
	}

	public static Scene getScene() {
		return s;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
