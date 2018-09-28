package com.del.a2b.recyclesample.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.del.a2b.recyclesample.R;
import com.del.a2b.recyclesample.controller.RecyclerViewAdapter;
import com.del.a2b.recyclesample.controller.TrackableManager;
import com.del.a2b.recyclesample.controller.TrackingManager;
import com.del.a2b.recyclesample.model.LastScreen;
import com.del.a2b.recyclesample.model.Trackable;
import com.del.a2b.recyclesample.model.Tracking;

public class TrackableActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private TrackableManager trackableObj;
    private TrackingManager trackingObj;
    private RecyclerViewAdapter<Trackable> trackableAdapter;
    private RecyclerViewAdapter<Tracking> trackingAdapter;
    private Button mTrackable,mTracking;
    private Spinner categoryFilter;
    public static LastScreen lastScreen= LastScreen.NONE;
    public static String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackable);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mTrackable = (Button) findViewById(R.id.trackable_btn);
        mTracking = (Button) findViewById(R.id.tracking_btn);
        categoryFilter = (Spinner) findViewById(R.id.spinner);

        mTrackable.setOnClickListener(this);
        mTracking.setOnClickListener(this);

        trackableObj = new TrackableManager(this);
        trackingObj = new TrackingManager(this);
        mLayoutManager=new LinearLayoutManager(this);

        trackableAdapter =new RecyclerViewAdapter<>(this,trackableObj.getTrackableListName(),R.layout.rv_trackable);
        trackingAdapter = new RecyclerViewAdapter<>(this,TrackingManager.getTrackingListName(),R.layout.rv_tracking);

        categoryFilter.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, TrackableManager.getCategories()));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(trackableAdapter);
        categoryFilter.setOnItemSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.trackable_btn: trackableAdapter.updateDataset(trackableObj.getTrackableListName());
                    trackableAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(trackableAdapter);
                    categoryFilter.setVisibility(View.VISIBLE);
                break;
            case R.id.tracking_btn:trackingAdapter.updateDataset(TrackingManager.getTrackingListName());
                     trackingAdapter.notifyDataSetChanged();
                     mRecyclerView.setAdapter(trackingAdapter);
                     categoryFilter.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (lastScreen)
        {
            case ADD: trackableAdapter.updateDataset(trackableObj.getTrackableListName());
                trackableAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(trackableAdapter);
                break;
            case EDIT: tracking_btn:trackingAdapter.updateDataset(TrackingManager.getTrackingListName());
                trackingAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(trackingAdapter);
                break;
            case NONE:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.spinner:
                trackableAdapter.updateDataset(trackableObj.getTrackableByCategory(parent.getItemAtPosition(position).toString()));
                trackableAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(trackableAdapter);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
