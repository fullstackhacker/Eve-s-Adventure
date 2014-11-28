package views.scenes;

import controllers.ButtonHandlers;
import views.MainApp;
import views.TopMenu;
import views.grid.GridWorld;
import views.karel.KarelTable;
import views.tabs.GameTabs;
import views.tips.ProTips;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public final class SandboxScene extends Scene {
	
	private static final class SandboxPane extends GridPane {

		private static SandboxPane instanceOfMainMenuPane = null;
		
		private ImageView imageBack = new ImageView(new Image("./Images/ArrowLeft.png"));
		private ImageView imageRight = new ImageView(new Image("./Images/ArrowRight.png"));
		private ImageView imagePlay = new ImageView(new Image("./Images/PlayButton.png"));
		private ImageView imageReset = new ImageView(new Image("./Images/ResetButton.png"));
		
		private Button BACK, FORWARD, PLAY, RESET;

		private SandboxPane() {
			this.getStylesheets().add("./sandbox_style.css");
			
			buttonSetup();

			GridPane.setFillWidth(BACK, true);
			GridPane.setHalignment(BACK, HPos.CENTER);
			GridPane.setFillWidth(FORWARD, true);
			GridPane.setHalignment(FORWARD, HPos.CENTER);
			GridPane.setFillWidth(PLAY, true);
			GridPane.setHalignment(PLAY, HPos.CENTER);
			GridPane.setFillWidth(RESET, true);
			GridPane.setHalignment(RESET, HPos.CENTER);

			TopMenu topMenu = TopMenu.getInstance();
			GameTabs gametabs = GameTabs.getInstance();
			gametabs.setId("gametabs");
			ProTips protips = ProTips.getInstance();
			KarelTable karelTable = KarelTable.getInstance();
			protips.setId("protips");
			GridWorld gridWorld = GridWorld.getInstance();

			this.add(gametabs, 0, 1, 1, 5);
			this.add(topMenu, 0, 0, 6, 1);
			this.add(gridWorld, 2, 2, 4, 5);
			this.add(karelTable, 1, 1, 1, 5);
			this.add(protips, 0, 6, 2, 1);
			this.add(BACK, 2, 1);
			this.add(FORWARD, 3, 1);
			this.add(PLAY, 4, 1);
			this.add(RESET, 5, 1);
			

			ColumnConstraints column1 = new ColumnConstraints();
			column1.setPercentWidth(32);
			ColumnConstraints column2 = new ColumnConstraints();
			column2.setPercentWidth(36);
			ColumnConstraints column3 = new ColumnConstraints();
			column3.setPercentWidth(8);
			ColumnConstraints column4 = new ColumnConstraints();
			column4.setPercentWidth(8);
			ColumnConstraints column5 = new ColumnConstraints();
			column5.setPercentWidth(8);
			ColumnConstraints column6 = new ColumnConstraints();
			column6.setPercentWidth(8);
			this.getColumnConstraints().addAll(column1, column2, column3,
					column4, column5, column6);

			RowConstraints row1 = new RowConstraints();
			row1.setPercentHeight(3);
			RowConstraints row2 = new RowConstraints();
			row2.setPercentHeight(5);
			RowConstraints row3 = new RowConstraints();
			row3.setPercentHeight(20.5);
			RowConstraints row4 = new RowConstraints();
			row4.setPercentHeight(20.5);
			RowConstraints row5 = new RowConstraints();
			row5.setPercentHeight(20.5);
			RowConstraints row6 = new RowConstraints();
			row6.setPercentHeight(20.5);
			RowConstraints row7 = new RowConstraints();
			row7.setPercentHeight(10);
			this.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);
			
			//Just a test to see if we can add items outside the GridWorld class retroactively
			Label Eve = new Label("Eve!");
			GridWorld.getInstance().add(Eve, 0, 0);
			Eve.setVisible(true);
			//failed
			
			
			this.setGridLinesVisible(true);

		}
		
		private void buttonSetup(){
			BACK = new Button();
			FORWARD = new Button();
			PLAY = new Button();
			RESET = new Button();
			BACK.setGraphic(imageBack);
			FORWARD.setGraphic(imageRight);
			PLAY.setGraphic(imagePlay);
			RESET.setGraphic(imageReset);
			
			BACK.setOnAction(ButtonHandlers::BACK_BUTTON_HANDLER);
			FORWARD.setOnAction(ButtonHandlers::FORWARD_BUTTON_HANDLER);
			PLAY.setOnAction(ButtonHandlers::PLAY_BUTTON_HANDLER);
			RESET.setOnAction(ButtonHandlers::RESET_BUTTON_HANDLER);
		}

		private static SandboxPane getInstance() {
			return (instanceOfMainMenuPane == null) ? instanceOfMainMenuPane = new SandboxPane()
					: instanceOfMainMenuPane;
		}
	}

	private static SandboxScene instanceOfSandboxScene = null;

	private SandboxScene(Parent arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}

	public static SandboxScene getInstance() {
		return (SandboxScene.instanceOfSandboxScene == null) ? instanceOfSandboxScene = new SandboxScene(
				SandboxPane.getInstance(), MainApp.WINDOW_WIDTH,
				MainApp.WINDOW_HEIGHT) : instanceOfSandboxScene;
	}
}
