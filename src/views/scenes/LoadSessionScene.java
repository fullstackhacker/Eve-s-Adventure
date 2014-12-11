package views.scenes;

import java.io.File;

import controllers.ButtonHandlers;
import controllers.Save;
import views.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LoadSessionScene extends Scene {

	private static final class LoadSessionPane extends BorderPane{
		
		/**
		 * 
		 */
		private static LoadSessionPane instanceOfLoadSessionPane = null;
		
		private Button LOAD_SESSION_BUTTON, CANCEL_BUTTON;
		
		private ObservableList<String> sessions;
		private ListView<String> sessionsListView;
		
		private static final String DATADIR = "data" + File.separator; 
		private static final String WORLDDIR = LoadSessionPane.DATADIR + "worlds" + File.separator; 
		private static final String LEVELDIR = LoadSessionPane.DATADIR + "levels" + File.separator; 
		private static final String CAMPAIGNDIR = LoadSessionPane.DATADIR + "campaigns" + File.separator; 
		private static final String WORLDEXT = ".world"; 
		private static final String NAMEFILENAME = "name.dat"; 
		
		/**
		 * 
		 */
		private LoadSessionPane(){
			this.getStylesheets().add(LoadSessionScene.class.getResource("/loadsession_style.css").toExternalForm());
			setupObjects();
			this.setCenter(addVBox());
			
			LOAD_SESSION_BUTTON.setOnAction(ButtonHandlers::NEW_CAMPAIGN_ADVENTURE_BUTTON_HANDLER);
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
		
		private String getSelectedWorld(){
			return this.sessionsListView.getSelectionModel().getSelectedItem();
		}
		
		private void addSessions(){
			File levelDir = new File(LoadSessionPane.LEVELDIR); 
			if(!levelDir.exists()) return;
			
			File[] listOfFiles = levelDir.listFiles();
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
		
		
		
		private static LoadSessionPane getInstance(){
			return (instanceOfLoadSessionPane == null) ?instanceOfLoadSessionPane = new LoadSessionPane() : instanceOfLoadSessionPane;
		
		}
	}
	
	private static LoadSessionScene instanceOfLoadSessionScene = null;
	
	private LoadSessionScene(Parent arg0, double arg1, double arg2){
		super(arg0, arg1, arg2);
	}
	
	public static LoadSessionScene getInstance(){
		return (LoadSessionScene.instanceOfLoadSessionScene == null) ? instanceOfLoadSessionScene = 
			new LoadSessionScene(LoadSessionPane.getInstance(), MainApp.WINDOW_WIDTH, MainApp.WINDOW_HEIGHT) : 
			instanceOfLoadSessionScene;
	}
	
	public String getSelectedWorld(){
		return LoadSessionPane.getInstance().getSelectedWorld();
	}
	
}
