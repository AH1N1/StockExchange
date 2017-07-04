package pl.sda.model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Company {

    private String name, nameCSV, downloadURL;
    private List<Record> records;

    public Company(String name, String downloadURL) {

        this.name = name;
        this.nameCSV = name + ".csv";
        this.downloadURL = downloadURL;
        this.records = new ArrayList<>();
    }


    public void downloadCSV() throws IOException {

        // The file that will be saved on your computer
        String fileName = nameCSV;
        // The file that you want to download
        URL link = new URL(downloadURL);

        // Code to download
        InputStream in = new BufferedInputStream(link.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(response);
        fos.close();
        // End download code

        System.out.println(nameCSV + " downloaded");
    }

    //po kolei czyta z pliku kazdy rekord i jesli jest on z roku wiekszego niz ten podany w parametrze dodaje do listy(po co ?)
    public void parserCSV(int period) {
        records.clear();


        String csvFile = nameCSV;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            //zmien while na taki ktory bierze pod uwage period
            //while ((line = br.readLine()) != null) {
            for (int i = 0; i < period; i++) {


                line = br.readLine();
                if (null == line) break;
                String[] figures = line.split(cvsSplitBy);

                String dateString = figures[0];
                String closeString = figures[4];
//				 System.out.println("Date = " + figures[0]);
//				 System.out.println("Open = " + figures[1]);
//				 System.out.println("High = " + figures[2]);
//				 System.out.println("Low = " + figures[3]);
//				 System.out.println("Close = " + figures[4]);
//				 System.out.println("Volume = " + figures[5]);
//				 System.out.println("Ex-dividend = " + figures[6]);
//				 System.out.println("Split ratio = " + figures[7]);
//				 System.out.println("Adj.open " + figures[8]);
//				 System.out.println("Adj.high = " + figures[9]);
//				 System.out.println("Adj.low = " + figures[10]);
//				 System.out.println("Adj.close = " + figures[11]);
//				 System.out.println("Adj.volume = " + figures[12]);


                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                double close = 0;
                try {

                    date = format.parse(dateString);
                    close = Double.parseDouble(closeString);

                    if (figures[0] != null && figures[4] != null)
                        records.add(new Record(date, close));
                } catch (Exception e) {
                    --i;
                    System.out.println(dateString);
                    System.out.println("double = " + close);
                    System.out.println("Error while parsing csv records");
                    //e.printStackTrace();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(records.size());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCSV() {
        return nameCSV;
    }

    public void setNameCSV(String nameCSV) {
        this.nameCSV = nameCSV;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        StringBuilder recordsStr = new StringBuilder();
        records.forEach(record -> recordsStr.append(record.toString()+"\n"));
        return "Company{" +
                "name='" + name + '\'' +
                ", records=" + recordsStr.toString() +
                '}';
    }
}
