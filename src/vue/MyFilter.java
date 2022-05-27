package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.EntertainmentEvent;
import vue.calendar.HomeMadeCalendar;
import vue.calendar.MyDay;
import vue.calendar.time.NumberTextField;

public class MyFilter extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	MyDay myDay;

	private static final Color COLOR = new Color(48, 209, 150);
	
	JComboBox<String> filter;
	
	JTextField nameTextField;
	
	JLabel dateLabel2;
	
	NumberTextField IDTextField;
	
	MainWindows myWindow;
	
	
	public MyFilter(MainWindows window) {
		
		int jtextLenght = 60;
		
		myWindow = window;
		
		setBackground(COLOR);
		setLayout(new GridLayout(3,2,0,5));
		
		myDay = new MyDay();
		

		setBorder(BorderFactory.createLineBorder(COLOR, 5));
	
		
		JPanel subPanel1 = new JPanel();
		subPanel1.setBackground(COLOR);
		subPanel1.setLayout(new BorderLayout());
		
		JLabel typeLabel = new JLabel("Type : ", SwingConstants.RIGHT);
		typeLabel.setPreferredSize(new Dimension(jtextLenght, 14));
		subPanel1.add(typeLabel,BorderLayout.WEST);
		
		String filters[] = {"All","rock concert","opera concert","theatre"};        
		filter = new JComboBox<String>(filters);
		filter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (filter.getSelectedItem().equals("rock concert")) {
				}else if (filter.getSelectedItem().equals("opera concert")) {

				}else if (filter.getSelectedItem().equals("theatre representation")){

				}
				
			}
		});
		subPanel1.add(filter,BorderLayout.CENTER);
		
		add(subPanel1);
		
		
		JPanel subPanel2 = new JPanel();
		subPanel2.setBackground(COLOR);
		
		JLabel nameLabel = new JLabel("Name : ",SwingConstants.RIGHT);
		nameLabel.setPreferredSize(new Dimension(jtextLenght, 14));
		subPanel2.add(nameLabel, BorderLayout.WEST);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		subPanel2.add(nameTextField, BorderLayout.CENTER);
		
		add(subPanel2);
		
		
		JPanel subPanel3 = new JPanel();
		subPanel3.setBackground(COLOR);
		
		JLabel dateLabel = new JLabel("Date : ",SwingConstants.RIGHT);
		dateLabel.setPreferredSize(new Dimension(jtextLenght, 14));
		subPanel3.add(dateLabel, BorderLayout.WEST);
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new BorderLayout());
		
		dateLabel2 = new JLabel("__/__/____ ",SwingConstants.CENTER);
		datePanel.add(dateLabel2,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		
		JButton dateButton = new JButton("Modify");
		dateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HomeMadeCalendar newCalendar = new HomeMadeCalendar(myDay);
				newCalendar.addWindowListener(new WindowListener() {
					@Override
					public void windowClosing(WindowEvent e) {
						dateLabel2.setText(myDay.toString() + " ");
					}
					@Override
					public void windowClosed(WindowEvent e) {
						dateLabel2.setText(myDay.toString() + " ");
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
		});
		
		JButton dateButtonClear = new JButton("Clear");
		dateButtonClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dateLabel2.setText("__/__/____ ");
				myDay = new MyDay();
			}
		});
		buttonPanel.add(dateButton);
		buttonPanel.add(dateButtonClear);
		
		datePanel.add(buttonPanel,BorderLayout.EAST);
		datePanel.setBackground(COLOR);
		
		subPanel3.add(datePanel, BorderLayout.CENTER);
		
		add(subPanel3);
		
		
		JPanel subPanel4 = new JPanel();
		subPanel4.setBackground(COLOR);
		
		JLabel IDLabel = new JLabel("ID : ",SwingConstants.RIGHT);
		IDLabel.setPreferredSize(new Dimension(jtextLenght, 14));
		subPanel4.add(IDLabel, BorderLayout.WEST);
		
		IDTextField = new NumberTextField();
		IDTextField.setColumns(10);
		subPanel4.add(IDTextField, BorderLayout.CENTER);
		
		add(subPanel4);
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(COLOR);
		add(emptyPanel);
		
		JButton researchButton = new JButton("Search");
		researchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				research();
			}
		});
		
		add(researchButton);
		
	}
	
	public void research() {
		TreeSet<EntertainmentEvent> eventList = new TreeSet<EntertainmentEvent>();
		
		String choice = (String) filter.getSelectedItem();
		if(choice.equals("All")) {
			for (EntertainmentEvent concert : myWindow.rockEvents.eventList) {
				eventList.add(concert);
			}
			for (EntertainmentEvent concert : myWindow.operaEvents.eventList) {
				eventList.add(concert);
			}
			for (EntertainmentEvent concert : myWindow.theatreEvents.eventList) {
				eventList.add(concert);
			}
		}else if (choice.equals("rock concert")) {
			for (EntertainmentEvent concert : myWindow.rockEvents.eventList) {
				eventList.add(concert);
			}
		}else if (choice.equals("opera concert")) {
			for (EntertainmentEvent concert : myWindow.operaEvents.eventList) {
				eventList.add(concert);
			}
		}else if (choice.equals("theatre")){
			for (EntertainmentEvent concert : myWindow.theatreEvents.eventList) {
				eventList.add(concert);
			}
		}
		
		if(!nameTextField.getText().equals("")) {
			TreeSet<EntertainmentEvent> eventList2 = new TreeSet<EntertainmentEvent>();
			for (EntertainmentEvent concert : eventList) {
				if(concert.name.equals(nameTextField.getText())) {
					eventList2.add(concert);
				}
				eventList = eventList2;
			}
		}
		
		if(!IDTextField.getText().equals("")) {
			TreeSet<EntertainmentEvent> eventList2 = new TreeSet<EntertainmentEvent>();
			for (EntertainmentEvent concert : eventList) {
				if(concert.eventID == Integer.parseInt(IDTextField.getText())) {
					eventList2.add(concert);
				}
				eventList = eventList2;
			}
		}
		
		if(!myDay.dayIsEqual(new MyDay())) {
			TreeSet<EntertainmentEvent> eventList2 = new TreeSet<EntertainmentEvent>();
			for (EntertainmentEvent concert : eventList) {
				if(myDay.dayIsEqual(concert.date)) {
					eventList2.add(concert);
				}
			}
			eventList = eventList2;
		}
		updateEvent(eventList);
	}
	
	private void updateEvent(TreeSet<EntertainmentEvent> eventList) {
		myWindow.globalPanel.panel.removeAll();
		for (EntertainmentEvent concert : eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print());
			myWindow.globalPanel.panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 14));
		}
		myWindow.globalPanel.panel.repaint();
	}

	public void updatePanelAll() {
		myWindow.globalPanel.panel.removeAll();
		for (EntertainmentEvent concert : myWindow.rockEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print());
			myWindow.globalPanel.panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 14));
		}
		
		for (EntertainmentEvent concert : myWindow.operaEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print());
			myWindow.globalPanel.panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 14));
		}
		
		for (EntertainmentEvent concert : myWindow.theatreEvents.eventList) {
			JLabel lblCurrentConcert = new JLabel(concert.print());
			myWindow.globalPanel.panel.add(lblCurrentConcert);
			lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 14));
		}
		myWindow.globalPanel.panel.repaint();
	}
	

}
