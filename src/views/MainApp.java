package views;

import views.scenes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static Stage stage = null;

	public static final float WINDOW_HEIGHT = 500;

	public static final float WINDOW_WIDTH = 500;

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainApp.stage = primaryStage;
		stage.setTitle("Eve's Adventure");
		stage.setScene(MainMenuScene.getInstance());
		stage.setResizable(false);
		stage.setWidth(1024);
		stage.setHeight(768);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void changeScenes(Scene scene) {
		stage.setScene(scene);
	}
}
