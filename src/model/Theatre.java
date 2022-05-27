package model; 

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

	public Theatre(MyDate date, int capacity, String name, String themeScene, String style) {
		super(date, capacity, name);
		this.themeScene = themeScene;
		this.style = style;
	}
	
	public String print() {
		return "[TH]- " + name + ", " + date.toString();
	}


	
}
