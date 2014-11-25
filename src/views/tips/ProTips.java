package views.tips;

import views.tabs.GameTabs;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public final class ProTips extends HBox {

	private static ProTips instant = null;

	private ProTips() {
		final Label LTips = new Label("Pro Tips! - ");
		final Label TFTips = new Label(
				"This is a long example message to see how far out this message will go. In the future I may need to make this have a text wrap. Who knows. Man, I really hope this is long enough. I mean, will our tips really be longer then this. Maaaybe the objectives...whatever.");
		this.getChildren().addAll(LTips, TFTips);

		LTips.setMinSize(100, 200);
		TFTips.setMinSize(100, 200);
		GridPane.setHalignment(LTips, HPos.LEFT);
		GridPane.setHalignment(TFTips, HPos.LEFT);
		TFTips.setWrapText(true);

	}

	public static ProTips getInstance() {
		return (instant == null) ? new ProTips() : instant;
	}
}
