package vue.calendar.Date;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JPanel;

import vue.calendar.HomeMadeCalendar;

public class DayNumberPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private int day = 1;
	
	private HomeMadeCalendar calendar;
	
	public DayNumberPanel(HomeMadeCalendar calendar) {
		setLayout(new GridLayout(6,7));
		this.calendar = calendar;
	}
	
	public void refresh() {
		if(day>calendar.getNumberOfDay()[calendar.getMonth()-1])day = calendar.getNumberOfDay()[calendar.getMonth()-1];
		removeAll();
		int i;
		int monthIndex = calendar.getMonth()-1;
		for(i=0;i<calendar.getNumberOfDay()[monthIndex]+calendar.getFirstMondayIndex()[monthIndex];i++) {
			JButton myButton = new JButton();
			if(day == i+1-calendar.getFirstMondayIndex()[monthIndex]) {
				myButton.setEnabled(false);
				myButton.setBackground(Color.white);
			}
			//if(i % 7 % 2 == 0)myButton.setBorder(BorderFactory.createLineBorder(Color.black));
			if(i>calendar.getFirstMondayIndex()[monthIndex]-1) {
				myButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						day = Integer.parseInt(myButton.getText());
						myButton.setEnabled(false);
						calendar.refresh();
					}
				});
				myButton.setText(""+(i+1-calendar.getFirstMondayIndex()[monthIndex]));
				add(myButton);
			}
			else {
				JPanel myPanel = new JPanel();
				//if(i % 7 % 2 == 0)myPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				add(myPanel);	
			}
		}
		for(int j=i;j<42;j++) {
			JPanel myPanel = new JPanel();
			//if(j % 7 % 2 == 0)myPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			add(myPanel);
		}
		revalidate();
		repaint();
	}
	
	public int getDay() {
		return day;
	}

	public void setCurrentDay() {
		day = LocalDateTime.now().getDayOfMonth();
		
	}
}
