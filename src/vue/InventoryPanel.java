package vue;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.TreeSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.EntertainmentEventType;
import model.Opera;
import model.RockConcert;
import model.Theatre;

public class InventoryPanel extends JPanel {
	
	private TitledBorder titleList;
	/**
	 * @param rockConcerts
	 * @param operaConcerts
	 * @param theatreRepresentations
	 * @param selectedEvent
	 */
	public InventoryPanel(TreeSet<RockConcert> rockConcerts, TreeSet<Opera> operaConcerts,
			TreeSet<Theatre> theatreRepresentations, EntertainmentEventType selectedEvent) {
		super();
		
		switch (selectedEvent) {
		case ROCK:
			titleList = new TitledBorder("planned rock concert");
			break;
		case OPERA:
			titleList = new TitledBorder("planned opera concert");
			break;
		case THEATRE:
			titleList = new TitledBorder("planned theatre representation");
			break;

		default:
			titleList = new TitledBorder("unknown event");
			break;
		}
		this.setBorder(titleList);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		titleList.setTitleFont(new Font("Dialog", Font.PLAIN, 12));

		updateList(rockConcerts, operaConcerts, theatreRepresentations, selectedEvent);
	}
	
	public void updatePanel(TreeSet<RockConcert> rockConcerts, TreeSet<Opera> operaConcerts,
			TreeSet<Theatre> theatreRepresentations, EntertainmentEventType selectedEvent) {
		this.removeAll();
		
		switch (selectedEvent) {
		case ROCK:
			titleList = new TitledBorder("planned rock concert");
			break;
		case OPERA:
			titleList = new TitledBorder("planned opera concert");
			break;
		case THEATRE:
			titleList = new TitledBorder("planned theatre representation");
			break;

		default:
			titleList = new TitledBorder("unknown event");
			break;
		}
		this.setBorder(titleList);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		titleList.setTitleFont(new Font("Dialog", Font.PLAIN, 12));
		
		updateList(rockConcerts, operaConcerts, theatreRepresentations, selectedEvent);
		this.repaint();
	}
	
	private void updateList(TreeSet<RockConcert> rockConcerts, TreeSet<Opera> operaConcerts,
			TreeSet<Theatre> theatreRepresentations, EntertainmentEventType selectedEvent) {
		switch (selectedEvent) {
		case ROCK:
			for (RockConcert concert : rockConcerts) {
				JLabel lblCurrentConcert = new JLabel("- " + concert.name + ", " + concert.date.toString());
				this.add(lblCurrentConcert);
				lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
			}
			break;
		case OPERA:
			for (Opera concert : operaConcerts) {
				JLabel lblCurrentConcert = new JLabel("- " + concert.name + ", " + concert.date.toString());
				this.add(lblCurrentConcert);
				lblCurrentConcert.setFont(new Font("Dialog", Font.ITALIC, 10));
			}
			break;
		case THEATRE:
			for (Theatre representation : theatreRepresentations) {
				JLabel lblCurrentRepresentation = new JLabel("- " + representation.name + ", " + representation.date.toString());
				this.add(lblCurrentRepresentation);
				lblCurrentRepresentation.setFont(new Font("Dialog", Font.ITALIC, 10));
			}
			break;
		default:
			break;
		}
	}
}
