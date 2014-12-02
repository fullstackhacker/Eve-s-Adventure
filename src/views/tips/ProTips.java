package views.tips;

import java.util.Iterator;
import java.util.Timer;

import models.campaign.Tips;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public final class ProTips extends GridPane {

	private static ProTips instant = null;

	long delay = 30000;
	Iterator<String> tips = Tips.getPossibleTips();

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

		System.out.println(tips.next());
		System.out.println(tips.next());
		System.out.println(tips.next());

		// Timer timer = new Timer(true);
		/*
		 * int i = 0; while(i < 5){ if (!tips.hasNext()){ tips =
		 * Tips.getPossibleTips(); } TFTips.setText(tips.next()); i++; }
		 */
		/*
		 * timer.scheduleAtFixedRate(new TimerTask() {
		 * 
		 * @Override public void run() { if (!tips.hasNext()){ tips =
		 * Tips.getPossibleTips(); } TFTips.setText(tips.next()); } }, delay,
		 * delay);
		 */

		/*
		 * long startTime = System.nanoTime();
		 * 
		 * while (true) { if (System.nanoTime() - startTime >= delay) {
		 * Platform.runLater(new Runnable() {
		 * 
		 * @Override public void run() { if (!tips.hasNext()) { tips =
		 * Tips.getPossibleTips(); } TFTips.setText(tips.next()); } });
		 * startTime = System.nanoTime(); } }
		 */

		Timeline tipSwitch = new Timeline(new KeyFrame(Duration.seconds(10),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (!tips.hasNext()) {
							tips = Tips.getPossibleTips();
						}
						TFTips.setText(tips.next());
					}
				}));
		tipSwitch.setCycleCount(Timeline.INDEFINITE);
		tipSwitch.play();

	}

	public static ProTips getInstance() {
		return (instant == null) ? new ProTips() : instant;
	}
}
