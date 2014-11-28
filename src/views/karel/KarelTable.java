package views.karel;

import controllers.ButtonHandlers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import views.tips.ProTips;

public final class KarelTable extends GridPane {

	private static KarelTable instant = null;

	private KarelTable() {

		ObservableList<String> karelCode = FXCollections.observableArrayList(
				"Top", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
				" ", " ", " ", " ", " ", " ", "Test Text ", " ", " ", " ", " ", " ",
				" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
				" ", " ", "Bottom");
		ListView<String> listView = new ListView<String>(karelCode);
		Button EDIT = new Button("EDIT");
		Button DELETE = new Button("DELETE");

		this.add(listView, 0, 0, 2, 1);
		this.add(EDIT, 0, 1);
		this.add(DELETE, 1, 1);
		
		EDIT.setOnAction(ButtonHandlers::EDIT_BUTTON_HANDLER);
		DELETE.setOnAction(ButtonHandlers::DELETE_BUTTON_HANDLER);

		GridPane.setFillWidth(EDIT, true);
		GridPane.setHalignment(EDIT, HPos.CENTER);
		GridPane.setFillWidth(DELETE, true);
		GridPane.setHalignment(DELETE, HPos.CENTER);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(50);
		this.getColumnConstraints().addAll(column1, column2);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(95);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(5);
		this.getRowConstraints().addAll(row1, row2);
		
		this.setPadding(new Insets(5, 5, 5, 5));

	}

	public static KarelTable getInstance() {
		return (instant == null) ? new KarelTable() : instant;
	}
}
