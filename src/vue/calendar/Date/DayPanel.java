package vue.calendar.Date;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import vue.calendar.HomeMadeCalendar;
import vue.calendar.Time.TimePanel;


public class DayPanel extends JPanel {
	private static final long serialVersionUID = 1L;	
	
	private DayNumberPanel dayNumberPanel;
	private DayNamePanel dayNamePanel;
	private TimePanel timePanel;
	
	public DayPanel(HomeMadeCalendar calendar) {
		
		setLayout(new BorderLayout());

		
		dayNamePanel = new DayNamePanel(calendar);
		dayNumberPanel = new DayNumberPanel(calendar);
		timePanel = new TimePanel(calendar);
		
		add(dayNumberPanel,BorderLayout.CENTER);
		add(dayNamePanel,BorderLayout.NORTH);
		add(timePanel,BorderLayout.SOUTH);
		
	
	}


	public boolean timeIsValid() {
		return timePanel.timeIsValid();
	}
	
	public boolean minuteIsValid() {
		return timePanel.minuteIsValid();
	}
	
	public boolean hourIsValid() {
		return timePanel.hourIsValid();
	}

	public void refresh() {
		dayNumberPanel.refresh();
		dayNamePanel.refresh();
		timePanel.refresh();
	}

	public int getDay() {
		return dayNumberPanel.getDay();
	}

	public int getHour() {
		return timePanel.getHour();
	}
	
	public int getMinute() {
		return timePanel.getMinute();
	}
	public void setCurrentDate() {
		dayNumberPanel.setCurrentDay();
		
	}


}




















