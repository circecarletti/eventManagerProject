package vue.calendar;

import model.MyDate;

public class MyDay {

	private int day;
	private int month;
	private int year;
	
	public MyDay(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public MyDay() {
	}
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public void setDate(int year, int month, int day) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}
	public String toString() {
		return String.format("%02d/%02d/%04d", day, month, year);	
	}
	
	public boolean dayIsEqual(MyDay myDay) {
		return (day == myDay.day && month == myDay.month && year == myDay.year);
	}
	public boolean dayIsEqual(MyDate myDate) {
		return (day == myDate.getDay() && month == myDate.getMonth() && year == myDate.getYear());
	}

	
	
}
