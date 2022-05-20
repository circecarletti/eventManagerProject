package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import model.EntertainmentEventType;
import model.MyDate;
import model.Opera;
import model.RockConcert;
import model.Theatre;

public class MainWindows extends JFrame {

	private JPanel contentPane;

	private InventoryPanel inventoryPanel;
	
	private AddEventPanel addEventPanel;
	
	private JPanel centerPanel;
	
	private EntertainmentEventType selectedEvent;
	
	
	public MainWindows(TreeSet<RockConcert> rockConcerts, TreeSet<Opera> operaConcerts, TreeSet<Theatre> theatreRepresentations) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 10));
		
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 2, 5, 10));
		contentPane.add(centerPanel);
		
	
		this.selectedEvent = EntertainmentEventType.ROCK;
		makeInventoryPanel(rockConcerts, operaConcerts, theatreRepresentations);
		makeAddEventPanel(rockConcerts, operaConcerts, theatreRepresentations);
		

		JPanel northPanel = new JPanel();
		
		JLabel lblChoiceEvent = new JLabel("Choose one type of event : ");
		northPanel.add(lblChoiceEvent);
		
		String events[] = {"rock concert","opera concert","theatre representation"};        
		JComboBox<String> choiceOfEvent = new JComboBox<String>(events);
		choiceOfEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String choice = (String) choiceOfEvent.getSelectedItem();
				if (choice.equals("rock concert")) {
					selectedEvent = EntertainmentEventType.ROCK;
				}else if (choice.equals("opera concert")) {
					selectedEvent = EntertainmentEventType.OPERA;
				}else if (choice.equals("theatre representation")){
					selectedEvent = EntertainmentEventType.THEATRE;
				}
				updateCenterPanel(rockConcerts, operaConcerts, theatreRepresentations);
			}
		});
		northPanel.add(choiceOfEvent);
		contentPane.add(northPanel, BorderLayout.NORTH);
	}

	private void updateCenterPanel(TreeSet<RockConcert> rockConcerts, TreeSet<Opera> operaConcerts, TreeSet<Theatre> theatreRepresentations) {
		inventoryPanel.updatePanel(rockConcerts, operaConcerts, theatreRepresentations, selectedEvent);
		addEventPanel.updatePanel(selectedEvent);
		inventoryPanel.revalidate();
		inventoryPanel.repaint();
	}

	private void makeInventoryPanel(TreeSet<RockConcert> rockConcerts, TreeSet<Opera> operaConcerts,
			TreeSet<Theatre> theatreRepresentations) {
		inventoryPanel = new InventoryPanel(rockConcerts, operaConcerts, theatreRepresentations, this.selectedEvent);
		centerPanel.add(inventoryPanel);
	}


	private void makeAddEventPanel(TreeSet<RockConcert> rockConcerts, TreeSet<Opera> operaConcerts, 
			TreeSet<Theatre> theatreRepresentations) {
		this.addEventPanel = new AddEventPanel(rockConcerts,  operaConcerts, theatreRepresentations, this.selectedEvent, inventoryPanel);
		centerPanel.add(addEventPanel, BorderLayout.CENTER);
	}

}
