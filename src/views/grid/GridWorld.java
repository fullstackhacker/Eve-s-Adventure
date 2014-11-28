package views.grid;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public final class GridWorld extends GridPane {

	private static GridWorld instant = null;

	private GridWorld() {

		this.setGridLinesVisible(true);

		Label R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, C1, C2, C3, C4, C5, fill;

		R1 = new Label("1");
		R2 = new Label("2");
		R3 = new Label("3");
		R4 = new Label("4");
		R5 = new Label("5");
		R6 = new Label("6");
		R7 = new Label("7");
		R8 = new Label("8");
		R9 = new Label("9");
		R10 = new Label("10");

		C1 = new Label("1");
		C2 = new Label("2");
		C3 = new Label("3");
		C4 = new Label("4");
		C5 = new Label("5");

		fill = new Label("*");

		GridPane.setHalignment(C1, HPos.CENTER);
		GridPane.setHalignment(C2, HPos.CENTER);
		GridPane.setHalignment(C3, HPos.CENTER);
		GridPane.setHalignment(C4, HPos.CENTER);
		GridPane.setHalignment(C5, HPos.CENTER);
		GridPane.setHalignment(fill, HPos.CENTER);
		GridPane.setHalignment(R1, HPos.CENTER);
		GridPane.setHalignment(R2, HPos.CENTER);
		GridPane.setHalignment(R3, HPos.CENTER);
		GridPane.setHalignment(R4, HPos.CENTER);
		GridPane.setHalignment(R5, HPos.CENTER);
		GridPane.setHalignment(R6, HPos.CENTER);
		GridPane.setHalignment(R7, HPos.CENTER);
		GridPane.setHalignment(R8, HPos.CENTER);
		GridPane.setHalignment(R9, HPos.CENTER);
		GridPane.setHalignment(R10, HPos.CENTER);

		this.add(fill, 0, 0);
		this.addColumn(0, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10);
		this.addRow(0, C1, C2, C3, C4, C5);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(3);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(19.2);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(19.2);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(19.2);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(19.2);
		ColumnConstraints column6 = new ColumnConstraints();
		column6.setPercentWidth(19.2);
		this.getColumnConstraints().addAll(column1, column2, column3, column4,
				column5, column6);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(3);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(9.7);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(9.7);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(9.7);
		RowConstraints row5 = new RowConstraints();
		row5.setPercentHeight(9.7);
		RowConstraints row6 = new RowConstraints();
		row6.setPercentHeight(9.7);
		RowConstraints row7 = new RowConstraints();
		row7.setPercentHeight(9.7);
		RowConstraints row8 = new RowConstraints();
		row8.setPercentHeight(9.7);
		RowConstraints row9 = new RowConstraints();
		row9.setPercentHeight(9.7);
		RowConstraints row10 = new RowConstraints();
		row10.setPercentHeight(9.7);
		RowConstraints row11 = new RowConstraints();
		row11.setPercentHeight(9.7);
		this.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6,
				row7, row8, row9, row10, row11);

		// this.setPadding(new Insets(5, 5, 5, 5));

	}

	public static GridWorld getInstance() {
		return (instant == null) ? new GridWorld() : instant;
	}

}