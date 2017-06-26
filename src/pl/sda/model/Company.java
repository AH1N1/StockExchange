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
import java.util.List;

public class Company {
	private String name, downloadURL;
	private List<Record> records;

	public Company(String name, String downloadURL) {
		this.name = name + ".csv";
		this.downloadURL = downloadURL;
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

	public void parserCSV(/*String name*/) {

		String csvFile = name + "csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while ((line = br.readLine()) != null) {
				
				String[] figures = line.split(cvsSplitBy);
				
				//Wrzuci� mo�liwo�� wybrania jakie dane bierzemy z jakiego zakresu
				//np. dane z lat xxxx-xxxx
				
				String date = figures[0];
				String close = figures[4];
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
				
				//Tutaj trzeba b�dzie sypn�� wyj�tek w tym stylu
				if (figures[0]!=null && figures[4]!=null)
				records.add(new Record(date,close));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}