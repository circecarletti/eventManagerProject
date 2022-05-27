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
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createLineBorder(new Color(10, 10, 10), 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		


		rockEvents = new ListEvent(EntertainmentEventType.ROCK);
		operaEvents = new ListEvent(EntertainmentEventType.OPERA);
		theatreEvents = new ListEvent(EntertainmentEventType.THEATRE);
		
		
		
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 2));
		
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
		paneAddTab.setBorder(BorderFactory.createLineBorder(Color.black));
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
		centerPanel.setBackground(new Color(171, 246, 173));
		
		updateCenterPaneOnSearch();
		
	}
	
	private void updatePanel() {
		if (!this.selectedTab) {
			this.paneSearchTab.setBorder(BorderFactory.createLineBorder(new Color(171, 246, 173)));
			this.paneAddTab.setBorder(BorderFactory.createLineBorder(Color.black));
			updateCenterPaneOnSearch();
		}else {
			this.paneSearchTab.setBorder(BorderFactory.createLineBorder(Color.black));
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
