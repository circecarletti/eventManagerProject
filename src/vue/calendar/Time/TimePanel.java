package vue.calendar.Time;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vue.calendar.HomeMadeCalendar;


public class TimePanel extends JPanel{
	private static final long serialVersionUID = 5690529964400739729L;
	
	private TimeTextField txtFieldHours;
	private TimeTextField txtFieldMinutes;
	
	
	
	private int hour = 0;
	private int minute = 0;
	
	public TimePanel(HomeMadeCalendar calendar){
		
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
	
		txtFieldHours = new HourTextField(calendar, "hh");
		
		JLabel lblTime = new JLabel(" : ");
		
		txtFieldMinutes = new MinuteTextField(calendar, "mm");
		
		topPanel.add(txtFieldHours);
		topPanel.add(lblTime);
		topPanel.add(txtFieldMinutes);
		
		add(topPanel,BorderLayout.CENTER);
			

		
	}
	
	public void refresh() {
		if(txtFieldMinutes.timeIsValid()) {
			txtFieldMinutes.setBackground(Color.white);
			minute = Integer.parseInt(txtFieldMinutes.getText());
		}
		else {
			txtFieldMinutes.setBackground(new Color(255,102,102));
		}
		
		if(txtFieldHours.timeIsValid()) {
			txtFieldHours.setBackground(Color.white);
			hour = Integer.parseInt(txtFieldHours.getText());
		}
		else {
			txtFieldHours.setBackground(new Color(255,102,102));
		}

	}
	
	public boolean timeIsValid() {
		return (minuteIsValid() && hourIsValid());
	}
	
	public boolean minuteIsValid() {
		return txtFieldMinutes.timeIsValid();
	}
	
	public boolean hourIsValid() {
		return txtFieldHours.timeIsValid();
	}

	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}

}
