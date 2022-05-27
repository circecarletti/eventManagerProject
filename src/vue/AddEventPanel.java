package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.EntertainmentEventType;
import model.ListEvent;
import model.MyDate;
import model.Opera;
import model.RockConcert;
import model.Theatre;
import vue.calendar.HomeMadeCalendar;

public class AddEventPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	protected ListEvent events;

	ArrayList<Component> commonVisualElements;
	ArrayList<Component> specificVisualElements;


	protected MyDate dateNewEvent;
	
	public AddEventPanel(ListEvent events) {
		this.events = events;
		TitledBorder titlePanel = new TitledBorder("adding event");
		setBorder(titlePanel);
		titlePanel.setTitleFont(new Font("Dialog", Font.PLAIN, 12));
		this.setBackground(new Color(246, 229, 171));

		this.commonVisualElements = new ArrayList<Component>();
		addAllEventElements();
		for (Component component : commonVisualElements) {
			this.add(component);
		}
		this.specificVisualElements = new ArrayList<Component>();
		updatePanel(events);
	}

	public void updatePanel(ListEvent events) {
		for (Component component : specificVisualElements) {
			this.remove(component);
		}
		this.specificVisualElements.clear();
		
		this.addSpecificElements(events.eventType);
		
		for (Component component : specificVisualElements) {
			this.add(component);
		}
		revalidate();
		repaint();
	}

	public void addAllEventElements() {		
		JLabel lblName = new JLabel("Name : ");
		commonVisualElements.add(lblName);
		JTextField txtFieldName = new JTextField();
		commonVisualElements.add(txtFieldName);
		
		JLabel lblCapacity = new JLabel("Capacity : ");
		commonVisualElements.add(lblCapacity);
		JTextField txtFieldCapacity= new JTextField();
		commonVisualElements.add(txtFieldCapacity);
		

		this.dateNewEvent = new MyDate();
		JLabel lblDate = new JLabel("Date : __/__/____   __:__");
		commonVisualElements.add(lblDate);		
		
		JButton btnModifyDate = new JButton("modify");
		btnModifyDate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				HomeMadeCalendar newCalendarRock = new HomeMadeCalendar(dateNewEvent);
				newCalendarRock.addWindowListener(new WindowListener() {
					@Override
					public void windowClosing(WindowEvent e) {
						lblDate.setText("Date : " + dateNewEvent.toString());
					}
					@Override
					public void windowClosed(WindowEvent e) {
						lblDate.setText("Date : " + dateNewEvent.toString());
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
		commonVisualElements.add(btnModifyDate);
	}
	

	public void addSpecificElements(EntertainmentEventType eventType) {
		switch (eventType) {
		case ROCK:
			addSpecificElementsRock(events);
			break;
		case OPERA:
			addSpecificElementsOpera(events);
			break;
		case THEATRE:
			addSpecificElementsTheatre(events);
			break;

		default:
			break;
		}
	}

	private void addSpecificElementsRock(ListEvent events2) {
		this.setLayout(new GridLayout(6, 2, 3, 15));
		
		JLabel lblDrummer = new JLabel("Drummer : ");
		specificVisualElements.add(lblDrummer);
		JTextField txtFieldDrummer = new JTextField();
		specificVisualElements.add(txtFieldDrummer);
		
		JLabel lblGuitarist = new JLabel("Guitarist : ");
		specificVisualElements.add(lblGuitarist);
		JTextField txtFieldGuitarist = new JTextField();
		specificVisualElements.add(txtFieldGuitarist);
		
		
		JButton btnEnterInfo = new JButton("add event");
		btnEnterInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = ((JTextField)commonVisualElements.get(1)).getText();
				int capacity = 0;
				try {
					capacity = Integer.parseInt(((JTextField)commonVisualElements.get(3)).getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "No valid value for the capacity", "Error", JOptionPane.ERROR_MESSAGE);
				}
				String drummer = txtFieldDrummer.getText();
				String guitarist = txtFieldGuitarist.getText();
				if (capacity > 0 && !(name.equals("") || (name.equals(" ")))){
					RockConcert newConcert = new RockConcert(dateNewEvent, capacity, drummer, guitarist, name);
					if (!events.addEvent(newConcert)) {
						JOptionPane.showMessageDialog(null, (name + " are already playing at this date!"), "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						((JTextField)commonVisualElements.get(1)).setText("");
						((JTextField)commonVisualElements.get(3)).setText("");
						txtFieldDrummer.setText("");
						txtFieldGuitarist.setText("");
					}
 				} else {
 					JOptionPane.showMessageDialog(null, "Capacity must be more than 0. \nThe name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
 				}				
			}
		});
		specificVisualElements.add(btnEnterInfo);
	}
		
	
	private void addSpecificElementsOpera(ListEvent events2) {
		this.setLayout(new GridLayout(6, 2, 3, 15));
		
		
		JLabel lblStyle = new JLabel("Style : ");
		specificVisualElements.add(lblStyle);
		JTextField txtFieldStyle = new JTextField();
		specificVisualElements.add(txtFieldStyle);
		
		
		JButton btnEnterInfo = new JButton("add event");
		btnEnterInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = ((JTextField)commonVisualElements.get(1)).getText();
				int capacity = 0;
				try {
					capacity = Integer.parseInt(((JTextField)commonVisualElements.get(3)).getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "No valid value for the capacity", "Error", JOptionPane.ERROR_MESSAGE);
				}
				String style = txtFieldStyle.getText();
				if (capacity > 0 && !(name.equals("") || (name.equals(" ")))){
					Opera newConcert = new Opera(dateNewEvent, capacity, style, name);
					if (!events.addEvent(newConcert)) {
						JOptionPane.showMessageDialog(null, (name + " are already playing at this date!"), "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						((JTextField)commonVisualElements.get(1)).setText("");
						txtFieldStyle.setText("");
						((JTextField)commonVisualElements.get(3)).setText("");
					}
 				} else {
 					JOptionPane.showMessageDialog(null, "Capacity must be more than 0. \nThe name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
 				}				
			}
		});
		specificVisualElements.add(btnEnterInfo);		
	}

	private void addSpecificElementsTheatre(ListEvent events2) {
		this.setLayout(new GridLayout(6, 2, 3, 15));
		
		MyDate dateNewEvent = new MyDate();
		
		
		JLabel lblTheme = new JLabel("Theme : ");
		specificVisualElements.add(lblTheme);
		JTextField txtFieldTheme = new JTextField();
		specificVisualElements.add(txtFieldTheme);
		
		JLabel lblStyle = new JLabel("Style : ");
		specificVisualElements.add(lblStyle);
		JTextField txtFieldStyle = new JTextField();
		specificVisualElements.add(txtFieldStyle);
		
		
		JButton btnEnterInfo = new JButton("add event");
		btnEnterInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = ((JTextField)commonVisualElements.get(1)).getText();
				int capacity = 0;
				try {
					capacity = Integer.parseInt(((JTextField)commonVisualElements.get(3)).getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "No valid value for the capacity", "Error", JOptionPane.ERROR_MESSAGE);
				}
				String style = txtFieldStyle.getText();
				String themeScene = txtFieldTheme.getText();
				if (capacity > 0 && !(name.equals("") || (name.equals(" ")))){
					Theatre newConcert = new Theatre(dateNewEvent, capacity, name, themeScene, style);
					if (!events.addEvent(newConcert)) {
						JOptionPane.showMessageDialog(null, (name + " are already playing at this date!"), "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						((JTextField)commonVisualElements.get(1)).setText("");
						((JTextField)commonVisualElements.get(3)).setText("");
						txtFieldTheme.setText("");
						txtFieldStyle.setText("");
					}
 				} else {
 					JOptionPane.showMessageDialog(null, "Capacity must be more than 0. \nThe name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
 				}				
			}
		});
		specificVisualElements.add(btnEnterInfo);
	}		

}
