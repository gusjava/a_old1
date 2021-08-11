package a.framework;

import java.awt.event.ActionListener;
import java.util.List;

public interface S {
	public void addActionListener(ActionListener listener) throws Exception;
	public void removeActionListener(ActionListener listener) throws Exception;
	public List listeners() throws Exception;
}