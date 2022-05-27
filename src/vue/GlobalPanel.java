package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import model.EntertainmentEvent;
import model.MyDate;
import vue.calendar.HomeMadeCalendar;

public class GlobalPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	
	MainWindows myWindow;
	JPanel panel;
	
	public GlobalPanel(MainWindows window) {
		setLayout(new BorderLayout(5, 5));
		setBackground(new Color(171, 246, 173));
		
		myWindow = window;
		TitledBorder titleList = new TitledBorder("Global event");
		setBorder(titleList);
		titleList.setTitleFont(new Font("Dialog", Font.PLAIN, 12));
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(0,1));
		add(panel,BorderLayout.NORTH);
		
		String events[] = {"All","Rock concert","Opera concert","Theatre representation","Date"};        
		JComboBox<String> choiceOfEvent = new JComboBox<String>(events);
		choiceOfEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				
				String choice = (String) choiceOfEvent.getSelectedItem();
				if (choice.equals("All")) {
					updatePanelAll();
				}else if(choice.equals("Rock concert")) {
					filterRock();
				}else if (choice.equals("Opera concert")) {
					filterOpera();
				}else if (choice.equals("Theatre representation")){
					filterTheatre();
				}else if (choice.equals("Date")){
					MyDate date = new MyDate();
					HomeMadeCalendar newCalendarRock = new HomeMadeCalendar(date);
					newCalendarRock.addWindowListener(new WindowListener() {
						@Override
						public void windowClosing(WindowEvent e) {
							filterDate(date);
						}
						@Override
						public void windowClosed(WindowEvent e) {
							filterDate(date);
						}
						@Override
						public void windowOpened(WindowEvent e) {}
						@Override
						public void windowIconified(WindowEvent e) {}
						@Override
						public void windowDeiconified(WindowEvent e) {}
						@Override
						public void windowActivated(WindowEvent e) {}
						@Override
						public void windowDeactivated(WindowEvent e) {}
					});
				}

			}
		});
		//add(choiceOfEvent, BorderLayout.NORTH);
		
		updatePanelAll();
	}
	
	public void updatePanelAll() {
		panel.removeAll();
		for (EntertainmentEvent concert : myWindow.rockEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print());
			panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
		}
		
		for (EntertainmentEvent concert : myWindow.operaEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print());
			panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
		}
		
		for (EntertainmentEvent concert : myWindow.theatreEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print());
			panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
		}
		panel.repaint();
	}
	
	public void filterRock() {
		panel.removeAll();
		for (EntertainmentEvent concert : myWindow.rockEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print(),SwingConstants.LEFT);
			panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
		}
		panel.repaint();
	}
	
	public void filterOpera() {
		panel.removeAll();
		for (EntertainmentEvent concert : myWindow.operaEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print(),SwingConstants.LEFT);
			panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
		}
		panel.repaint();
	}
	
	public void filterTheatre() {
		panel.removeAll();
		for (EntertainmentEvent concert : myWindow.theatreEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print(),SwingConstants.LEFT);
			panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
		}
		panel.repaint();
	}
	
	public void filterDate(MyDate date) {
		panel.removeAll();
		for (EntertainmentEvent concert : myWindow.rockEvents.eventList) {
			if(concert.date.dayIsEqual(date)) {
				JLabel lblCurrentConcert = new JLabel(concert.print());
				panel.add(lblCurrentConcert);
				lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
			}
				
		}
		
		for (EntertainmentEvent concert : myWindow.operaEvents.eventList) {
			if(concert.date.dayIsEqual(date)) {
				JLabel lblCurrentConcert = new JLabel(concert.print());
				panel.add(lblCurrentConcert);
				lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
			}
		}
		
		for (EntertainmentEvent concert : myWindow.theatreEvents.eventList) {
			if(concert.date.dayIsEqual(date)) {
				JLabel lblCurrentConcert = new JLabel(concert.print());
				panel.add(lblCurrentConcert);
				lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
			}
		}
		panel.repaint();
	}
	
}
