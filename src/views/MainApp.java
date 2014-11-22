package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private static Stage stage = null; 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainApp.stage = primaryStage; 
	}
	
	public static void main(String[] args){
		launch(args); 
	}
	
	public static Stage getStage(){
		if(MainApp.stage == null) launch(); 
		 return MainApp.stage; 
	}

	public static void changeScenes(Stage stage, Scene scene){
		stage.setScene(scene);
	}
}
