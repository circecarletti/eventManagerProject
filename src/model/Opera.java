package model;

public class Opera extends EntertainmentEvent {
	protected String style;


	/**
	 * @param date
	 * @param capacity
	 * @param style
	 * @param name 
	 */
	public Opera(MyDate date, int capacity, String style, String name) {
		super(date, capacity, name);
		this.style = style;
	}

	@Override
	public String toString() {
		return "Opera [style=" + style + ", date=" + date + ", capacity=" + capacity + "]";
	}
	
	@Override
	public String print() {
		return "(opera) " + name + ", " + date.toString();
	}
	

}
