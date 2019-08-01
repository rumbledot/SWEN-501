package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Main extends Application {

	private Scene sc;
	private Pane playArea;
	private Pane background;
	private Pane starField;
	private Ball ball;
	private Bat bat;
	private Text txtScoreNum;
	private Text txtLifeNum;
	private Text txtPowerActive;

	private Boolean ballFall = true;
	private Boolean scoreBoost = false;
	private Boolean shooter = false;

	private double width = 320;
	private double height = 500;
	private int score = 0;
	private int life = 3;
	private int counter;
	private double ballDX = 0.0;
	private double ballDY = 6.0;
	private double speed = 6.0;
	private double batX, oldBatX;
	private double batSpeed = 0;

	public ArrayList<Brick> bricks = new ArrayList<>();
	private ArrayList<PowerUp> powerups = new ArrayList<>();
	
	private ArrayList<Rectangle> starNear = new ArrayList<>();
	private ArrayList<Rectangle> starFar = new ArrayList<>();
	
	public Color[] brickColors = new Color[] {
			Color.DARKSLATEBLUE, Color.DARKOLIVEGREEN, Color.DARKRED, Color.DARKKHAKI, Color.YELLOW };


	@Override
	public void start(Stage primaryStage) {

		setStage(primaryStage);

		gameObjects();
		
		stars();

		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				moveStars();
				
				if (counter > 0) {
					counter--;
					System.out.println(counter);
					bat.setFill(brickColors[counter % 3]);
					if (counter <= 0) {
						scoreBoost = false;
						txtPowerActive.setText("");
						counter = 0;
						bat.setFill(Color.BLUE);
					}
				}

				if (ball.getCenterY() + ball.getRadius() >= bat.getY()) {
					ballFall = !ballFall;

					if (ball.getCenterX() - bat.getX() < 25) {
						ballDX = -((25 - (ball.getCenterX() - bat.getX())) / 25) * speed;
					}
					if (ball.getCenterX() - bat.getX() > 25) {
						ballDX = (((ball.getCenterX() - bat.getX()) - 25) / 25) * speed;
					}

				}

				if (ball.getCenterY() - ball.getRadius() <= 0) {

					ballFall = !ballFall;
				}

				if (ball.getCenterX() - ball.getRadius() <= 0) ballDX = -ballDX;
				if (ball.getCenterX() + ball.getRadius() >= playArea.getWidth()) ballDX = -ballDX;

				ArrayList<Brick> tobeRemoved = new ArrayList<>();
				for (Brick b : bricks) { 
					if (ball.getCenterY() - ball.getRadius() <= b.getY() + 15 &&
							ball.getCenterX() + ball.getRadius() > b.getX() && ball.getCenterX() - ball.getRadius() < b.getX() + 30) { 
						ballFall = true;
						ballDX = -ballDX;
						playArea.getChildren().remove(b);
						tobeRemoved.add(b);
						score++;
						if (scoreBoost) score++;
						txtScoreNum.setText(String.valueOf(score));
						int r = (int)(Math.random() * 100 + 1);
						if (r > 10 && r < 15) {
							LifeUp pwr = new LifeUp(b.getX() + 5, b.getY());
							powerups.add(pwr);
							playArea.getChildren().add(pwr);
						}
						if (r > 15 && r < 20) {
							ScoreBoost pwr = new ScoreBoost(b.getX() + 5, b.getY());
							powerups.add(pwr);
							playArea.getChildren().add(pwr);
						}
					}
				}

				if (tobeRemoved.size() > 0) {
					for (Brick b : tobeRemoved) {
						bricks.remove(b);
						playArea.getChildren().remove(b);
					}
				}

				ArrayList<PowerUp> tobeRemoved1 = new ArrayList<>();
				for (PowerUp p : powerups) {
					p.move();
					if (p.getX() >= bat.getX() && p.getX() + 20 <= bat.getX() + 50 && 
							p.getY() >= bat.getY()) {
						tobeRemoved1.add(p);
						if (p instanceof LifeUp) {
							life++;
							txtLifeNum.setText(String.valueOf(life));
							counter = 10;
							txtPowerActive.setText("LIFE UP!!");
						}
						if (p instanceof ScoreBoost) {
							scoreBoost = true;
							counter = 100;
							txtPowerActive.setText("SCORE BOOST!!");
						}
					}
					if (p.getY() >= playArea.getHeight()) {
						tobeRemoved1.add(p);
					}
				}

				if (tobeRemoved1.size() > 0) {
					for (PowerUp b : tobeRemoved1) {
						powerups.remove(b);
						playArea.getChildren().remove(b);
					}
				}

				if (ballFall) {
					ballDY = +speed;
				} else {
					ballDY = -speed;
				}

				ball.setCenterX(ball.getCenterX() + ballDX);
				ball.setCenterY(ball.getCenterY() + ballDY);
			}

			private void moveStars() {
				for (Rectangle r : starFar) {
					r.setY(r.getY() + 0.5);
					if (r.getY() > background.getHeight()) r.setY(0);
				}
				
				for (Rectangle r : starNear) {
					r.setY(r.getY() + 1);
					if (r.getY() > starField.getHeight()) r.setY(0);
				}
				
			}

		});

		sc.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode()==KeyCode.A) {
				//playArea.getChildren().remove(bricks.get(4));
			}
			if(key.getCode()==KeyCode.D) {
				System.out.println("You pressed D");
			}
			if(key.getCode()==KeyCode.SPACE) {
				System.out.println("You pressed SPACE");
			}
		});

		sc.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				batX = e.getX() - 25;
				batSpeed = Math.abs(oldBatX - batX);
				if (batX <= 0) batX = 0;
				if (batX >= playArea.getWidth() - 50) batX = playArea.getWidth() - 50; 
				bat.setX(batX);
				oldBatX = batX;
			}

		});

		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.getKeyFrames().add(frame);
		timeline.play();

	}

	private void setStage(Stage primaryStage) {
		playArea = new Pane();
		background = new Pane();
		starField = new Pane();
		HBox infoArea = new HBox();
		VBox leftBorder = new VBox();
		VBox rightBorder = new VBox();
		HBox bottomBorder = new HBox();

		/*
		 * File f = new File("C:/Users/Abram/Documents/MSwDev 2019/Field.png"); Image
		 * img = new Image(f.toURI().toString());
		 * playArea.setStyle("-fx-background-image: url('" + img + "');" +
		 * "    -fx-background-repeat: stretch;" +
		 * "    -fx-background-position: center center;");
		 */

		File f1 = new File("src/application/graphics/starfield.jpg");
		playArea.setBackground(new Background(
				Collections.singletonList(new BackgroundFill(
						Color.WHITE, 
						new CornerRadii(500), 
						new Insets(0))),
				Collections.singletonList(new BackgroundImage(
						new Image(f1.toURI().toString()),
						BackgroundRepeat.REPEAT,
						BackgroundRepeat.REPEAT,
						BackgroundPosition.CENTER,
						BackgroundSize.DEFAULT))));

		background.setPrefSize(width, height);
		starField.setPrefSize(width, height);
		
		playArea.getChildren().add(background);
		playArea.getChildren().add(starField);

		Label txtLive = new Label("Lives");
		txtLifeNum = new Text("0");
		Text txtScore = new Text("Score : ");
		txtScoreNum = new Text("0");
		txtPowerActive = new Text();
		infoArea.setPadding(new Insets(4, 4, 4, 4));
		infoArea.setSpacing(4);
		infoArea.getChildren().addAll(txtLive, txtLifeNum, txtScore, txtScoreNum, txtPowerActive);

		leftBorder.setPrefSize(10, height);

		rightBorder.setPrefSize(10, height);

		bottomBorder.setPrefSize(width, 10);

		BorderPane bp = new BorderPane();
		bp.setBackground(null);
		bp.setTop(infoArea);
		bp.setCenter(playArea);
		bp.setBottom(bottomBorder);
		bp.setLeft(leftBorder);
		bp.setRight(rightBorder);

		sc = new Scene(bp, width, height);
		primaryStage.setScene(sc);
		primaryStage.setTitle("Brick Breaker");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		background.setPrefSize(playArea.getWidth(), playArea.getHeight());
		starField.setPrefSize(playArea.getWidth(), playArea.getHeight());
	}

	private void stars() {
		for (int i =0; i < 20; i++) {
			double bx = Math.random() * starField.getWidth();
			double by = Math.random() * starField.getHeight();
			Rectangle r = new Rectangle(bx, by, 3, 3);
			r.setFill(Color.GREY);
			starField.getChildren().add(r);
			starNear.add(r);
		}
		
		for (int i =0; i < 20; i++) {
			double bx = Math.random() * background.getWidth();
			double by = Math.random() * background.getHeight();
			Rectangle r = new Rectangle(bx, by, 2, 2);
			r.setFill(Color.DARKGREY);
			background.getChildren().add(r);
			starFar.add(r);
		}
	}

	private void gameObjects() {
		ball = new Ball(playArea.getWidth() / 2 - 8, playArea.getHeight() / 2 - 8);
		bat = new Bat(playArea.getWidth() / 2 - 30, playArea.getHeight() - 30);
		playArea.getChildren().addAll(ball, bat);

		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 10; col++) {
				int i = (int)(Math.random() * brickColors.length);
				Brick brick = new Brick(col * 30, 40 + row * 15, brickColors[i]);
				bricks.add(brick);
				playArea.getChildren().add(brick);
			}
		}

	}

	public Scene getScene() {
		return sc;
	}

	public Pane playArea() {
		return playArea;
	}

	public static void main(String[] args) {
		launch(args);
	}
}