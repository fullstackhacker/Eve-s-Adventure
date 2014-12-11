package views;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javax.swing.JLabel;

public class Running extends Label{

	private static Running instant = null;
	
	private Running(){
		setText("Trust in the code!");
		GridPane.setHalignment(this, HPos.CENTER);
	}
	
	public static Running getInstance() {
		return (instant == null) ? instant = new Running() : instant;
	}
	
}
