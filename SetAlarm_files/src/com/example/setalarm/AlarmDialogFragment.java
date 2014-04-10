package com.example.setalarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class AlarmDialogFragment extends DialogFragment {
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.alarm_dialog_message)
               .setPositiveButton(R.string.snooze_alarm, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       ((AlarmReceiverActivity)getActivity()).doPositiveClick();
                   }
               })
               .setNegativeButton(R.string.stop_alarm, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       ((AlarmReceiverActivity)getActivity()).doNegativeClick();}
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
