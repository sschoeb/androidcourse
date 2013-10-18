package ch.schoeb.JavaBasics.Event;

import java.util.ArrayList;

public class ObservableItem {

	private ArrayList<ObserverListener> registeredListeners = new ArrayList<ObserverListener>();

	public void addObserver(ObserverListener listener) {
		registeredListeners.add(listener);
	}

	public void removeObserver(ObserverListener listener) {
		registeredListeners.remove(listener);
	}

	private void fireEvent() {
		for (ObserverListener listener : registeredListeners) {
			listener.onObservableItemChanged();
		}
	}

	public void doSomething() {
		// Do something here that changes this object
		// so that we have to notify the observers...

		fireEvent();
	}
}
