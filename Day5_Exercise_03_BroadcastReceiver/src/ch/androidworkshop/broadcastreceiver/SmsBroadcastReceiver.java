package ch.androidworkshop.broadcastreceiver;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsBroadcastReceiver {

	// TODO 1: Implement your receiver here
	// TODO 2: Navigate to the MainActivity when you have received the SMS
	// TODO 3: Check extras delivered to the MainActivity and add them here too

	/**
	 * Helper method to get the first SMS out of the BroadcastReceiver intent
	 */
	private SmsMessage getFirstSmsFromIntent(Intent dataIntent)
	{
		Bundle bundle = dataIntent.getExtras();
		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			final SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < pdus.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			}
			if (messages.length > -1) {
				return messages[0];
			}
			
		}
		return null;
	}
}
