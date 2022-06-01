package vue.calendar.date;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import vue.calendar.HomeMadeCalendar;

public class YearMonthPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private YearPanel yearPanel;
	private MonthPanel monthPanel;
	
	public YearMonthPanel(HomeMadeCalendar calendar){
		setLayout(new GridLayout(2,1));
		
		yearPanel = new YearPanel(calendar);
		add(yearPanel,BorderLayout.NORTH);
		
		monthPanel = new MonthPanel(calendar);
		add(monthPanel,BorderLayout.NORTH);
	}
	
	public void refresh() {
		yearPanel.refresh();
		monthPanel.refresh();
	}
	
	public int getYear() {
		return yearPanel.getYear();
	}
		
	public int getMonth() {
		return monthPanel.getMonth();
	}

	public void decrementYear() {
		yearPanel.decrement();
	}
	
	public void incrementYear() {
		yearPanel.increment();
	}

	public void setCurrentDate() {
		yearPanel.setCurrentYear();
		monthPanel.setCurrentMonth();
		
	}

	public void setYear(int year) {
		yearPanel.setYear(year);
	}

	public void setMonth(int month) {
		monthPanel.setMonth(month);
	}

}
