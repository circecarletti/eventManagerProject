package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.EntertainmentEventType;
import model.ListEvent;
import model.MyDate;
import model.Opera;
import model.RockConcert;
import model.Theatre;
public class MainWindows extends JFrame {

	private static final long serialVersionUID = -701041987776898855L;

	private JPanel contentPane;

	
	private boolean selectedTab;
	
	private JPanel centerPanel;
	private JPanel northPanel;
	private JPanel paneSearchTab;
	private JPanel paneAddTab;
	
	private MyFilter myFilter;
	public GlobalPanel globalPanel;
	
	private JComboBox<String> cbChoiceAdding;
	private AddEventPanel paneAddEvent;

	public ListEvent rockEvents;
	public ListEvent operaEvents;
	public ListEvent theatreEvents;

	
	
	public MainWindows() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 2));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));
		


		rockEvents = new ListEvent(EntertainmentEventType.ROCK);
		operaEvents = new ListEvent(EntertainmentEventType.OPERA);
		theatreEvents = new ListEvent(EntertainmentEventType.THEATRE);
		//////////EXEMPLES//////////////////////////////////////////////////////////
		rockEvents.addEvent(new RockConcert(new MyDate(2022, 6, 25, 22, 0), "Rock It", 100, "JB", "Alejandro"));
		rockEvents.addEvent(new RockConcert(new MyDate(2022, 8, 10, 21, 30), "Rock It", 100, "Fred", "Jules"));
		rockEvents.addEvent(new RockConcert(new MyDate(2022, 10, 5, 20,45), "Rock It", 100, "Axel", "Moris"));
		operaEvents.addEvent(new Opera(new MyDate(2022, 11, 30, 18, 30), 150, "neo-classique", "Simphony"));
		operaEvents.addEvent(new Opera(new MyDate(2022, 12, 0, 18, 30), 150, "neo-classique", "Simphony"));
		operaEvents.addEvent(new Opera(new MyDate(2022, 5, 30, 19, 0), 150, "classique", "Mozart"));
		theatreEvents.addEvent(new Theatre(new MyDate(2023, 1, 12, 16, 0), 50, "Cyrano", "XIX century", "classique"));
		theatreEvents.addEvent(new Theatre(new MyDate(2023, 1, 12, 16, 0), 50, "Cyrano", "XIX century", "classique"));
		theatreEvents.addEvent(new Theatre(new MyDate(2023, 1, 14, 16, 0), 50, "Cyrano", "XIX century", "classique"));
		theatreEvents.addEvent(new Theatre(new MyDate(2023, 1, 16, 16, 0), 50, "Cyrano", "XIX century", "classique"));
		theatreEvents.addEvent(new Theatre(new MyDate(2022, 8, 10, 14, 30), 50, "Romeo and Juliet", "XIX century", "classique"));
		////////////////////////////////////////////////////////////////////////////
		
		
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 2));
		northPanel.setBackground(new Color(75,75,75));
		
		this.paneAddEvent = new AddEventPanel(rockEvents);
		
		
		paneSearchTab = new JPanel();
		paneSearchTab.setBackground(new Color(171, 246, 173));
		JLabel lblSearchTab = new JLabel("Search");
		paneSearchTab.add(lblSearchTab);
		paneSearchTab.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedTab = false;
				updatePanel();
				centerPanel.setBackground(new Color(171, 246, 173));
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		northPanel.add(paneSearchTab);
		
		paneAddTab = new JPanel();
		paneAddTab.setBackground(new Color(246, 229, 171));
		paneAddTab.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 2));
		JLabel lblAddTab = new JLabel("Add event");
		paneAddTab.add(lblAddTab);
		paneAddTab.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedTab = true;
				centerPanel.setBackground(new Color(246, 229, 171));
				updatePanel();
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		northPanel.add(paneAddTab);

		contentPane.add(northPanel, BorderLayout.NORTH);
		
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(10, 10));
		contentPane.add(centerPanel);
		
		myFilter = new MyFilter(this);
		globalPanel = new GlobalPanel(this);
		
		

		this.selectedTab = false; 
		contentPane.setBackground(new Color(171, 246, 173));
		centerPanel.setBackground(new Color(171, 246, 173));
		centerPanel.setBorder(BorderFactory.createLineBorder(new Color(171, 246, 173), 10));
		
		updateCenterPaneOnSearch();
		
	}
	
	private void updatePanel() {
		if (!this.selectedTab) {
			contentPane.setBackground(new Color(171, 246, 173));
			centerPanel.setBorder(BorderFactory.createLineBorder(new Color(171, 246, 173), 10));
			this.paneSearchTab.setBorder(BorderFactory.createLineBorder(new Color(171, 246, 173)));
			this.paneAddTab.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 2));
			updateCenterPaneOnSearch();
		}else {
			contentPane.setBackground(new Color(246, 229, 171));
			centerPanel.setBorder(BorderFactory.createLineBorder(new Color(246, 229, 171), 10));
			this.paneSearchTab.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 2));
			this.paneAddTab.setBorder(BorderFactory.createLineBorder(new Color(246, 229, 171)));
			updateCenterPaneOnAdd();
		}
		
	}
	
	private void updateCenterPaneOnAdd() {
		centerPanel.removeAll();
		String[] choices = {"rock", "opera", "theatre"};
		cbChoiceAdding = new JComboBox<String>(choices);
		cbChoiceAdding.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch ((String)cbChoiceAdding.getSelectedItem()) {
				case "rock":
					paneAddEvent.updatePanel(rockEvents);
					break;
				case "opera":
					paneAddEvent.updatePanel(operaEvents);
					break;
				case "theatre":
					paneAddEvent.updatePanel(theatreEvents);				
					break;

				default:
					break;
				}

			}
		});
		centerPanel.add(cbChoiceAdding, BorderLayout.NORTH);
		centerPanel.add(paneAddEvent, BorderLayout.CENTER);
		centerPanel.revalidate();
		centerPanel.repaint();
	}


	private void updateCenterPaneOnSearch() {
		centerPanel.removeAll();
		
		centerPanel.add(myFilter,BorderLayout.NORTH);
		globalPanel.updatePanelAll();
		centerPanel.add(globalPanel,BorderLayout.CENTER);
		
		centerPanel.revalidate();
		centerPanel.repaint();
	}

}
