package vue.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.MyDate;
import vue.calendar.date.DayPanel;
import vue.calendar.date.YearMonthPanel;
import vue.calendar.time.TimePanel;


public class HomeMadeCalendar extends JFrame{

	private static final long serialVersionUID = 6295540607883675075L;
	
	public static enum ERROR_STATE { 
		maxLengthReached,
		emptyHourField,
		emptyMinuteField,
		invalidMinutes,
		invalidHours,
		invalidCharacter,
		noError
	};
	
	private ERROR_STATE errorState;
	
	
	public final int REF_YEAR = 2024;
	public final int firstMondayIndex2024[] = {0,3,4,0,2,5,0,3,6,1,4,6};
	private int firstMondayIndex[] = new int[12];
	
	private int numberOfDay[] = {31,28,31,30,31,30,31,31,30,31,30,31}; // Non bisextile year
	
	private JButton confirmButton;
	private JButton cancelButton;
	
	private DayPanel dayPanel;
	private YearMonthPanel yearMonthPanel;
	private TimePanel timePanel;
	
	private MyDate date;
	@SuppressWarnings("unused")
	private MyDay myDay;

	public HomeMadeCalendar(MyDay myDay) {
		setBounds(200,200,350,250);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("Homemade Calendar");
		
		this.myDay = myDay;
		
		

		yearMonthPanel = new YearMonthPanel(this);
		add(yearMonthPanel,BorderLayout.NORTH);
		
		dayPanel = new DayPanel(this);
		add(dayPanel,BorderLayout.CENTER);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,1));
		
		confirmButton = new JButton("Confirm");
		confirmButton.setPreferredSize(new Dimension(0,15));
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myDay.setDate(getYear(), getMonth(), getDay());
				close();
			}
		});
		
		
		bottomPanel.add(confirmButton,BorderLayout.SOUTH);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(0,15));
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bottomPanel.add(cancelButton);
		
		add(bottomPanel,BorderLayout.SOUTH);
		
		
		setCurrentDate();
		
		refresh();
		setVisible(true);
	}

	public HomeMadeCalendar(MyDate date) {
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(200,200,350,250);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("Homemade Calendar");
		
		this.date = date;

		yearMonthPanel = new YearMonthPanel(this);
		add(yearMonthPanel,BorderLayout.NORTH);
		
		dayPanel = new DayPanel(this);
		timePanel = new TimePanel(this);
		
		dayPanel.add(timePanel,BorderLayout.SOUTH);
		
		add(dayPanel,BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,1));
		
		confirmButton = new JButton("Confirm");
		confirmButton.setPreferredSize(new Dimension(0,15));
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				date.setDate(getYear(), getMonth(), getDay(), getHour(), getMinute());
				close();
			}
		});
		bottomPanel.add(confirmButton,BorderLayout.SOUTH);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(0,15));
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bottomPanel.add(cancelButton);
		
		add(bottomPanel,BorderLayout.SOUTH);
		
		setCurrentDate();
		
		refresh();
		setVisible(true);
	}
	
	
	
	public void setErrorState(ERROR_STATE errorState) {
		this.errorState = errorState;
	}
	
	public void setErrorState2(ERROR_STATE errorState) {
		timePanel.setErrorText(errorState);
	}
	
	private void setCurrentDate() {
		yearMonthPanel.setCurrentDate();
		dayPanel.setCurrentDate();
		
	}
	
	
	public void refresh() {
		
		yearMonthPanel.refresh();
		dayPanel.refresh();
		if(date != null) {
			timePanel.refresh();
			errorState = ERROR_STATE.noError;
			timePanel.setErrorText(errorState);
			refreshConfirmButton();
		}
			
	}
	
	public void refreshConfirmButton() {
		if(timePanel.timeIsValid()) {
			confirmButton.setEnabled(true);
			//confirmButton.setText(String.format("%02d/%02d/%04d %02d:%02d",getDay(),getMonth(),getYear(),getHour(),getMinute()));
			//setErrorState(ERROR_STATE.noError);
		}
		else {
			/*
			if(dayPanel.minuteIsValid()) {
				confirmButton.setText(String.format("%02d/%02d/%04d --:%02d",getDay(),getMonth(),getYear(),getMinute()));
				//setErrorState(ERROR_STATE.invalidHours);
			}
			else if(dayPanel.hourIsValid()) {
				confirmButton.setText(String.format("%02d/%02d/%04d %02d:--",getDay(),getMonth(),getYear(),getHour()));
				//setErrorState(ERROR_STATE.invalidMinutes);
			}
			else {
				confirmButton.setText(String.format("%02d/%02d/%04d --:--",getDay(),getMonth(),getYear()));
			}
			*/
			confirmButton.setEnabled(false);	
		}
	}
	private void close() {
		
		dispose();
	}
	
	public int getYear() {
		return yearMonthPanel.getYear();
	}
	
	public int getMonth() {
		return yearMonthPanel.getMonth();
	}
	
	public int getDay() {
		return dayPanel.getDay();
	}
	
	public int getHour() {
		return timePanel.getHour();
	}
	
	public int getMinute() {
		return timePanel.getMinute();
	}


	public void decrementYear() {
		yearMonthPanel.decrementYear();
	}
	
	public void incrementYear() {
		yearMonthPanel.incrementYear();
	}


	public int[] getFirstMondayIndex() {
		return firstMondayIndex;
	}


	public void setFirstMondayIndex(int firstMondayIndex[]) {
		this.firstMondayIndex = firstMondayIndex;
	}

	public int[] getNumberOfDay() {
		return numberOfDay;
	}



}
