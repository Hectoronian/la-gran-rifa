package com.ricardorivera.rifa;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ShakeList itemList;
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    protected LayoutManager layoutManager;
    private ShakeDialogFragment shakeDialog;
    private ClearDialogFragment clearDialog;
    FragmentManager fragmentManager = getSupportFragmentManager();

    // Sensor data
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

        public void onSensorChanged(SensorEvent se) {

            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
            Toast.makeText(this, "Shaken:", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //Not used
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialise the App
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // DATA
        //Shake array data structure
        itemList = new ShakeList();


        // LIST
        // recycle list view
        recyclerView = findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next ricardorivera)
        mAdapter = new ShakeListAdapter(itemList);
        recyclerView.setAdapter(mAdapter);



        // BUTTONS
        // clear
        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DIALOG
                clearDialog = new ClearDialogFragment();
                clearDialog.getData(itemList, mAdapter, recyclerView);
                clearDialog.show(fragmentManager, "clear_dialog");
            }
        });

        // shake
        Button shakeButton = findViewById(R.id.shake_button);
        shakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DIALOG
                shakeDialog = new ShakeDialogFragment();
                shakeDialog.getData(itemList);
                shakeDialog.show(fragmentManager, "shake_dialog");
            }
        });


//TEXT INPUT
        EditText textInput = findViewById(R.id.editText);
        textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String inText = v.getText().toString();
                itemList.addItem(inText);
                v.setText("");
                v.setFocusableInTouchMode(true);
                v.requestFocus();
                return false;
            }
        });
    }

}
