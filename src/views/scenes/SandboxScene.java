package views.scenes;

import controllers.ButtonHandlers;
import views.MainApp;
import views.TopMenu;
import views.tabs.GameTabs;
import views.tips.ProTips;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public final class SandboxScene extends Scene {
	
	private static final class SandboxPane extends GridPane {

		private static SandboxPane instanceOfMainMenuPane = null;

		private SandboxPane() {
			this.getStylesheets().add("./sandbox_style.css");
			final Button BACK = new Button("BACK");
			final Button FORWARD = new Button("FORWARD");
			final Button PLAY = new Button("PLAY");
			final Button RESET = new Button("RESET");
			
			BACK.setOnAction(ButtonHandlers::BACK_BUTTON_HANDLER);
			FORWARD.setOnAction(ButtonHandlers::FORWARD_BUTTON_HANDLER);
			PLAY.setOnAction(ButtonHandlers::PLAY_BUTTON_HANDLER);
			RESET.setOnAction(ButtonHandlers::RESET_BUTTON_HANDLER);

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
			protips.setId("protips");

			this.add(gametabs, 0, 1, 1, 5);
			this.add(topMenu, 0, 0, 6, 1);
			this.add(protips, 0, 6, 2, 1);
			this.add(BACK, 2, 1);
			this.add(FORWARD, 3, 1);
			this.add(PLAY, 4, 1);
			this.add(RESET, 5, 1);
			

			ColumnConstraints column1 = new ColumnConstraints();
			column1.setPercentWidth(36);
			ColumnConstraints column2 = new ColumnConstraints();
			column2.setPercentWidth(36);
			ColumnConstraints column3 = new ColumnConstraints();
			column3.setPercentWidth(7);
			ColumnConstraints column4 = new ColumnConstraints();
			column4.setPercentWidth(7);
			ColumnConstraints column5 = new ColumnConstraints();
			column5.setPercentWidth(7);
			ColumnConstraints column6 = new ColumnConstraints();
			column6.setPercentWidth(7);
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
