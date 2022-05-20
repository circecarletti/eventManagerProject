package vue.calendar.Time;

import model.MyDate;
import vue.calendar.HomeMadeCalendar;

public class MinuteTextField extends TimeTextField{

	private static final long serialVersionUID = -5077633988306123499L;

	MinuteTextField(HomeMadeCalendar calendar, String s) {
		super(calendar, s);
		
	}

	@Override
	public boolean timeIsValid() {
		return (textFieldIsValid(this) && MyDate.minuteIsValid(Integer.parseInt(getText())));
	}
	


}
