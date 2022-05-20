package vue;

import java.awt.BorderLayout;
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
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.EntertainmentEventType;
import model.MyDate;
import model.Opera;
import model.RockConcert;
import model.Theatre;
import vue.calendar.HomeMadeCalendar;

public class AddEventPanel extends JPanel {
	
	private TreeSet<RockConcert> rockConcerts;
	private TreeSet<Opera> operaConcerts;
	private TreeSet<Theatre> theatreRepresentations;
	private MyDate dateNewEvent;
	private InventoryPanel inventoryPanel;
	
	
	
	/**
	 * @param rockConcerts
	 * @param operaConcerts
	 * @param theatreRepresentations
	 * @param selectedEvent 
	 * @param inventoryPanel 
	 */
	public AddEventPanel(TreeSet<RockConcert> rockConcerts, TreeSet<Opera> operaConcerts,
			TreeSet<Theatre> theatreRepresentations, EntertainmentEventType selectedEvent, InventoryPanel inventoryPanel) {
		super();
		this.inventoryPanel = inventoryPanel;
		this.rockConcerts = rockConcerts;
		this.operaConcerts = operaConcerts;
		this.theatreRepresentations = theatreRepresentations;

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		TitledBorder titlePanel = new TitledBorder("adding event");
		this.setBorder(titlePanel);
		titlePanel.setTitleFont(new Font("Dialog", Font.PLAIN, 12));
		

		updatePanel(selectedEvent);

	}
	
	
	void updatePanel(EntertainmentEventType selectedEvent) {
		this.removeAll();
		switch (selectedEvent) {
		case ROCK:
			makeAddingRockFilling();
			break;
		case OPERA:
			makeAddingOperaFilling();
			break;
		case THEATRE:
			makeAddingTheatreFilling();
			break;

		default:
			break;
		}
		this.repaint();
	}

	
	private void addAllEventElements(JTextField txtFieldName, JTextField txtFieldCapacity) {
		
		
		JLabel lblName = new JLabel("Name : ");
		this.add(lblName);
		this.add(txtFieldName);
		
		JLabel lblCapacity = new JLabel("Capacity : ");
		this.add(lblCapacity);
		this.add(txtFieldCapacity);
		

		this.dateNewEvent = new MyDate();
		JLabel lblDate = new JLabel("Date : __/__/____   __:__");
		this.add(lblDate);		
		
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
		this.add(btnModifyDate);

	}
	///////////////////////////////////////////////////////////////////////////////
	//								THEATRE                                      //
	///////////////////////////////////////////////////////////////////////////////
	
	private void makeAddingTheatreFilling() {
		this.setLayout(new GridLayout(6, 2, 3, 5));
		
		JTextField txtFieldName = new JTextField();
		JTextField txtFieldCapacity =  new JTextField();
		addAllEventElements(txtFieldName, txtFieldCapacity);
		
		JLabel lblTheme = new JLabel("Theme : ");
		this.add(lblTheme);
		JTextField txtFieldTheme = new JTextField();
		this.add(txtFieldTheme);
		
		JLabel lblStyle = new JLabel("Style : ");
		this.add(lblStyle);
		JTextField txtFieldStyle = new JTextField();
		this.add(txtFieldStyle);
		
		
		
		JButton btnEnterInfo = new JButton("add event");
		btnEnterInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = txtFieldName.getText();
				int capacity = 0;
				try {
					capacity = Integer.parseInt(txtFieldCapacity.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "No valid value for the capacity", "Error", JOptionPane.ERROR_MESSAGE);
				}
				String style = txtFieldStyle.getText();
				String themeScene = txtFieldTheme.getText();
				if (capacity > 0 
						&& !(name.equals("") || (name.equals(" ")))){
					if (!addTheatreEvent(theatreRepresentations, dateNewEvent, themeScene, capacity, style, name)) {
						JOptionPane.showMessageDialog(null, (name + " are already playing at this date!"), "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						txtFieldName.setText("");
						txtFieldCapacity.setText("");
						txtFieldTheme.setText("");
						txtFieldStyle.setText("");
					}
 				} else {
 					JOptionPane.showMessageDialog(null, "Capacity must be more than 0. \nThe name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
 				}
			}

		});
		this.add(btnEnterInfo);
	}
	
	
	private boolean addTheatreEvent(TreeSet<Theatre> theatreRepresentations, MyDate date, String themeScene, int capacity, String style, String name) {
		Theatre representation = new Theatre(themeScene, style, date, capacity, name);
		if (!representation.isIn(theatreRepresentations)) {
			if(theatreRepresentations.add(representation)) 
			{
				inventoryPanel.updatePanel(null, null, theatreRepresentations, EntertainmentEventType.THEATRE);
				return true;
			} else {
				System.out.println("add failure.");
				return false;
			}
		} else {
			System.out.println("isIn failure.");
			return false;
		}
	}

	///////////////////////////////////////////////////////////////////////////////
	//								ROCK                                         //
	///////////////////////////////////////////////////////////////////////////////

	private void makeAddingRockFilling() {
		this.setLayout(new GridLayout(6, 2, 3, 5));

		JTextField txtFieldName = new JTextField();
		JTextField txtFieldCapacity =  new JTextField();
		addAllEventElements(txtFieldName, txtFieldCapacity);
		
		JLabel lblDrummer = new JLabel("Drummer : ");
		this.add(lblDrummer);
		JTextField txtFieldDrummer = new JTextField();
		this.add(txtFieldDrummer);
		
		JLabel lblGuitarist = new JLabel("Guitarist : ");
		this.add(lblGuitarist);
		JTextField txtFieldGuitarist = new JTextField();
		this.add(txtFieldGuitarist);
				
		
		
		JButton btnEnterInfo = new JButton("add event");
		btnEnterInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = txtFieldName.getText();
				int capacity = 0;
				try {
					capacity = Integer.parseInt(txtFieldCapacity.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "No valid value for the capacity", "Error", JOptionPane.ERROR_MESSAGE);
				}
				String drummer = txtFieldDrummer.getText();
				String guitarist = txtFieldGuitarist.getText();
				if (capacity > 0 
						&& !(name.equals("") || (name.equals(" ")))){
					if (!addRockEvent(rockConcerts, dateNewEvent, capacity, drummer, guitarist, name)) {
						JOptionPane.showMessageDialog(null, (name + " are already playing at this date!"), "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						txtFieldName.setText("");
						txtFieldCapacity.setText("");
						txtFieldDrummer.setText("");
						txtFieldGuitarist.setText("");
					}
 				} else {
 					JOptionPane.showMessageDialog(null, "Capacity must be more than 0. \nThe name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
 				}
			}
		});
		this.add(btnEnterInfo);		
	}

	
	
	private boolean addRockEvent(TreeSet<RockConcert> rockConcerts, MyDate date, int capacity, String drummer, String guitarist, String name) {
		RockConcert newConcert = new RockConcert(date, capacity, drummer, guitarist, name);
		if (!newConcert.isIn(rockConcerts)) {
			if(this.rockConcerts.add(newConcert)) 
			{
				inventoryPanel.updatePanel(rockConcerts, null, null, EntertainmentEventType.ROCK);
				return true;
			} else {
				System.out.println("add failure.");
				return false;
			}
		} else {
			System.out.println("isIn failure.");
			return false;
		}
	}


	///////////////////////////////////////////////////////////////////////////////
	//								OPERA                                        //
	///////////////////////////////////////////////////////////////////////////////
	private void makeAddingOperaFilling() {
		this.setLayout(new GridLayout(6, 2, 3, 5));

		JTextField txtFieldName = new JTextField();
		JTextField txtFieldCapacity =  new JTextField();
		addAllEventElements(txtFieldName, txtFieldCapacity);
		
		JLabel lblStyle = new JLabel("Style : ");
		this.add(lblStyle);
		JTextField txtFieldStyle = new JTextField();
		this.add(txtFieldStyle);
		
		
		JButton btnEnterInfo = new JButton("add event");
		btnEnterInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtFieldName.getText();
				int capacity = 0;
				try {
					capacity = Integer.parseInt(txtFieldCapacity.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "No valid value for the capacity", "Error", JOptionPane.ERROR_MESSAGE);
				}
				String style = txtFieldStyle.getText();
				if (capacity > 0 
						&& !(name.equals("") || (name.equals(" ")))){
					if (!addOperaEvent(operaConcerts, dateNewEvent, capacity, style, name)) {
						JOptionPane.showMessageDialog(null, (name + " are already playing at this date!"), "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						txtFieldCapacity.setText("");
						txtFieldStyle.setText("");
						txtFieldName.setText("");
					}
 				} else {
 					JOptionPane.showMessageDialog(null, "Capacity must be more than 0. \nThe name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
 				}				
			}
		});
		this.add(btnEnterInfo, BorderLayout.CENTER);		
	}
	
	
	private boolean addOperaEvent(TreeSet<Opera> operaConcerts, MyDate date, int capacity, String style, String name) {
		Opera newConcert = new Opera(date, capacity, style, name);
		if (!newConcert.isIn(operaConcerts)) {
			if(operaConcerts.add(newConcert)) 
			{
				inventoryPanel.updatePanel(null, operaConcerts, null, EntertainmentEventType.OPERA);
				return true;
			} else {
				System.out.println("add failure.");
				return false;
			}
		} else {
			System.out.println("isIn failure.");
			return false;
		}
	}

}
