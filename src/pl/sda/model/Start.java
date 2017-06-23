package pl.sda.model;

import java.io.IOException;

public class Start {

	public static void main(String[] args) {
		StockExchange Exchange = new StockExchange();
		
		Company Apple = new Company("Apple", "https://www.quandl.com/api/v3/datasets/WIKI/AAPL.csv");
		try {
			Apple.downloadCSV();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
