package views.tips;

import views.tabs.GameTabs;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public final class ProTips extends GridPane {

	private static ProTips instant = null;

	private ProTips() {
		final Label LTips = new Label("Pro Tip! - ");
		final Label TFTips = new Label(
				"This is Sandbox Mode! You can create your own senarios here. Check out the tabs to drop objects into the grid world!");
		// this.getChildren().addAll(LTips, TFTips);

		/*
		 * LTips.setMinSize(100, 200); TFTips.setMinSize(100, 200);
		 */

		this.add(LTips, 0, 0);
		this.add(TFTips, 1, 0);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(10);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(90);
		this.getColumnConstraints().addAll(column1, column2);

		GridPane.setHalignment(LTips, HPos.LEFT);
		GridPane.setHalignment(TFTips, HPos.LEFT);
		TFTips.setWrapText(true);

		this.setPadding(new Insets(5, 5, 5, 5));

	}

	public static ProTips getInstance() {
		return (instant == null) ? new ProTips() : instant;
	}
}
