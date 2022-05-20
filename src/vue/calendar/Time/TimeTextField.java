package vue.calendar.Time;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import vue.calendar.HomeMadeCalendar;

public abstract class TimeTextField extends JTextField {

	private static final long serialVersionUID = -8998205272545644837L;
	
	private HomeMadeCalendar calendar;
	
	TimeTextField(HomeMadeCalendar calendar, String s) {
		
		setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.calendar = calendar;
		setDocument(new LengthRestrictedDocument(2));
		setText(s);
		getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
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
	
	public abstract boolean timeIsValid();
	
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
	    	  System.out.println("Cannot insert more character");
	    	  for(int i=0;i<10;i++) {
	    		  setBackground(new Color(Color.HSBtoRGB((float)Math.random(), (float)0.5, (float)0.5)));

	    		  SwingUtilities.updateComponentTreeUI(calendar);
	    		  try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		  setBackground(Color.white);
	    		  	    		  /*SwingUtilities.updateComponentTreeUI(calendar);
	    		  try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
	    	  }
	      }
	    }
	}
	
	
}
