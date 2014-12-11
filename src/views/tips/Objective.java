package views.tips;

import java.util.Iterator;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import models.campaign.Tips;
import views.grid.GridWorld;

public final class Objective extends GridPane {

	private static Objective instant = null;

	long delay = 30000;
	Iterator<String> tips = Tips.getPossibleTips();

	private Objective() {
		final Label header = new Label("Objective! - ");
		final Label obj = new Label();
		if(!GridWorld.getInstance().getWorld().getFindObj()){
			obj.setText("Find and collect all the bamboo!");
		} else {
			obj.setText("Find Eve's friend and save her!");
		}
		
		// this.getChildren().addAll(header, obj);

		/*
		 * header.setMinSize(100, 200); obj.setMinSize(100, 200);
		 */

		this.add(header, 0, 0);
		this.add(obj, 1, 0);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(80);
		this.getColumnConstraints().addAll(column1, column2);

		GridPane.setHalignment(header, HPos.LEFT);
		GridPane.setHalignment(obj, HPos.LEFT);
		obj.setWrapText(true);

		this.setPadding(new Insets(5, 5, 5, 5));

	}

	public static Objective getInstance() {
		return (instant == null) ? instant = new Objective() : instant;
	}
}
