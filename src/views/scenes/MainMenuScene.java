package views.scenes;

import views.MainApp;
import controllers.ButtonHandlers;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
	private static final class MainMenuPane extends BorderPane{
	
		/**
		 * The Instance of MainMenuPane
		 */
		private static MainMenuPane instanceOfMainMenuPane = null;
		
		private Button SANDBOX_MODE_BUTTON, ADVENTURE_MODE_BUTTON;
		
		private ImageView LOGO = new ImageView(new Image("./Images/Eve.png"));
		//private ImageView LOGO;
		private Label TITLE_LABEL;
		
		/**
		 * MainMenuPane's Constructor
		 * 
		 * This will generate all the buttons,
		 * textfields, and more. 
		 */
		private MainMenuPane(){
			this.getStylesheets().add("./homescreen_style.css");
			
			setupObjects();
			this.setCenter(addVBox());
			
			SANDBOX_MODE_BUTTON.setOnAction(ButtonHandlers::SANDBOX_MODE_BUTTON_HANDLER);
			ADVENTURE_MODE_BUTTON.setOnAction(ButtonHandlers::ADVENTURE_MODE_BUTTON_HANDLER);
			
		}
		
		private void setupObjects(){
//			ClassLoader cldr = this.getClass().getClassLoader();
//			java.net.URL imageURL   = cldr.getResource("./Images/Eve.png");
//			Image logo = new Image(getClass().getResourceAsStream("./Images/Eve.png"));
//			LOGO = new ImageView();
//			LOGO.setImage(logo);
			TITLE_LABEL = new Label("EVE'S ADVENTURE");
			SANDBOX_MODE_BUTTON = new Button("SANDBOX MODE");
			ADVENTURE_MODE_BUTTON = new Button("ADVENTURE MODE");
			SANDBOX_MODE_BUTTON.setPrefWidth(300);
			ADVENTURE_MODE_BUTTON.setPrefWidth(300);
			
			TITLE_LABEL.setId("title");
			SANDBOX_MODE_BUTTON.setId("sandbox-button");
			ADVENTURE_MODE_BUTTON.setId("adventure-button");
		}
		
		private VBox addVBox(){
			VBox vbox = new VBox();
			
			vbox.setPrefWidth(200);
			vbox.getChildren().add(LOGO);
			vbox.getChildren().add(TITLE_LABEL);
		    vbox.getChildren().add(SANDBOX_MODE_BUTTON);
		    vbox.getChildren().add(ADVENTURE_MODE_BUTTON);
			vbox.setAlignment(Pos.CENTER);
			
			return vbox;
		}
		
		/**
		 * @return MainMenuPane's Object
		 */
		private static MainMenuPane getInstance(){
			return (instanceOfMainMenuPane ==  null) ? instanceOfMainMenuPane = new MainMenuPane() : instanceOfMainMenuPane;
		}
	}
	
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
		return (MainMenuScene.instanceOfMainMenuScene == null) ? instanceOfMainMenuScene = 
				new MainMenuScene(MainMenuPane.getInstance(), MainApp.WINDOW_WIDTH, MainApp.WINDOW_HEIGHT) : 
				instanceOfMainMenuScene;
	}
}
