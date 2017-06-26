package pl.sda.technicalanalyse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class PointAndFigureChart extends Application {
	
	public void draw(Stage args) {
		
		//Handling chart exceptions
		try {
			start(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Stage stage = new Stage();
		
		//Application name
		stage.setTitle("Technical analysis");
		
		//Size of chart
		final NumberAxis xAxis = new NumberAxis(0, 10, 1);
		final NumberAxis yAxis = new NumberAxis(0, 10, 1);
		
		//Axis name
		final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);
		xAxis.setLabel("Time");
		yAxis.setLabel("Price");
		
		//Chart name
		sc.setTitle("Investment Overview");

		//Creating series
		XYChart.Series seriesX = new XYChart.Series();
		seriesX.setName("X");
		seriesX.getData().add(new XYChart.Data(1, 1));

		XYChart.Series seriesO = new XYChart.Series();
		seriesO.setName("O");
		seriesO.getData().add(new XYChart.Data(2, 2));

		//Drawing chart
		sc.getData().addAll(seriesX, seriesO);
		Scene scene = new Scene(sc, 500, 500);
		stage.setScene(scene);
		stage.show();
	}

}
