package views.grid;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public final class Cols extends GridPane {

	private static Cols instant = null;

	private Cols() {

		this.setGridLinesVisible(true);

		Label C1, C2, C3, C4, C5;

		

		C1 = new Label("0");
		C2 = new Label("1");
		C3 = new Label("2");
		C4 = new Label("3");
		C5 = new Label("4");


		GridPane.setHalignment(C1, HPos.CENTER);
		GridPane.setHalignment(C2, HPos.CENTER);
		GridPane.setHalignment(C3, HPos.CENTER);
		GridPane.setHalignment(C4, HPos.CENTER);
		GridPane.setHalignment(C5, HPos.CENTER);
		
		this.addRow(0, C1, C2, C3, C4, C5);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(20);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(20);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(20);
		this.getColumnConstraints().addAll(column1, column2, column3, column4,
				column5);

		

		this.setPadding(new Insets(5, 5, 5, 5));

	}

	public static Cols getInstance() {
		return (instant == null) ? new Cols() : instant;
	}

}