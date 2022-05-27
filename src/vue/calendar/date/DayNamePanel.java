package vue.calendar.date;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vue.calendar.HomeMadeCalendar;

public class DayNamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private String dayNames[] = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
	
	HomeMadeCalendar calendar;
	DayNamePanel(HomeMadeCalendar calendar){
		this.calendar = calendar;
		setLayout(new GridLayout(1,7));
		
	}
	
	public void refresh() {
		
		removeAll();
		for(int i=0;i<7;i++) {
			JLabel myLabel = new JLabel(dayNames[i]);
			if((calendar.getDay() + calendar.getFirstMondayIndex()[calendar.getMonth() - 1] - 1) % 7 == i) {
				myLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
			}
			add(myLabel);
		}
		revalidate();
		repaint();
	}
}
