package vue.calendar.Time;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class NumberTextField extends JTextField{

	private static final long serialVersionUID = 1248515377178593582L;
	
	public NumberTextField(){
		
		setDocument(new NumberRestrictedDocument());
	}
	
	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch(final NumberFormatException e) {
			return false;
		}
	}
	
	public final class NumberRestrictedDocument extends PlainDocument {

		private static final long serialVersionUID = 1L;


	    public NumberRestrictedDocument() {
	    }
	
	    @Override
	    public void insertString(int offs, String str, AttributeSet a)
	        throws BadLocationException {
	      if (str == null)
	        return;
	
	      if (isParsable(str)) {
	        super.insertString(offs, str, a);
	      }
	    }
	}

}
