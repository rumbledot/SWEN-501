package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {

	private int activeTool = 1;

	private Rectangle[][] rectangles = new Rectangle[20][20];
	private Color c = Color.BLACK;
	private Color[] palette = new Color[] 
			{ Color.BLACK, Color.BLUE, Color.GREEN, Color.CYAN, Color.DARKRED, Color.DARKMAGENTA, Color.BROWN, Color.LIGHTGREY,
					Color.DARKGREY, Color.LIGHTBLUE, Color.LIGHTGREEN, Color.LIGHTCYAN, Color.RED, Color.MAGENTA, Color.LIGHTYELLOW, Color.WHITE };
	private String[] hexpalette = new String[]
			{ "#000000", "#0000AA", "#00AA00", "#00AAAA", "#AA0000", "#AA00AA", "#AA5500", "#AAAAAA", "#555555",
					"#5555FF", "#55FF55" , "#55FFFF", "#FF5555", "#FF55FF", "#FFFF55", "#FFFFFF"	};

	@Override
	public void start(Stage primaryStage) {

		HBox toolBox = new HBox();
		Button btnPencil = new Button("Pencil");
		Button btnErase = new Button("Erase");
		toolBox.getChildren().addAll(btnPencil, btnErase);

		TilePane colorPallete = new TilePane();
		colorPallete.setPrefSize(50, 700);
		colorPallete.setPadding(new Insets(5, 0, 5, 0));
		colorPallete.setVgap(4);
		colorPallete.setHgap(4);
		colorPallete.setPrefColumns(2);
		Button colors[] = new Button[16];
		for (int i = 0; i < 16; i++) {
			String background = "-fx-background-color : " + hexpalette[i];
			colors[i] = new Button();
			colors[i].setStyle(background);
			colorPallete.getChildren().add(colors[i]);
		}
		System.out.println(palette.length);

		VBox vbox = new VBox();
		vbox.setPrefSize(50, 700);
		
		Pane canvas = new Pane();
		canvas.setPrefSize(600, 600); canvas.minWidth(600); canvas.minHeight(600);
		canvas.maxWidth(600); canvas.maxHeight(600);
		canvas.setStyle("-fx-background-color : #FAF7EF; height:600; width:600");
		canvas.setStyle("-fx-border-width: 5px; -fx-border-color: #000000");

		BorderPane g = new BorderPane();
		g.setTop(toolBox);
		g.setCenter(canvas);
		g.setRight(colorPallete);
		g.setLeft(vbox);

		Scene s = new Scene(g, 700, 700);
		s.setFill(Color.DARKGREY);

		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

			}

		});

		btnPencil.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				activeTool = 1;
			}
		});
		btnErase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				activeTool = 2;
			}
		});

		colors[0].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[0];
			}
		});
		colors[1].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[1];
			}
		});
		colors[2].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[2];
			}
		});
		colors[3].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[3];
			}
		});
		colors[4].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[4];
			}
		});
		colors[5].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[5];
			}
		});
		colors[6].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[6];
			}
		});
		colors[7].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[7];
			}
		});
		colors[8].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[8];
			}
		});
		colors[9].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[9];
			}
		});
		colors[10].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[10];
			}
		});
		colors[11].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[11];
			}
		});
		colors[12].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[12];
			}
		});colors[13].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[13];
			}
		});colors[14].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[14];
			}
		});colors[15].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				c = palette[15];
			}
		});

		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {

				double gridX = Math.floor(e.getX() / 30);
				double mX = gridX * 30;
				double gridY = Math.floor(e.getY() / 30);
				double mY = gridY * 30;
				
				switch (activeTool) {
				case 1:
					if (gridX >= 0 && gridX < 20 && gridY >= 0 && gridY < 20) {
						if (rectangles[(int) gridX][(int) gridY] == null) {
							Rectangle dot = new Rectangle(mX + 2, mY + 2, 28, 28);
							dot.setFill(c);
							canvas.getChildren().add(dot);
							rectangles[(int) gridX][(int) gridY] = dot;
						}
					
					}
					break;
				case 2:
					if (gridX >= 0 && gridX < 20 && gridY >= 0 && gridY < 20) {
						if (rectangles[(int) gridX][(int) gridY] != null) {
							Rectangle dot = rectangles[(int) gridX][(int) gridY];
							if (dot.getParent() instanceof Pane) {
								((Pane) dot.getParent()).getChildren().remove(dot);
								rectangles[(int) gridX][(int) gridY] = null;
							}
						}
					
					}
					break;
				}
			}
		});

		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.getKeyFrames().add(frame);
		timeline.play();

		primaryStage.setTitle("Drawing");
		primaryStage.setResizable(false);
		primaryStage.setScene(s);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
