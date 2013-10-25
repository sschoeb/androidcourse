package com.example.day3_exercise_03_preferences;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class PreferencesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(android.R.id.content, new CustomPreferencesFragment());
		ft.commit();
	}

}
