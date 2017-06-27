package pl.sda.technicalanalyse;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class PointAndFigureChart {

	public EventHandler<ActionEvent> draw() {

		// Handling chart exceptions
		try {
			Stage stage = new Stage();

			// Application name
			stage.setTitle("Technical analysis");

			// Size of chart
			final NumberAxis xAxis = new NumberAxis(0, 10, 1);
			final NumberAxis yAxis = new NumberAxis(0, 10, 1);

			// Axis name
			final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);
			xAxis.setLabel("Time");
			yAxis.setLabel("Price");

			// Chart name
			sc.setTitle("Investment Overview");

			// Creating series
			XYChart.Series seriesX = new XYChart.Series();
			seriesX.setName("X");
			seriesX.getData().add(new XYChart.Data(1, 1));

			XYChart.Series seriesO = new XYChart.Series();
			seriesO.setName("O");
			seriesO.getData().add(new XYChart.Data(2, 2));

			// Drawing chart
			sc.getData().addAll(seriesX, seriesO);
			Scene scene = new Scene(sc, 500, 500);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
