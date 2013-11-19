package ch.androidworkshop.alarmmanager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TimePicker;

// Code from here: http://wptrafficanalyzer.in/blog/displaying-timepickerdialog-using-dialogfragment-in-android-with-backward-compatibilty-support-library/
public class TimePickerDialogFragment extends DialogFragment {
	Handler mHandler;
	int mHour;
	int mMinute;

	public void setHandler(Handler h) {
		mHandler = h;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		/** Creating a bundle object to pass currently set time to the fragment */
		Bundle b = getArguments();

		/** Getting the hour of day from bundle */
		mHour = b.getInt("set_hour");

		/** Getting the minute of hour from bundle */
		mMinute = b.getInt("set_minute");

		TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

				mHour = hourOfDay;
				mMinute = minute;

				/**
				 * Creating a bundle object to pass currently set time to the
				 * fragment
				 */
				Bundle b = new Bundle();

				/** Adding currently set hour to bundle object */
				b.putInt("set_hour", mHour);

				/** Adding currently set minute to bundle object */
				b.putInt("set_minute", mMinute);

				/** Adding Current time in a string to bundle object */
				b.putString("set_time", "Set Time : " + Integer.toString(mHour) + " : " + Integer.toString(mMinute));

				/** Creating an instance of Message */
				Message m = new Message();

				/** Setting bundle object on the message object m */
				m.setData(b);

				/**
				 * Message m is sending using the message handler instantiated
				 * in MainActivity class
				 */
				mHandler.sendMessage(m);
			}
		};

		/** Opening the TimePickerDialog window */
		return new TimePickerDialog(getActivity(), listener, mHour, mMinute, false);
	}
}
