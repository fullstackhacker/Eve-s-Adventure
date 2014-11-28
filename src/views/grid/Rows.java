package views.grid;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public final class Rows extends GridPane {

	private static Rows instant = null;

	private Rows() {

		this.setGridLinesVisible(true);

		Label R1, R2, R3, R4, R5, R6, R7, R8, R9, R10;

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

		this.addColumn(0, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(10);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(10);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(10);
		RowConstraints row5 = new RowConstraints();
		row5.setPercentHeight(10);
		RowConstraints row6 = new RowConstraints();
		row6.setPercentHeight(10);
		RowConstraints row7 = new RowConstraints();
		row7.setPercentHeight(10);
		RowConstraints row8 = new RowConstraints();
		row8.setPercentHeight(10);
		RowConstraints row9 = new RowConstraints();
		row9.setPercentHeight(10);
		RowConstraints row10 = new RowConstraints();
		row10.setPercentHeight(10);
		this.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6,
				row7, row8, row9, row10);

		this.setPadding(new Insets(5, 0, 5, 5));

	}

	public static Rows getInstance() {
		return (instant == null) ? new Rows() : instant;
	}

}