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

public class NewCampaignScene extends Scene {

	private static final class NewCampaignPane extends BorderPane{
		
		/**
		 * 
		 */
		private static NewCampaignPane instanceOfNewCampaignPane = null;
		
		private Button LOAD_SESSION_BUTTON, CANCEL_BUTTON;
		
		private ObservableList<String> sessions;
		private ListView<String> sessionsListView;
		
		private static final String DATADIR = "data" + File.separator; 
		private static final String WORLDDIR = NewCampaignPane.DATADIR + "worlds" + File.separator; 
		private static final String LEVELDIR = NewCampaignPane.DATADIR + "levels" + File.separator; 
		private static final String CAMPAIGNDIR = NewCampaignPane.DATADIR + "campaigns" + File.separator; 
		private static final String WORLDEXT = ".world"; 
		private static final String NAMEFILENAME = "name.dat"; 
		
		/**
		 * 
		 */
		private NewCampaignPane(){
			this.getStylesheets().add("/loadsession_style.css");
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
		
		private void addSessions(){
			File campaignDir = new File(NewCampaignPane.CAMPAIGNDIR); 
			if(!campaignDir.exists()) return;
			
			File[] listOfFiles = campaignDir.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isDirectory()) {
			        //System.out.println("File " + listOfFiles[i].getName());
			    	  sessions.add(listOfFiles[i].getName());
			      } else if (listOfFiles[i].isFile()) {
			        //Do nothing
			      }
			}
			return;
		}
		
		private String getSelectedCampaign(){
			return sessionsListView.getSelectionModel().getSelectedItem(); 
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
		
		
		
		private static NewCampaignPane getInstance(){
			return (instanceOfNewCampaignPane == null) ?instanceOfNewCampaignPane = new NewCampaignPane() : instanceOfNewCampaignPane;
		
		}
	}
	
	private static NewCampaignScene instanceOfNewCampaignScene = null;
	
	private NewCampaignScene(Parent arg0, double arg1, double arg2){
		super(arg0, arg1, arg2);
	}
	
	public String getSelectedCampaign(){
		return instanceOfNewCampaignScene.getSelectedCampaign();
	}
	
	public static NewCampaignScene getInstance(){
		return (NewCampaignScene.instanceOfNewCampaignScene == null) ? instanceOfNewCampaignScene = 
			new NewCampaignScene(NewCampaignPane.getInstance(), MainApp.WINDOW_WIDTH, MainApp.WINDOW_HEIGHT) : 
				instanceOfNewCampaignScene;
	}
	
}
