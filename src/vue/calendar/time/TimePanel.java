package vue.calendar.time;

import java.awt.BorderLayout;
import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.JPanel;


import vue.calendar.ErrorLabel;
import vue.calendar.HomeMadeCalendar;
import vue.calendar.HomeMadeCalendar.ERROR_STATE;


public class TimePanel extends JPanel{
	private static final long serialVersionUID = 5690529964400739729L;
	
	private TimeTextField txtFieldHours;
	private TimeTextField txtFieldMinutes;
	
	private ErrorLabel errorLabel;
	
	
	private int hour = 0;
	private int minute = 0;
	
	HomeMadeCalendar calendar;
	
	public TimePanel(HomeMadeCalendar calendar){
		
		setLayout(new BorderLayout());
		this.calendar = calendar;
		
		JPanel topPanel = new JPanel();
	
		txtFieldHours = new HourTextField(calendar, "hh");
		
		JLabel lblTime = new JLabel(" : ");
		
		txtFieldMinutes = new MinuteTextField(calendar, "mm");
		
		topPanel.add(txtFieldHours);
		topPanel.add(lblTime);
		topPanel.add(txtFieldMinutes);
		
		add(topPanel,BorderLayout.CENTER);
		
		errorLabel = new ErrorLabel();
		add(errorLabel,BorderLayout.SOUTH);
		
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
	
	public void setErrorText(ERROR_STATE errorState) {
		switch(errorState) {
		case maxLengthReached:
			errorLabel.setText("Cannot insert more character");
			break;
		case invalidHours:
			errorLabel.setText("Invalid Hour");
			break;
		case invalidMinutes:
			errorLabel.setText("Invalid Minute");
			break;
		case invalidCharacter:
			errorLabel.setText("Please only enter numbers");
			break;
		case noError:
			errorLabel.setText(String.format("%02d/%02d/%04d %02d:%02d",calendar.getDay(),calendar.getMonth(),calendar.getYear(),calendar.getHour(),calendar.getMinute()));
			break;
		case emptyHourField:
			errorLabel.setText("Please fill the hour field");
			break;
		case emptyMinuteField:
			errorLabel.setText("Please fill the minute field");
			break;
		default:
			break;
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



	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}

}
