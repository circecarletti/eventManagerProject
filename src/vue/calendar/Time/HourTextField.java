package vue.calendar.Time;

import vue.calendar.HomeMadeCalendar;
import model.MyDate;

public class HourTextField extends TimeTextField{

	private static final long serialVersionUID = -5077633988306123499L;

	HourTextField(HomeMadeCalendar calendar, String s) {
		super(calendar, s);
		
	}

	@Override
	public boolean timeIsValid() {
		return (textFieldIsValid(this) && MyDate.hourIsValid(Integer.parseInt(getText())));
	}
	


}
