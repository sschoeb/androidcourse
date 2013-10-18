package ch.schoeb.JavaBasics.Event;

public class EventObserver {

	private ObservableItem observableItem = new ObservableItem();

	private ObserverListener listener = new ObserverListener() {

		@Override
		public void onObservableItemChanged() {
			// The observableItem has changed its state
			// so we can react here to this
		}
	};

	public void startObservation() {
		observableItem.addObserver(listener);
	}

	public void stopObservation() {
		observableItem.removeObserver(listener);
	}
}
