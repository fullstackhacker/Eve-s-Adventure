package views;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Anthony Wong
 * 
 * The MainMenuScene will be use for
 * the MainApp.java. This class extends
 * Scene. It is using design pattern Singleton.

 * TODO Beautify this Code.  
 */
public final class MainMenuScene extends Scene {
	
	/**
	 * @author Anthony Wong
	 * 
	 * The MainMenuPane will be use for the MainMenuScene.java.
	 * This class extends Pane and it is the design patter singleton. 
	 */
	private static final class MainMenuPane extends Pane{
	
		/**
		 * The Instance of MainMenuPane
		 */
		private static MainMenuPane instanceOfMainMenuPane = null;
		
		/**
		 * MainMenuPane's Constructor
		 * 
		 * This will generate all the buttons,
		 * textfields, and more. 
		 */
		private MainMenuPane(){
			Button btn = new Button("Hello World");
			this.getChildren().addAll(btn);
		}
		
		/**
		 * @return MainMenuPane's Object
		 */
		private static MainMenuPane getInstance(){
			return (instanceOfMainMenuPane ==  null) ? instanceOfMainMenuPane = new MainMenuPane() : instanceOfMainMenuPane;
		}
	}
	
	/**
	 * The Height of the MainMenuScene.
	 */
	public static float HEIGHT = 70;
	
	/**
	 * The Width of the MainMenuScene.
	 */
	public static float WIDTH = 70;
	
	/**
	 * The Instance of MainMenuScene
	 */
	private static MainMenuScene instanceOfMainMenuScene = null;
	
	/**
	 * MainMenuScene' Constructor
	 * @param arg0 MainMenuPane
	 * @param arg1 width
	 * @param arg2 height
	 * 
	 * This will generate the Scene and
	 * will call MainMenuPane Object
	 */
	private MainMenuScene(Parent arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}

	/**
	 * @return MainMenuScene's Object.
	 */
	public static MainMenuScene getInstance(){
		return (MainMenuScene.instanceOfMainMenuScene == null) ? instanceOfMainMenuScene = new MainMenuScene(MainMenuPane.getInstance(), WIDTH, HEIGHT) : instanceOfMainMenuScene;
	}
}
