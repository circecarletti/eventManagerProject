package vue.calendar.time;

import vue.calendar.HomeMadeCalendar;
import vue.calendar.HomeMadeCalendar.ERROR_STATE;
import model.MyDate;

public class HourTextField extends TimeTextField{

	private static final long serialVersionUID = -5077633988306123499L;

	HourTextField(HomeMadeCalendar calendar, String s) {
		super(calendar, s);
		
	}

	@Override
	public boolean timeIsValid() {
		boolean output = textFieldIsValid(this) && MyDate.hourIsValid(Integer.parseInt(getText()));
		if(!output) {
			if(!getText().equals(""))blink();
			calendar.setErrorState(ERROR_STATE.invalidHours);
			if(!textFieldIsValid(this)) {
				calendar.setErrorState(ERROR_STATE.invalidCharacter);
				if(getText().equals("")) {
					calendar.setErrorState(ERROR_STATE.emptyHourField);
				}
			}
		}
		return output;
	}
	
	@Override
	public void setErrorState() {
		calendar.setErrorState(ERROR_STATE.invalidHours);
	}


}
