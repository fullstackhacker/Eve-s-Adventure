package views.scenes;

import views.MainApp;
import controllers.ButtonHandlers;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Anthony Wong
 * 
 * The MainMenuScene will be use for
 * the MainApp.java. This class extends
 * Scene. It is using design pattern Singleton.
 */
public final class LoadMenuScene extends Scene {
	
	/**
	 * @author Anthony Wong
	 * 
	 * The MainMenuPane will be use for the MainMenuScene.java.
	 * This class extends Pane and it is the design patter singleton. 
	 */
	private static final class LoadMenuPane extends BorderPane{
	
		/**
		 * The Instance of MainMenuPane
		 */
		private static LoadMenuPane instanceOfMainMenuPane = null;
		
		private Button LOAD_SESSION_BUTTON, NEW_SESSION_BUTTON;
		
		/**
		 * MainMenuPane's Constructor
		 * 
		 * This will generate all the buttons,
		 * textfields, and more. 
		 */
		private LoadMenuPane(){
			this.getStylesheets().add("./loadmenu_style.css");
			setupObjects();
			this.setCenter(addVBox());
			
			LOAD_SESSION_BUTTON.setOnAction(ButtonHandlers::LOAD_SESSION_BUTTON_HANDLER);
			NEW_SESSION_BUTTON.setOnAction(ButtonHandlers::NEW_SESSION_BUTTON_HANDLER);
		}
		
		private void setupObjects(){
			LOAD_SESSION_BUTTON = new Button("LOAD SESSION");
			NEW_SESSION_BUTTON = new Button("NEW SESSION");
			LOAD_SESSION_BUTTON.setPrefWidth(500);
			NEW_SESSION_BUTTON.setPrefWidth(500);
			
			LOAD_SESSION_BUTTON.setId("load-button");
			NEW_SESSION_BUTTON.setId("new-button");
		}
		
		private VBox addVBox(){
			VBox vbox = new VBox();
			
			vbox.setPrefWidth(500);
		    vbox.getChildren().add(LOAD_SESSION_BUTTON);
		    vbox.getChildren().add(NEW_SESSION_BUTTON);
			vbox.setAlignment(Pos.CENTER);
			
			return vbox;
		}
		
		private static LoadMenuPane getInstance(){
			return (instanceOfMainMenuPane ==  null) ? instanceOfMainMenuPane = new LoadMenuPane() : instanceOfMainMenuPane;
		}
	}
	
	/**
	 * The Instance of MainMenuScene
	 */
	private static LoadMenuScene instanceOfMainMenuScene = null;
	
	/**
	 * MainMenuScene' Constructor
	 * @param arg0 MainMenuPane
	 * @param arg1 width
	 * @param arg2 height
	 * 
	 * This will generate the Scene and
	 * will call MainMenuPane Object
	 */
	private LoadMenuScene(Parent arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}

	public static LoadMenuScene getInstance(){
		return (LoadMenuScene.instanceOfMainMenuScene == null) ? instanceOfMainMenuScene = 
			new LoadMenuScene(LoadMenuPane.getInstance(), MainApp.WINDOW_WIDTH, MainApp.WINDOW_HEIGHT) : 
			instanceOfMainMenuScene;
	}
}
