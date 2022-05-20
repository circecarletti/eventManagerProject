package model;

public class EntertainmentEvent implements Comparable<EntertainmentEvent>{
	public MyDate date;
	protected int capacity;
	public String name;
	public int eventID;
	
	/**
	 * @param date
	 * @param capacity
	 */
	public EntertainmentEvent(MyDate date, int capacity, String name) {
		super();
		this.date = date;
		this.capacity = capacity;
		this.name = name;
		this.eventID = createID(date, capacity, name);
	}

	@Override
	public String toString() {
		return "EntertainmentEvent [date=" + date + ", capacity=" + capacity + "]";
	}
	
	
	public int createID(MyDate date, int capacity, String name) {
		 int result = 24;
		 result = 1 * result + date.hashCode();
	     result = 3 * result + name.hashCode();
	     return result;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + eventID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntertainmentEvent other = (EntertainmentEvent) obj;
		if (other.eventID == this.eventID)
			return true;
		return false;
	}
	
	
	

	/**
	 * @param event : the event you want to compare
	 * @return 1 if the ID event are identical, -1 otherwise
	 */
	@Override
	public int compareTo(EntertainmentEvent event) {
		//System.out.println("compare " + this.eventID + " to " + event.eventID);
		if(this.eventID == event.eventID) {
			return 1;
		} else {
			return -1;
		}
	}
	
}
