package vue.calendar.date;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import vue.calendar.HomeMadeCalendar;
import vue.calendar.time.TimePanel;


public class DayPanel extends JPanel {
	private static final long serialVersionUID = 1L;	
	
	private DayNumberPanel dayNumberPanel;
	private DayNamePanel dayNamePanel;
	private TimePanel timePanel;
	
	public DayPanel(HomeMadeCalendar calendar) {
		
		setLayout(new BorderLayout());

		
		dayNamePanel = new DayNamePanel(calendar);
		dayNumberPanel = new DayNumberPanel(calendar);
		
		add(dayNumberPanel,BorderLayout.CENTER);
		add(dayNamePanel,BorderLayout.NORTH);
		
	
	}
	


	public void refresh() {
		dayNumberPanel.refresh();
		dayNamePanel.refresh();
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




















