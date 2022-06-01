package vue.calendar.time;

import model.MyDate;
import vue.calendar.HomeMadeCalendar;
import vue.calendar.HomeMadeCalendar.ERROR_STATE;

public class MinuteTextField extends TimeTextField{

	private static final long serialVersionUID = -5077633988306123499L;

	MinuteTextField(HomeMadeCalendar calendar, String s) {
		super(calendar, s);
		
	}

	@Override
	public boolean timeIsValid() {
		boolean output = textFieldIsValid(this) && MyDate.minuteIsValid(Integer.parseInt(getText()));
		if(!output) {
			
			if(!getText().equals(""))blink();
			calendar.setErrorState(ERROR_STATE.invalidMinutes);
			if(!textFieldIsValid(this)) {
				
				calendar.setErrorState(ERROR_STATE.invalidCharacter);
				if(getText().equals("")) {
					calendar.setErrorState(ERROR_STATE.emptyMinuteField);
				}
			}
		}
		return output;
	}
	
	@Override
	public void setErrorState() {
		calendar.setErrorState(ERROR_STATE.invalidMinutes);
	}


}
