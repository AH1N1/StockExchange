package pl.sda.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Company {
	private String name, downloadURL;

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
}
