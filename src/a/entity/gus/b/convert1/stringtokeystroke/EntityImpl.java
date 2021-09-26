package a.entity.gus.b.convert1.stringtokeystroke;

import a.framework.*;

import java.awt.Event;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210926";}
	
	
	public static final String CTRL = "ctrl ";
	public static final String CTRL_SHIFT = "ctrl shift ";
	public static final String CTRL_ALT = "ctrl alt ";
	
	public static final String SHIFT = "shift ";
	public static final String SHIFT_ALT = "shift alt ";
	
	public static final String ALT = "alt ";
	public static final String ALT_SHIFT = "alt shift ";
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String s0 = s.toLowerCase();
			
		if(s0.startsWith(CTRL_SHIFT))
			return keyStroke_ctrlShift(s.substring(CTRL_SHIFT.length()));
		if(s0.startsWith(CTRL_ALT))
			return keyStroke_ctrlAlt(s.substring(CTRL_ALT.length()));
		if(s0.startsWith(CTRL))
			return keyStroke_ctrl(s.substring(CTRL.length()));
		
		if(s0.startsWith(SHIFT_ALT))
			return keyStroke_shiftAlt(s.substring(SHIFT_ALT.length()));
		if(s0.startsWith(SHIFT))
			return keyStroke_shift(s.substring(SHIFT.length()));
			
		if(s0.startsWith(ALT_SHIFT))
			return keyStroke_altShift(s.substring(ALT_SHIFT.length()));
		if(s0.startsWith(ALT))
			return keyStroke_alt(s.substring(ALT.length()));
			
		return keyStroke(s);
	}
	

	private KeyStroke keyStroke(String s) throws Exception
	{return KeyStroke.getKeyStroke(code(s),0);}
	
	

	private KeyStroke keyStroke_ctrl(String s) throws Exception
	{return KeyStroke.getKeyStroke(code(s),Event.CTRL_MASK);}

	private KeyStroke keyStroke_ctrlShift(String s) throws Exception
	{return KeyStroke.getKeyStroke(code(s),Event.CTRL_MASK + Event.SHIFT_MASK);}

	private KeyStroke keyStroke_ctrlAlt(String s) throws Exception
	{return KeyStroke.getKeyStroke(code(s),Event.CTRL_MASK + Event.ALT_MASK);}



	private KeyStroke keyStroke_shift(String s) throws Exception
	{return KeyStroke.getKeyStroke(code(s),KeyEvent.SHIFT_MASK);}
	
	private KeyStroke keyStroke_shiftAlt(String s) throws Exception
	{return KeyStroke.getKeyStroke(code(s),Event.SHIFT_MASK + Event.ALT_MASK);}



	private KeyStroke keyStroke_alt(String s) throws Exception
	{return KeyStroke.getKeyStroke(code(s),Event.ALT_MASK);}

	private KeyStroke keyStroke_altShift(String s) throws Exception
	{return KeyStroke.getKeyStroke(code(s),Event.ALT_MASK + Event.SHIFT_MASK);}




    private int code(String s) throws Exception
    {
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e){}
			
		s = s.toUpperCase();

    	if(s.equals("F1")) return KeyEvent.VK_F1;
    	if(s.equals("F2")) return KeyEvent.VK_F2;
    	if(s.equals("F3")) return KeyEvent.VK_F3;
    	if(s.equals("F4")) return KeyEvent.VK_F4;
    	if(s.equals("F5")) return KeyEvent.VK_F5;
    	if(s.equals("F6")) return KeyEvent.VK_F6;
    	if(s.equals("F7")) return KeyEvent.VK_F7;
    	if(s.equals("F8")) return KeyEvent.VK_F8;
    	if(s.equals("F9")) return KeyEvent.VK_F9;
    	if(s.equals("F10")) return KeyEvent.VK_F10;
    	if(s.equals("F11")) return KeyEvent.VK_F11;
    	if(s.equals("F12")) return KeyEvent.VK_F12;
        
    	if(s.equals("A")) return KeyEvent.VK_A;
    	if(s.equals("B")) return KeyEvent.VK_B;
    	if(s.equals("C")) return KeyEvent.VK_C;
    	if(s.equals("D")) return KeyEvent.VK_D;
    	if(s.equals("E")) return KeyEvent.VK_E;
    	if(s.equals("F")) return KeyEvent.VK_F;
    	if(s.equals("G")) return KeyEvent.VK_G;
    	if(s.equals("H")) return KeyEvent.VK_H;
    	if(s.equals("I")) return KeyEvent.VK_I;
    	if(s.equals("J")) return KeyEvent.VK_J;
    	if(s.equals("K")) return KeyEvent.VK_K;
    	if(s.equals("L")) return KeyEvent.VK_L;
    	if(s.equals("M")) return KeyEvent.VK_M;
    	if(s.equals("N")) return KeyEvent.VK_N;
    	if(s.equals("O")) return KeyEvent.VK_O;
    	if(s.equals("P")) return KeyEvent.VK_P;
    	if(s.equals("Q")) return KeyEvent.VK_Q;
    	if(s.equals("R")) return KeyEvent.VK_R;
    	if(s.equals("S")) return KeyEvent.VK_S;
    	if(s.equals("T")) return KeyEvent.VK_T;
    	if(s.equals("U")) return KeyEvent.VK_U;
    	if(s.equals("V")) return KeyEvent.VK_V;
    	if(s.equals("W")) return KeyEvent.VK_W;
    	if(s.equals("X")) return KeyEvent.VK_X;
    	if(s.equals("Y")) return KeyEvent.VK_Y;
    	if(s.equals("Z")) return KeyEvent.VK_Z;

		if(s.equals("DOWN")) return KeyEvent.VK_DOWN;
		if(s.equals("UP")) return KeyEvent.VK_UP;
		if(s.equals("RIGHT")) return KeyEvent.VK_RIGHT;
		if(s.equals("LEFT")) return KeyEvent.VK_LEFT;
    	
    	if(s.equals("ESCAPE")) return KeyEvent.VK_ESCAPE;
    	if(s.equals("SPACE")) return KeyEvent.VK_SPACE;
    	if(s.equals("BACKSPACE")) return KeyEvent.VK_BACK_SPACE;
    	if(s.equals("DEL")) return KeyEvent.VK_DELETE;
    	if(s.equals("ENTER")) return KeyEvent.VK_ENTER;

    	if(s.equals("-")) return KeyEvent.VK_MINUS;
    	if(s.equals("+")) return KeyEvent.VK_EQUALS;		//touche +=}
    	if(s.equals("!")) return KeyEvent.VK_EXCLAMATION_MARK;
    	if(s.equals("/")) return KeyEvent.VK_COLON;		//touche /:
	
    	throw new Exception("Unknown code: "+s);
    }
}
