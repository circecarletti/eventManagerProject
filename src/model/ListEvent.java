package model;

import java.util.TreeSet;

public class ListEvent {

	public TreeSet<EntertainmentEvent> eventList;
	public EntertainmentEventType eventType;
	
	public ListEvent event;
	
	public ListEvent(EntertainmentEventType eventType){
		eventList = new TreeSet<EntertainmentEvent>();
		this.eventType = eventType;
	}
	
	public boolean addEvent(EntertainmentEvent event) {
		if (!event.isIn(eventList)) {
			if(eventList.add(event)) 
			{
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
