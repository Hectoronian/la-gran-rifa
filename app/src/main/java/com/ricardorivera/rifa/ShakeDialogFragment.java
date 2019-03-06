package com.ricardorivera.rifa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class ShakeDialogFragment extends DialogFragment {

    private ShakeList inState;
    public void getData(ShakeList a) {
        inState = a;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(inState.shakeArray())
               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                   }
               });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}