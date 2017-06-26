package pl.sda.model;

//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;

//import java.math.BigDecimal;

public class Record {

	private Date date;
	private double close; // BigDecimal zamiast double?

	public Record(Date date, double close) {

		this.date = date;
		this.close = close;
	}

	/*
	 * public Record (String date, String close){
	 * 
	 * this.close = Double.parseDouble(close); DateFormat format = new
	 * SimpleDateFormat("yyyy - MM - dd"); try { this.date = format.parse(date);
	 * } catch (ParseException e) { e.printStackTrace(); } }
	 */

	public Date getDate() {
		return this.date;
	}

	public double getClose() {
		return this.close;
	}
}
