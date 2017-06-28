package pl.sda.technicalanalyse;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class PointAndFigureChart {

	public ScatterChart<Number, Number> draw() {

		final NumberAxis xAxis = new NumberAxis(0, 10, 1);
		final NumberAxis yAxis = new NumberAxis(0, 10, 1);

		final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);

		// Axis name
		xAxis.setLabel("Time");
		yAxis.setLabel("Price");

		// Chart name
		sc.setTitle("Investment Overview");

		// Creating series
		XYChart.Series seriesX = new XYChart.Series();
		seriesX.setName("X");
		// seriesX.getData().add(new XYChart.Data(1, 1));

		XYChart.Series seriesO = new XYChart.Series();
		seriesO.setName("O");
		// seriesO.getData().add(new XYChart.Data(2, 2));

		// Adding axis to chart
		sc.getData().addAll(seriesX, seriesO);

		return sc;
	}

}
