package vue.calendar.date;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vue.calendar.HomeMadeCalendar;
public class MonthPanel extends JPanel {

	private static final long serialVersionUID = 68705179137355688L;

	
	private final String monthNames[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};	
	
	private JButton previousButton = new JButton("<");
	private JButton nextButton = new JButton(">");
	private static JLabel monthName = new JLabel("",SwingConstants.CENTER);
	private int month = 1;
	
	
	MonthPanel(HomeMadeCalendar calendar){
		setLayout(new BorderLayout());
		setBackground(Color.white);
		add(monthName,BorderLayout.CENTER);
		add(previousButton,BorderLayout.WEST);
		add(nextButton,BorderLayout.EAST);
		
		previousButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(month>1) {
					month--;
				}
				else {
					month = 12;
					calendar.decrementYear();
				}
				calendar.refresh();
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(month<12) {
					month++;
				}
				else {
					month = 1;
					calendar.incrementYear();
				}
				calendar.refresh();
			}
		});
	}
	
	public int getMonth() {
		return month;
	}
	
	public void refresh() {
		monthName.setText(getMonthName(month));
	}
	
	public String getMonthName(int monthNumber) {
		return monthNames[monthNumber-1];
	}

	public void setCurrentMonth() {
		month = LocalDateTime.now().getMonthValue();
	}
}
