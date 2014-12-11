package views.scenes;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import views.MainApp;
import controllers.ButtonHandlers;

public class NewLevelScene extends Scene {

	private static final class NewLevelSessionPane extends BorderPane{
		
		/**
		 * The Instance of LoadSessionPane
		 */
		private static NewLevelSessionPane instanceOfNewLevelSessionPane = null;
		
		private Button LOAD_SESSION_BUTTON, CANCEL_BUTTON;
		
		private ObservableList<String> sessions;
		private ListView<String> sessionsListView;
		
		private static final String DATADIR = "data" + File.separator; 
		private static final String WORLDDIR = NewLevelSessionPane.DATADIR + "worlds" + File.separator; 
		private static final String LEVELDIR = NewLevelSessionPane.DATADIR + "levels" + File.separator; 
		private static final String CAMPAIGNDIR = NewLevelSessionPane.DATADIR + "campaigns" + File.separator; 
		private static final String WORLDEXT = ".world"; 
		private static final String NAMEFILENAME = "name.dat"; 
		
		/**
		 * LoadSessionPane's Constructor
		 * 
		 * This will generate all the buttons,
		 * textfields, and more. 
		 */
		private NewLevelSessionPane(){
			this.getStylesheets().add("./loadsession_style.css");
			setupObjects();
			this.setCenter(addVBox());
			
			LOAD_SESSION_BUTTON.setOnAction(ButtonHandlers::LOAD_SESSION_BUTTON_HANDLER);
			CANCEL_BUTTON.setOnAction(ButtonHandlers::CANCEL_BUTTON_HANDLER);
		}
		
		private void setupObjects(){
			LOAD_SESSION_BUTTON = new Button("LOAD SESSION");
			CANCEL_BUTTON = new Button("CANCEL");
			LOAD_SESSION_BUTTON.setPrefWidth(500);
			CANCEL_BUTTON.setMaxWidth(500);
			
			sessions = FXCollections.observableArrayList();
			addSessions();
			
			sessionsListView = new ListView<String>(sessions);
			sessionsListView.setMaxWidth(490);
			sessionsListView.setId("listView");
			
			LOAD_SESSION_BUTTON.setId("load-button");
			CANCEL_BUTTON.setId("cancel-button");
		}
		
		private void addSessions(){
			File worldsDir = new File(NewLevelSessionPane.WORLDDIR); 
			if(!worldsDir.exists()) return;
			
			File[] listOfFiles = worldsDir.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			        //System.out.println("File " + listOfFiles[i].getName());
			    	  sessions.add(listOfFiles[i].getName());
			      } else if (listOfFiles[i].isDirectory()) {
			        //Do nothing
			      }
			}
			return;
		}
		private VBox addVBox(){
			VBox vbox = new VBox();
			
			vbox.setPrefWidth(500);
			vbox.getChildren().add(sessionsListView);
		    vbox.getChildren().add(LOAD_SESSION_BUTTON);
		    vbox.getChildren().add(CANCEL_BUTTON);
			vbox.setAlignment(Pos.CENTER);
			
			return vbox;
		}
		
		
		
		private static NewLevelSessionPane getInstance(){
			return (instanceOfNewLevelSessionPane == null) ?instanceOfNewLevelSessionPane = new NewLevelSessionPane() : instanceOfNewLevelSessionPane;
		
		}
	}
	
	private static NewLevelScene instanceOfNewLevelSessionScene = null;
	
	private NewLevelScene(Parent arg0, double arg1, double arg2){
		super(arg0, arg1, arg2);
	}
	
	public static NewLevelScene getInstance(){
		return (NewLevelScene.instanceOfNewLevelSessionScene == null) ? instanceOfNewLevelSessionScene = 
			new NewLevelScene(NewLevelSessionPane.getInstance(), MainApp.WINDOW_WIDTH, MainApp.WINDOW_HEIGHT) : 
				instanceOfNewLevelSessionScene;
	}
	
}
