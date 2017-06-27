package pl.sda.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import oracle.jrockit.jfr.Recording;

public class StockExchange {

	private List<Company> Companies = new ArrayList<>();

	public StockExchange() {
		readingFromFile();
	}

	private void readingFromFile() {
		try {
			// Opening file with names and urls
			BufferedReader br = new BufferedReader(new FileReader("file.txt"));

			String name = br.readLine();
			String downloadURL = br.readLine();

			int i = 0;

			while (!(name == null)) {

				// Adding company and download .csv
				Companies.add(new Company(name, downloadURL));
				Companies.get(i).downloadCSV();
				i++;

				name = br.readLine();
				downloadURL = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Company> getCompanies() {
		return Companies;
	}

}
