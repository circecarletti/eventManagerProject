package vue.calendar.date;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vue.calendar.HomeMadeCalendar;
public class YearPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JLabel yearLabel = new JLabel("",SwingConstants.CENTER);
	
	private JButton previousButton = new JButton("<<");
	private JButton nextButton = new JButton(">>");
	
	private HomeMadeCalendar calendar;
	
	private int year;
	
	YearPanel(HomeMadeCalendar calendar){
		setLayout(new BorderLayout());
		
		this.calendar = calendar;
		
		add(yearLabel,BorderLayout.CENTER);
		add(previousButton,BorderLayout.WEST);
		add(nextButton,BorderLayout.EAST);
		
		previousButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				decrement();
				calendar.refresh();
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				increment();
				calendar.refresh();
			}
		});
		
		refresh();
	}
	public void increment() {
		year++;
	}
	public void decrement() {
		year--;
	}
	
	public int getYear() {
		return year;
	}
	
	public boolean isBisextileYear(int year) {
		return(year%4 == 0 && (year % 100 != 0 || year % 400 == 0));
	}
	
	public void refresh() {
		yearLabel.setText("" + year);
		if(isBisextileYear(year)) {
			calendar.getNumberOfDay()[1] = 29;
			int refYearDiff = year - calendar.REF_YEAR;
			for(int i=0;i<12;i++) {
				int newfirstMondayIndex = (calendar.firstMondayIndex2024[i] - refYearDiff / 4 * 2) % 7;
				while(newfirstMondayIndex<0)newfirstMondayIndex += 7;
				calendar.getFirstMondayIndex()[i] = newfirstMondayIndex;
			}
		}
		else {
			calendar.getNumberOfDay()[1] = 28;
			int lowerBisextileYear = year;
			while(!isBisextileYear(lowerBisextileYear)) {
				lowerBisextileYear--;
			}
			
			int refYearDiff = lowerBisextileYear - calendar.REF_YEAR;
			int yearDiff = year - lowerBisextileYear;
			for(int i=0;i<12;i++) {
				int newfirstMondayIndex;
				if(i<2) {
					newfirstMondayIndex = (calendar.firstMondayIndex2024[i] - refYearDiff / 4 * 2 + yearDiff + 1) % 7;
				}
				else {
					newfirstMondayIndex = (calendar.firstMondayIndex2024[i] - refYearDiff / 4 * 2 + yearDiff) % 7;
				}
				while(newfirstMondayIndex<0)newfirstMondayIndex += 7;
				calendar.getFirstMondayIndex()[i] = newfirstMondayIndex;
			}
		}
	}
	public void setCurrentYear() {
		year = LocalDateTime.now().getYear();
		
	}
	public void setYear(int year) {
		this.year = year;
		
	}
}
