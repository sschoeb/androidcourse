package com.example.day3_exercise_03_preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class CustomPreferencesFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
	}
	
}
