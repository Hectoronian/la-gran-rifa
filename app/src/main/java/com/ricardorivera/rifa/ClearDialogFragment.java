package com.ricardorivera.rifa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;

public class ClearDialogFragment  extends DialogFragment {

    private ShakeList inList;
    private RecyclerView.Adapter inAdapter;
    private RecyclerView inView;
    public void getData(ShakeList l, RecyclerView.Adapter a, RecyclerView v) {
        inList = l;
        inAdapter = a;
        inView = v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Are you sure you want to DELETE all items?")
               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        inList.clearArray();
                        inView.setAdapter(inAdapter);
                    }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
               });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
