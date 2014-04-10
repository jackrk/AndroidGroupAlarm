package com.example.setalarm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" }) 
public class AlarmInterface {
	
	AlarmManager am;
	PendingIntent pi;
    Context mContext;

    /** Instantiate the interface and set the context */
    AlarmInterface(Context c, PendingIntent pend) {
        mContext = c;
        pi = pend;
        am = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
    }
    /**
     * Set's an alarm via the input parameters, using the current date of the year as a reference. 
     * 
     * Currently the alarm works if the device is awake, but should work when device is asleep. 
     * See note on line 67
     * 
     * @param time Time of alarm ex. "7:30 am"
     * @param day Day of week for alarm repeat
     * @param offset Minutes backwards from time that alarm will be set. 
     */
    @JavascriptInterface
    public void set(String time, String day, String offset) {
		try {
			int minuteOffset = -(Integer.parseInt(offset));
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa EEEEEE's'");
			SimpleDateFormat printer = new SimpleDateFormat("hh:mm aa EEEEEE's' MMM dd yyyy");
			if (time.indexOf(Character.getNumericValue(':')) == 1) {
				time = "0" + time;
			}
			
			// alarm is needed to maintain month, date, year for alarm, otherwise the alarm will be set for 1970 and go off immediately
			// timeRef is only needed for the time and day of the week
			Calendar alarm = Calendar.getInstance();
			Calendar timeRef = Calendar.getInstance();
			
			//alarm.setTime(sdf.parse("01:42 pm" + " " + "Thursdays"));
			timeRef.setTime(sdf.parse(time + " " + day));
			
			alarm.set(Calendar.DAY_OF_WEEK, timeRef.get(Calendar.DAY_OF_WEEK));
			alarm.set(Calendar.HOUR, timeRef.get(Calendar.HOUR));
			alarm.set(Calendar.AM_PM, timeRef.get(Calendar.AM_PM));
			alarm.set(Calendar.MINUTE, timeRef.get(Calendar.MINUTE));
			alarm.add(Calendar.MINUTE, minuteOffset);
			
			// Set one-time
			am.set(AlarmManager.RTC_WAKEUP, alarm.getTimeInMillis(), pi);
			
			// AlarmManager.RTC_WAKEUP should give the new alarm permission to wake the device up. 
			// This didn't work on the virtual device; hasn't been tested on real device. 
			//am.setRepeating(AlarmManager.RTC_WAKEUP, alarm.getTimeInMillis(),
			//		AlarmManager.INTERVAL_DAY*7, pi);
			Log.d("alarm set", printer.format(new Date(alarm.getTimeInMillis())));
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("interface error", "err", e);
		}

		Toast.makeText(mContext, "Alarm Set", Toast.LENGTH_SHORT).show();
    }
    
}