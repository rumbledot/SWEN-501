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
	private Scene s;
	private BorderPane r;
	private Group g;

	private String[] herbiFiles = new String[] 
			{ "C:/Users/Abram/Documents/MSwDev 2019/cow.png",
			"C:/Users/Abram/Documents/MSwDev 2019/Sheep.png" };

	private ArrayList<Entity> entities = new ArrayList<>();

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

		Text lblBug = new Text();
		lblBug.setText("Population : ");
		v.getChildren().add(lblBug);

		Text lblBugPopulation = new Text();
		lblBugPopulation.setText("0");
		v.getChildren().add(lblBugPopulation);

		g.getChildren().add(v);

		/*		r.setTop(v);
		r.setCenter(g);*/

		//this.bug(100, 100, Color.RED, "C:/Users/Abram/Documents/MSwDev 2019/Charmander.png");
		//this.bug(150, 150, Color.GREEN, "C:/Users/Abram/Documents/MSwDev 2019/Squirtle.png");
		//this.bug(200, 200, Color.BLUE, "C:/Users/Abram/Documents/MSwDev 2019/Bulbasaur.png");

		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (Entity b : entities) {
					if (b instanceof Herbivore)	((Herbivore) b).move();
					if (b instanceof Plant) ((Plant) b).grow();
				}

				int ran = (int)(Math.random() * 100);
				if (ran > 10 && ran < 12) {
					double bx = Math.random() * s.getWidth() + 1;
					double by = Math.random() * s.getHeight() + 1;
					int r = (int)(Math.random() * herbiFiles.length);
					Plant b = new Plant(bx, by);
					g.getChildren().add(b.plant());
					b.getScene(s);
				}

			}
		});

		addBug.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				double bx = Math.random() * s.getWidth() + 1;
				double by = Math.random() * s.getHeight() + 1;
				int r = (int)(Math.random() * herbiFiles.length);
				Herbivore b = new Herbivore(bx, by, Color.BLUE, herbiFiles[r], 30, 1.5f);
				g.getChildren().add(b.bug());
				b.getScene(s);
				entities.add(b);
				lblBugPopulation.setText(String.valueOf(entities.size()));
			}

		});

		s.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				double mX = e.getSceneX();
				double mY = e.getSceneY();
				Entity toBeRemove = null;

				for (Entity b : entities) {

					if (b instanceof Herbivore) {
						Bounds pos = ((Herbivore) b).bug().localToScene(
								((Herbivore) b).bug().getBoundsInLocal() );

						double aa = mX - pos.getCenterX();
						double bb = mY - pos.getCenterY();
						double cc = Math.sqrt(aa*aa + bb*bb);

						if (cc <= ((Herbivore) b).r() * 3) {

							if (((Herbivore) b).bug().getParent() instanceof Group) {
								((Group) ((Herbivore) b).bug().getParent()).getChildren().remove(((Herbivore) b).bug());
								toBeRemove = b;
							}
						}
					}

					if (toBeRemove != null) {
						entities.remove(toBeRemove);
						lblBugPopulation.setText(String.valueOf(entities.size()));
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

	public static void main(String[] args) {
		launch(args);
	}
}
