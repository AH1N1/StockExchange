package pl.sda.model;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.sda.technicalanalyse.PointAndFigureChart;

public class JavaFX {

	boolean isChart = true;

	// Starting JavaFX
	public void startWindow(Stage stage) {

		// Creating stockExchange
				StockExchange stockExchange = new StockExchange();
				
		// Setting main window
		stage.setTitle("StockExchange");
		Scene scene = new Scene(new Group(), 500, 450);
		stage.setResizable(false);

		// Creating menu bar and adding sub menus
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());

		Menu fileMenu = new Menu("File");
		Menu helpMenu = new Menu("Help");

		MenuItem addMenu = new MenuItem("Add company");
		MenuItem removeMenu = new MenuItem("Remove company");
		MenuItem saveChartToPNG = new MenuItem("Save chart to .png");

		MenuItem aboutMenu = new MenuItem("About");

		fileMenu.getItems().addAll(addMenu, removeMenu, saveChartToPNG);
		helpMenu.getItems().addAll(aboutMenu);

		menuBar.getMenus().addAll(fileMenu, helpMenu);

		// Creating toggles
		ToggleGroup group = new ToggleGroup();

		ToggleButton tb1 = new ToggleButton("1 month");
		tb1.setToggleGroup(group);
		tb1.setSelected(true);

		ToggleButton tb2 = new ToggleButton("6 months");
		tb2.setToggleGroup(group);

		ToggleButton tb3 = new ToggleButton("1 year");
		tb3.setToggleGroup(group);

		// Creating ComboBox with companies
		final ComboBox<String> companyComboBox = new ComboBox<String>();

		for (int i = 0; i < stockExchange.getCompanies().size(); i++) {
			companyComboBox.getItems().add(stockExchange.getCompanies().get(i).getName());
		}

		companyComboBox.setPrefWidth(scene.getWidth() * 0.415);

		// Creating button
		final Button button = new Button("Show chart");

		// Grid
		GridPane grid = new GridPane();

		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));

		grid.add(companyComboBox, 0, 0);
		grid.add(button, 4, 0);

		grid.add(tb1, 1, 0);
		grid.add(tb2, 2, 0);
		grid.add(tb3, 3, 0);

		// Creating root and adding everything to it
		Group root = (Group) scene.getRoot();

		root.getChildren().add(grid);
		root.getChildren().get(0).setTranslateY(25);

		root.getChildren().add(1, new PointAndFigureChart().draw());
		root.getChildren().get(1).setTranslateY(55);

		root.getChildren().add(2, menuBar);
		root.getChildren().get(2).setTranslateY(0);

		// Drawing chart
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				// Removing old chart
				if (isChart) {
					root.getChildren().remove(1);
					isChart = false;
				}

				// For testing
				for (int i = 0; i < stockExchange.getCompanies().size(); i++) {
					if (stockExchange.getCompanies().get(i).getName().equals(companyComboBox.getValue())) {

						int period;

						if (group.getSelectedToggle().equals(tb1)) {
							period = 1;
						} else if (group.getSelectedToggle().equals(tb2)) {
							period = 6;
						} else {
							period = 12;
						}

						stockExchange.getCompanies().get(i).parserCSV(period);
					}
				}

				root.getChildren().add(1, new PointAndFigureChart().draw());
				root.getChildren().get(1).setTranslateY(55);

				isChart = true;
			}
		});

		// Saving screenshot
		saveChartToPNG.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				saveAsPng(stage, scene);
			}
		});

		// Creating window
		stage.setScene(scene);
		stage.show();
	}

	private void saveAsPng(Stage stage, Scene scene) {
		WritableImage image = scene.snapshot(null);

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG", "*.png");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(stage);

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
