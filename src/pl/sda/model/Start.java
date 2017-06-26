package pl.sda.model;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.sda.technicalanalyse.PointAndFigureChart;

public class Start extends Application {

	public static void main(String[] args) {

		new StockExchange();

		//Chart test
		launch(args);
	}

	//Must Be Here
	@Override
	public void start(Stage primaryStage) throws Exception {
		new PointAndFigureChart().draw(primaryStage);
	}

}
