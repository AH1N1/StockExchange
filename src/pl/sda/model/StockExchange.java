package pl.sda.model;

import pl.sda.technicalanalyse.PointAndFigureAnalyse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockExchange {

    private List<Company> Companies = new ArrayList<>();

    public StockExchange() {
        readingFromFile();
        System.out.println(Companies.get(0).toString());
    }

    private void readingFromFile() {
        try {
            // Opening file with names and urls
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));


            //nie lepiej do while ?
            String name = br.readLine();
            String downloadURL = br.readLine();

            int i = 0;

            while (!(name == null)) {

                // Adding company and download .csv
                Companies.add(new Company(name, downloadURL));
                Companies.get(i).downloadCSV();

               //dorazne niekoniecznie tu wywolywac
                Companies.get(i).parserCSV(30);
                PointAndFigureAnalyse analyse = new PointAndFigureAnalyse(Companies.get(i), 0.1,1);
                try {
                    analyse.getAnalyse();
                } catch (Exception e) {
                    e.printStackTrace();
                }


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
