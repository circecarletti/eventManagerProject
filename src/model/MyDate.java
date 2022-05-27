package model;

public class MyDate {
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	
	
	public MyDate() {
		super();
			this.year = 0;
			this.month = 1;
			this.day = 1;
			this.hour = 0;
			this.minute = 0;
	}
	
	public MyDate(int year, int month, int day) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.hour = 0;
			this.minute = 0;
	}
	
	public MyDate(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
}

	
	@Override
	public int hashCode() {
		 int result = 24;
		 result = 1 * result + (int) minute;
		 result = 2 * result + (int) hour;
		 result = 3 * result + (int) day;
		 result = 4 * result + (int) month;
		 result = 5 * result + (int) year;
	     return result;
	}


	public boolean setDate(int year, int month, int day, int hour, int minute) {
		if(dateIsValid(day, month, year) && timeIsValid(minute, hour)) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.hour = hour;
			this.minute = minute;
			return true;
		}else {
			System.out.println("Date isn't valid!");
			return false;
		}
	}
	
	public boolean setDate(int year, int month, int day) {
		if(dateIsValid(day, month, year)) {
			this.year = year;
			this.month = month;
			this.day = day;
			return true;
		}else {
			System.out.println("Date is not valid !");
			return false;
		}
	}
	
	public boolean setTime(int hour, int minute) {
		if(timeIsValid(minute, hour)) {
			this.hour = hour;
			this.minute = minute;
			return true;
		}else {
			System.out.println("Time is not valid !");
			return false;
		}
	}
	
	public boolean setDate(MyDate newDate) {
		if(newDate.isValid()) {
			this.day = newDate.day;
			this.month = newDate.month;
			this.year = newDate.year;
			this.hour = newDate.hour;
			this.minute = newDate.minute;
			return true;
		}else {
			System.out.println("Date isn't valid!");
			return false;
		}
	}
	

	
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}


	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}



	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}


	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}



	public void setInvalid() {
		this.day = -1;
	}
	
	/*
	public String toString() {
		return "  " + this.day + "/" + this.month + "/" + this.year + "  " + this.hour + ":" + this.minute;	
	}*/
	
	@Override
	public String toString() {
		return String.format("%02d/%02d/%04d %02d:%02d", day, month, year, hour, minute);	
	}
	
	
	public static boolean leapYear(int year) {
		if(year % 4 == 0) {
			if(year % 100 != 0) {
				return true;
			}else if(year % 400 == 0){
				return true;
			}
		}
		return false;
	}

	public static boolean dateIsValid(int day, int month, int year) {
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 9 || month == 11) {
			if(day < 1 || day > 31) {
				return false;
			}
		}else if (month == 4 || month == 6 || month == 8 || month == 10 || month == 12) {
			if (day < 1 || day > 30) {
				return false;
			}
		}else if (month == 2) {
			if(MyDate.leapYear(year)) {
				if (day < 1 || day > 28) {
					return false;
				}
			}else {
				if(day < 1 || day > 29) {
					return false;
				}
			}
		}else {
			return false;
		}
		return true;
	}


	public static boolean timeIsValid(int minute, int hour) {
		if(minuteIsValid(minute) && hourIsValid(hour)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean minuteIsValid(int minute) {
		if(minute >= 0 && minute < 60)return true;
		else return false;
	}
	public static boolean hourIsValid(int hour) {
		if(hour >= 0 && hour < 24)return true;
		else return false;
	}
	
	
	public boolean isValid() {
		return dateIsValid(this.day, this.month, this.year) && timeIsValid(this.minute, this.hour);
	}


	public boolean dayIsEqual(MyDate date) {
		return (day == date.day && month == date.month && year == date.year);
	}
	
}
