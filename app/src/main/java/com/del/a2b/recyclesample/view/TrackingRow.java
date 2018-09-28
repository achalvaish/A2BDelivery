package com.del.a2b.recyclesample.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.del.a2b.recyclesample.R;
import com.del.a2b.recyclesample.controller.TrackingManager;
import com.del.a2b.recyclesample.controller.TrackingService;
import com.del.a2b.recyclesample.model.RecyclerViewRow;
import com.del.a2b.recyclesample.model.Trackable;
import com.del.a2b.recyclesample.model.Tracking;

public class TrackingRow extends CardView implements RecyclerViewRow<Tracking> {
    private TextView mName, mLocation, mTime;
    private Button mEdit;
    private String ID;
    int trackableID;

    public TrackingRow(Context context) {
        super(context);
    }

    public TrackingRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TrackingRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mName = (TextView) findViewById(R.id.tracking_name);
        mLocation = (TextView) findViewById(R.id.tracking_Location);
        mTime = (TextView) findViewById(R.id.tracking_Time);
        mEdit = (Button) findViewById(R.id.edit_tracking);

        mEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trackingIntent = new Intent(getContext(), AddTrackingActivity.class);

                trackingIntent.putExtra("Intent",2);
                trackingIntent.putExtra("Tracking", ID);
                trackingIntent.putExtra("Trackable",trackableID);
                trackingIntent.putExtra("Tracking Name",mName.getText());
                getContext().startActivity(trackingIntent);
            }
        });
    }

    @Override
    public void showData(Tracking item) {
        mName.setText(item.getTitle());
        mLocation.setText(item.getMeetLocation());
        mTime.setText(item.getMeetTime().toString());
        ID = item.getId();
        trackableID = item.getTrackableId();
    }

}