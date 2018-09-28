package com.del.a2b.recyclesample.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.del.a2b.recyclesample.R;
import com.del.a2b.recyclesample.controller.TrackableManager;
import com.del.a2b.recyclesample.controller.TrackingManager;
import com.del.a2b.recyclesample.model.LastScreen;

import java.util.Date;
import java.util.List;

public class AddTrackingActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private int defaultTrackableID = 0, ADD_INTENT =1, EDIT_INTENT=2;
    private TextView mTrackingName,mTrackableName,mTrackableDescription,mHeadLabel;;
    private Spinner mMeetTime,mMeetLocation;
    private Button addButton,editButton;
    private TrackingManager trackingManager;
    private Date meetTime;
    private String meetLocation,trackingID;
    private int trackableID;
    private List<Date> availableDates;
    private List<String> availableLocation;
    private ArrayAdapter<String> LocationAdapter;
    private TrackableManager trackableManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        trackingManager = new TrackingManager(this);
        trackableManager = new TrackableManager(this);

        Intent intent = getIntent();
        int INTENT = intent.getIntExtra("Intent",0);


        mTrackingName = (TextView) findViewById(R.id.txt_Name);
        mMeetTime = (Spinner) findViewById(R.id.cb_Time);
        mMeetLocation = (Spinner) findViewById(R.id.cb_location);
        addButton = (Button) findViewById(R.id.add_btn);
        editButton = (Button) findViewById(R.id.edit_btn);
        mHeadLabel = (TextView) findViewById(R.id.headLabel);
        mTrackableName = (TextView) findViewById(R.id.lbl_trackablename);
        mTrackableDescription = (TextView) findViewById(R.id.lbl_trackabledescription);

        if(INTENT == ADD_INTENT) {
            trackableID= intent.getIntExtra("Trackable", defaultTrackableID);
            mTrackableName.setText(getIntent().getStringExtra("Trackable Name"));
            mTrackableDescription.setText(getIntent().getStringExtra("Trackable Description"));
            editButton.setVisibility(View.GONE);
            addButton.setVisibility(View.VISIBLE);
            TrackableActivity.lastScreen = LastScreen.ADD;
        }

        else if(INTENT == EDIT_INTENT)
        {
            trackableID = intent.getIntExtra("Trackable",defaultTrackableID);
            trackingID = intent.getStringExtra("Tracking");
            mTrackingName.setText(intent.getStringExtra("Tracking Name"));
            mHeadLabel.setVisibility(View.GONE);
            mTrackableName.setVisibility(View.GONE);
            mTrackableDescription.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.GONE);
            TrackableActivity.lastScreen = LastScreen.EDIT;
        }


        availableDates = trackingManager.getAvailableTimes(trackableID);


        ArrayAdapter<Date> timeAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,availableDates);
        mMeetTime.setAdapter(timeAdapter);

        availableLocation = trackingManager.getAvailableLocation(trackableID,(Date)mMeetTime.getSelectedItem());

        LocationAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,availableLocation);
        mMeetLocation.setAdapter(LocationAdapter);

        mMeetTime.setOnItemSelectedListener(this);
        mMeetLocation.setOnItemSelectedListener(this);

        addButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:  List<Date> eventDates=trackingManager.getNearestTime(trackableID,meetTime);
                trackingManager.createTracking(trackableID,mTrackingName.getText().toString(),eventDates.get(0),eventDates.get(1),meetTime,trackingManager.getCurrentLocation(trackableID),meetLocation);
                break;
            case R.id.edit_btn: trackingManager.updateTracking(trackingID,mTrackingName.getText().toString(),meetTime,meetLocation);
                break;
        }
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.cb_Time: meetTime = (Date) parent.getItemAtPosition(position);
                availableLocation = trackingManager.getAvailableLocation(trackableID,(Date)mMeetTime.getSelectedItem());
                LocationAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,availableLocation);
                LocationAdapter.notifyDataSetChanged();
                mMeetLocation.setAdapter(LocationAdapter);
                break;
            case R.id.cb_location: meetLocation = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
