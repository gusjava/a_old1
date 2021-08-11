package a.framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class S1 implements S {

	public final static int EVENTID = 1000;

	private Vector listeners = new Vector();
	private boolean activated = true;

	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}

	public void removeActionListener(ActionListener listener) {
		listeners.remove(listener);
	}

	public List listeners() {
		return listeners;
	}

	private void fireActionEvent(Object source, String id) {
		if(source == null) source = this;
		ActionEvent evt = new ActionEvent(source, EVENTID, id);

		for(int i=0; i<listeners.size(); i++)
			((ActionListener) listeners.get(i)).actionPerformed(evt);
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isActivated() {
		return activated;
	}

	public void send(Object source, String id) {
		if (activated) fireActionEvent(source, id);
	}
}