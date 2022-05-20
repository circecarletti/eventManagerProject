package vue.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.MyDate;
import vue.calendar.Date.DayPanel;
import vue.calendar.Date.YearMonthPanel;


public class HomeMadeCalendar extends JFrame{

	private static final long serialVersionUID = 6295540607883675075L;
	
	public final int REF_YEAR = 2024;
	public final int firstMondayIndex2024[] = {0,3,4,0,2,5,0,3,6,1,4,6};
	private int firstMondayIndex[] = new int[12];
	
	private int numberOfDay[] = {31,28,31,30,31,30,31,31,30,31,30,31}; // Non bisextile year
	
	private JButton confirmButton;
	private DayPanel dayPanel;
	private YearMonthPanel yearMonthPanel;

	public HomeMadeCalendar(MyDate date) {
	
		setBounds(200,200,350,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("Homemade Calendar");
		
		
		yearMonthPanel = new YearMonthPanel(this);
		add(yearMonthPanel,BorderLayout.NORTH);
		
		dayPanel = new DayPanel(this);
		add(dayPanel,BorderLayout.CENTER);
		
		confirmButton = new JButton();
		confirmButton.setPreferredSize(new Dimension(0,20));
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				date.setDate(getYear(), getMonth(), getDay(), getHour(), getMinute());
				close();
			}
		});
		add(confirmButton,BorderLayout.SOUTH);
		
		setCurrentDate();
		
		refresh();
		setVisible(true);
	}
	
	
	private void setCurrentDate() {
		yearMonthPanel.setCurrentDate();
		dayPanel.setCurrentDate();
		
	}
	
	public void refresh() {
		yearMonthPanel.refresh();
		dayPanel.refresh();
		refreshConfirmButton();
	}
	
	public void refreshConfirmButton() {
		if(dayPanel.timeIsValid()) {
			confirmButton.setEnabled(true);
			confirmButton.setText(String.format("%02d/%02d/%04d %02d:%02d",getDay(),getMonth(),getYear(),getHour(),getMinute()));
		}
		else {
			if(dayPanel.minuteIsValid()) {
				confirmButton.setText(String.format("%02d/%02d/%04d --:%02d",getDay(),getMonth(),getYear(),getMinute()));
			}
			else if(dayPanel.hourIsValid()) {
				confirmButton.setText(String.format("%02d/%02d/%04d %02d:--",getDay(),getMonth(),getYear(),getHour()));
			}
			else {
				confirmButton.setText(String.format("%02d/%02d/%04d --:--",getDay(),getMonth(),getYear()));
				
			}
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
		return dayPanel.getHour();
	}
	
	public int getMinute() {
		return dayPanel.getMinute();
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
