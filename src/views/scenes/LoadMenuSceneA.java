package views.scenes;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views.MainApp;
import controllers.ButtonHandlers;

/**
 * 
 */
public final class LoadMenuSceneA extends Scene {

	/**
	 * 
	 */
	private static final class LoadMenuPaneA extends BorderPane {

		/**
		 * 
		 */
		private static LoadMenuPaneA instanceOfLoadMenuPaneA = null;

		private Button LOAD_CAMPAIGN_BUTTON, NEW_CAMPAIGN_BUTTON, BACK_BUTTON;

		/**
		 * 
		 */
		private LoadMenuPaneA() {
			this.getStylesheets().add("./loadmenu_style.css");
			setupObjects();
			this.setCenter(addVBox());
			this.setBottom(addHBox());

			LOAD_CAMPAIGN_BUTTON
					.setOnAction(ButtonHandlers::LOAD_CAMPAIGN_BUTTON_HANDLER);
			NEW_CAMPAIGN_BUTTON
					.setOnAction(ButtonHandlers::NEW_CAMPAIGN_BUTTON_HANDLER);
			BACK_BUTTON
					.setOnAction(ButtonHandlers::BACK_HOMESCREEN_BUTTON_HANDLER);
		}

		private void setupObjects() {
			LOAD_CAMPAIGN_BUTTON = new Button("LOAD CAMPAIGN");
			NEW_CAMPAIGN_BUTTON = new Button("NEW CAMPAIGN");
			BACK_BUTTON = new Button("BACK TO HOME SCREEN");
			LOAD_CAMPAIGN_BUTTON.setPrefWidth(500);
			NEW_CAMPAIGN_BUTTON.setPrefWidth(500);
			BACK_BUTTON.setPrefWidth(300);

			LOAD_CAMPAIGN_BUTTON.setId("load-button");
			NEW_CAMPAIGN_BUTTON.setId("new-button");
			BACK_BUTTON.setId("back-button");
		}

		private VBox addVBox() {
			VBox vbox = new VBox();

			vbox.setPrefWidth(500);
			vbox.getChildren().add(LOAD_CAMPAIGN_BUTTON);
			vbox.getChildren().add(NEW_CAMPAIGN_BUTTON);
			vbox.setAlignment(Pos.CENTER);

			return vbox;
		}

		private HBox addHBox() {
			HBox hbox = new HBox();

			hbox.setPrefWidth(300);
			hbox.getChildren().add(BACK_BUTTON);
			hbox.setAlignment(Pos.CENTER);

			return hbox;
		}

		private static LoadMenuPaneA getInstance() {
			return (instanceOfLoadMenuPaneA == null) ? instanceOfLoadMenuPaneA = new LoadMenuPaneA()
					: instanceOfLoadMenuPaneA;
		}
	}

	/**
	 * 
	 */
	private static LoadMenuSceneA instanceOfLoadMenuAScene = null;

	/**
	 */
	private LoadMenuSceneA(Parent arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}

	public static LoadMenuSceneA getInstance() {
		return (LoadMenuSceneA.instanceOfLoadMenuAScene == null) ? instanceOfLoadMenuAScene = new LoadMenuSceneA(
				LoadMenuPaneA.getInstance(), MainApp.WINDOW_WIDTH,
				MainApp.WINDOW_HEIGHT) : instanceOfLoadMenuAScene;
	}
}
