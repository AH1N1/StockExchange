package pl.sda.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.sda.technicalanalyse.PointAndFigureChart;

public class JavaFX {

	final Button button = new Button("Show chart");

	// Starting JavaFX
	public void startWindow(Stage stage) throws Exception {

		// Creating main window
		stage.setTitle("StockExchange");
		Scene scene = new Scene(new Group(), 450, 250);
		// stage.setResizable(false);

		// Creating stockExchange
		StockExchange stockExchange = new StockExchange();

		// Creating ComboBox with companies
		final ComboBox<String> companyComboBox = new ComboBox<String>();

		for (int i = 0; i < stockExchange.getCompanies().size(); i++) {
			companyComboBox.getItems().add(stockExchange.getCompanies().get(i).getName());
		}

		companyComboBox.setPrefWidth(scene.getWidth() * 0.64);

		// Grid
		GridPane grid = new GridPane();
		
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));

		grid.add(new Label("Company: "), 0, 0);
		grid.add(companyComboBox, 1, 0);
		grid.add(button, 02, 0);

		// Drawing chart
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new PointAndFigureChart().draw();
			}
		});

		// No idea
		Group root = (Group) scene.getRoot();
		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.show();
	}
}
