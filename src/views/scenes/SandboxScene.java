package views.scenes;

import views.MainApp;
import views.tabs.GameTabs;
import views.tibs.ProTips;
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
			final Button BACK = new Button("BACK");
			final Button FORWARD = new Button("FORWARD");
			final Button PLAY = new Button("PLAY");
			final Button RESET = new Button("RESET");

			GridPane.setFillWidth(BACK, true);
			GridPane.setHalignment(BACK, HPos.CENTER);
			GridPane.setFillWidth(FORWARD, true);
			GridPane.setHalignment(FORWARD, HPos.CENTER);
			GridPane.setFillWidth(PLAY, true);
			GridPane.setHalignment(PLAY, HPos.CENTER);
			GridPane.setFillWidth(RESET, true);
			GridPane.setHalignment(RESET, HPos.CENTER);

			this.add(GameTabs.getInstance(), 0, 0, 1, 5);
			this.add(ProTips.getInstance(), 0, 5, 2, 1);
			this.add(BACK, 2, 0);
			this.add(FORWARD, 3, 0);
			this.add(PLAY, 4, 0);
			this.add(RESET, 5, 0);

			this.setGridLinesVisible(true);

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
			row1.setPercentHeight(5);
			RowConstraints row2 = new RowConstraints();
			row2.setPercentHeight(21.5);
			RowConstraints row3 = new RowConstraints();
			row3.setPercentHeight(21.5);
			RowConstraints row4 = new RowConstraints();
			row4.setPercentHeight(21.5);
			RowConstraints row5 = new RowConstraints();
			row5.setPercentHeight(21.5);
			RowConstraints row6 = new RowConstraints();
			row6.setPercentHeight(9);
			this.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6);

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
