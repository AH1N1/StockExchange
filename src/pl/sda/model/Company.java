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

	private String name, downloadURL;
	private List<Record> records;

	public Company(String name, String downloadURL) {

		this.name = name + ".csv";
		this.downloadURL = downloadURL;
		this.records = new ArrayList<>();
	}

	
	public void downloadCSV() throws IOException {

		// The file that will be saved on your computer
		String fileName = name;
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

		System.out.println(name + " downloaded");
	}

	public void parserCSV(int Date/*,String name*/) {

		String csvFile = name;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			while ((line = br.readLine()) != null) {
				
				String[] figures = line.split(cvsSplitBy);
				
				String dateString = figures[0];
				String closeString = figures[4];
		//		System.out.println("Date = "        + figures[0]);
		//		System.out.println("Open =  "       + figures[1]);
		//		System.out.println("High =  "       + figures[2]);
		//		System.out.println("Low = "         + figures[3]);
		//		System.out.println("Close = "       + figures[4]);
		//		System.out.println("Volume = "      + figures[5]);
		//		System.out.println("Ex-dividend = " + figures[6]);
		//		System.out.println("Split ratio = " + figures[7]);
		//		System.out.println("Adj.open "      + figures[8]);
		//		System.out.println("Adj.high = "    + figures[9]);
		//		System.out.println("Adj.low = "     + figures[10]);
		//		System.out.println("Adj.close = "   + figures[11]);
		//		System.out.println("Adj.volume = "  + figures[12]);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = format.parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy");
				String yearString = df.format(date);
				int yearInt = Integer.parseInt(yearString);
				double close = Double.parseDouble(closeString);
				
				//Tutaj trzeba bêdzie sypn¹æ wyj¹tek w tym stylu
				if (yearInt>Date)
				if (figures[0]!=null && figures[4]!=null)
				records.add(new Record(date,close));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
