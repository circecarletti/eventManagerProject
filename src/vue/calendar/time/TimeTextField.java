package vue.calendar.time;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import vue.calendar.HomeMadeCalendar;
import vue.calendar.HomeMadeCalendar.ERROR_STATE;

public abstract class TimeTextField extends JTextField {

	private static final long serialVersionUID = -8998205272545644837L;
	
	
	
	protected HomeMadeCalendar calendar;
	
	private Color defaultColor;
	private Color blinkColor;
	private Timer timer;
	int timerCounter;
	
	
	
	
	TimeTextField(HomeMadeCalendar calendar, String s) {
		
		setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.calendar = calendar;
		setDocument(new LengthRestrictedDocument(2));
		setText(s);
		getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				timer.stop();
				textFieldChange();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				textFieldChange();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				textFieldChange();
			}
		});
		setColumns(10);
		
		timerCounter = 0;
		defaultColor = Color.white;
		blinkColor = new Color(255,102,102);
		timer = createTimer();
		
	}
	
	private Timer createTimer() {
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				timerCounter++;
				if(getBackground().equals(defaultColor)) {
					setBackground(blinkColor);
				}
				else {
					setBackground(defaultColor);
				}
				if(timerCounter>=6) {
					timer.stop();
					timerCounter = 0;
					//setBackground(Color.white);
				}
			}
		};
		return new Timer(70,action);
	}
	
	public void blink() {
		timer.start();
	}
	
	
	
	
	public void textFieldChange() {
		calendar.refresh();
	}

	public boolean textFieldIsValid(JTextField textField) {
		return isParsable(textField.getText());
	}
	
	public boolean textFieldIsValid() {
		return isParsable(getText());
	}
	
	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch(final NumberFormatException e) {
			return false;
		}
	}
	
	
	
	public final class LengthRestrictedDocument extends PlainDocument {

		private static final long serialVersionUID = 1L;
		
		private final int limit;

	    public LengthRestrictedDocument(int limit) {
	      this.limit = limit;
	    }
	
	    @Override
	    public void insertString(int offs, String str, AttributeSet a)
	        throws BadLocationException {
	      if (str == null)
	        return;
	
	      if ((getLength() + str.length()) <= limit) {
	        super.insertString(offs, str, a);
	      }
	      else {
	    	  calendar.setErrorState2(ERROR_STATE.maxLengthReached);
	    	  blink();
	    	  
	    	  }
	      }
	    
	}
	
	public abstract void setErrorState();
	
	public abstract boolean timeIsValid();
	
	
}
