package com.example.setalarm;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiverActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        showDialog();

        //finish();
        
        /*this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.alarm);
 
        Button stopAlarm = (Button) findViewById(R.id.stopAlarm);
        Button snoozeAlarm = (Button) findViewById(R.id.snoozeAlarm);
        stopAlarm.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
            	Toast.makeText(arg0.getContext(), "Alarm Stopped", Toast.LENGTH_SHORT).show();
            	return false;
            }
        });
        snoozeAlarm.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
            	Toast.makeText(arg0.getContext(), "Alarm Stopped", Toast.LENGTH_SHORT).show();
				return false;
            }
        });*/
    }
	public void showDialog() {
		DialogFragment newFragment = new AlarmDialogFragment();
        newFragment.show(getFragmentManager(), "alarm");
	}

	public void doPositiveClick() {
	    // Do stuff here.
	    Log.i("FragmentAlertDialog", "Positive click!");
	   	Toast.makeText(this, "Alarm Snoozed", Toast.LENGTH_SHORT).show();
	    finish();
	}

	public void doNegativeClick() {
	    // Do stuff here.
	    Log.i("FragmentAlertDialog", "Negative click!");
   		Toast.makeText(this, "Alarm Stopped", Toast.LENGTH_SHORT).show();
	    finish();
	}
}
