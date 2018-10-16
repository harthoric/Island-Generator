package com.harthoric.island;

import com.harthoric.island.noise.draw.Draw;
import com.harthoric.island.noise.properties.Properties;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
 * <h1> Istoria Isola </h1>
 * 
 * Procedurally generated island using simplex noise
 * @see <a href="https://www.csee.umbc.edu/~olano/s2002c36/ch02.pdf">Noise Hardware</a>
 * @see <a href="http://staffwww.itn.liu.se/~stegu/simplexnoise/simplexnoise.pdf">Simplex noise demystified</a>
 * @see <a href="https://mrl.nyu.edu/~perlin/paper445.pdf">Improving noise</a>
 * 
 * @author  Harthoric
 * @version 1.0
 * @since   2018-09-03
 * 
 */

public class Core extends Application {

	final Group lines = new Group(), curves = new Group(), scrollBars = new Group();

	final StackPane root = new StackPane();

	int octaves = 7;
	float roughness = 0.6f, scale = 0.004f;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Generator");

		Rectangle rect = new Rectangle(0, 0, 400, 400);
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.BLACK);

		// y = -x
		Line line1 = new Line(0, 0, 400, 400);
		// y = x
		Line line2 = new Line(400, 0, 0, 400);
		// y = 0
		Line line3 = new Line(0, 200, 400, 200);
		// x = 0
		Line line4 = new Line(200, 0, 200, 400);
		lines.getChildren().addAll(line1, line2, line3, line4);

		for (int i = 0; i <= 400; i++) {
			System.out.printf("x: %s, y: %s \n", i, ((400 + Math.sqrt(160000 - 4 * 40000 + i ^ 2 + 400 * i)) / 2));
			curves.getChildren().add(new Circle(i, ((400 + Math.sqrt(160000 - 4 * 40000 + i ^ 2 + 400 * i)) / 2), 1));
			curves.getChildren().add(new Circle(i, ((400 - Math.sqrt(160000 - 4 * 40000 + i ^ 2 + 400 * i)) / 2), 1));
		}

		// Set properties
		Properties property = new Properties();
		property.setProperties(scrollBars);

		// Set handlers
		Draw draw = new Draw();
		property.setHandlers(draw.getImage(), draw.getNormalImage());

		root.getChildren().addAll(scrollBars, property.getCircleMain(), rect, lines, curves);
		primaryStage.setScene(new Scene(root, 800, 500));
		primaryStage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}

	public int getOctaves() {
		return octaves;
	}

	public float getRoughness() {
		return roughness;
	}

	public float getScale() {
		return scale;
	}

	public void setOctaves(int octaves) {
		this.octaves = octaves;
	}

	public void setRoughness(float roughness) {
		this.roughness = roughness;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

}
