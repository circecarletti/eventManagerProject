package model;

import java.util.TreeSet;

public class Theatre extends EntertainmentEvent{
	

	protected String themeScene;
	protected String style;
	
	/**
	 * @param performers
	 * @param themeScene
	 * @param style
	 * @param date 
	 * @param capacity 
	 * @param name
	 */
	public Theatre(String themeScene, String style, MyDate date, int capacity, String name) {
		super(date, capacity, name);
		this.themeScene = themeScene;
		this.style = style;
	}


	public boolean isIn(TreeSet<Theatre> representationList) {
		for (Theatre representation : representationList) {
			if (this.compareTo(representation) == 1) {
				return true;
			}
		}
		return false;
	}
	
}
