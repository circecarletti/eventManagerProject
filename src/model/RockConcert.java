package model;

import java.util.TreeSet;

public class RockConcert extends EntertainmentEvent {
	
	protected String drummer;
	protected String guitarist;
	
	/**
	 * @param date
	 * @param capacity
	 * @param drummer
	 * @param guitarist
	 * @param name
	 */
	public RockConcert(MyDate date, int capacity, String drummer, String guitarist, String name) {
		super(date, capacity, name);
		this.drummer = drummer;
		this.guitarist = guitarist;
	}

	

	@Override
	public String toString() {
		return "RockConcert [drummer=" + drummer + ", guitarist=" + guitarist + ", date="
				+ date + ", capacity=" + capacity + ", name=" + name + ", eventID=" + eventID + "]";
	}




	public boolean isIn(TreeSet<RockConcert> rockConcerts) {
		for (RockConcert concert : rockConcerts) {
			if (this.compareTo(concert) == 1) {
				return true;
			}
		}
		return false;
	}

	
}
