package model;

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
	public RockConcert(MyDate date, String name, int capacity, String drummer, String guitarist) {
		super(date, capacity, name);
		this.drummer = drummer;
		this.guitarist = guitarist;
	}

	

	@Override
	public String toString() {
		return "RockConcert [drummer=" + drummer + ", guitarist=" + guitarist + ", date="
				+ date + ", capacity=" + capacity + ", name=" + name + ", eventID=" + eventID + "]";
	}
	
	public String print() {
		return "(rock) " + name + ", " + date.toString();
	}


}
