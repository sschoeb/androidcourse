package ch.schoeb.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        
        Button btnSecondScreen = (Button)findViewById(R.id.buttonNavigateScreen2);
        btnSecondScreen.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View btn) {
				Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
				startActivity(intent);
			}
        	
        });

        Button btnOtherApp = (Button)findViewById(R.id.buttonNavigateOtherApp);
        btnOtherApp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View btn) {
				Intent intent = new Intent("ch.schoeb.navigationotherapp.goforit");
				startActivity(intent);
			}
		});
    }
    
}
