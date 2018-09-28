package com.del.a2b.recyclesample.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.del.a2b.recyclesample.R;
import com.del.a2b.recyclesample.model.LastScreen;
import com.del.a2b.recyclesample.model.RecyclerViewRow;
import com.del.a2b.recyclesample.model.Trackable;

public class TrackableRow extends CardView implements RecyclerViewRow<Trackable>{
    private TextView mName,mDesc;
    private Button mCategory,mAdd;
    private Context context;
    private int trackableID;

    public TrackableRow(Context context) {
        super(context);
        this.context = context;
    }
    public TrackableRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TrackableRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mName = (TextView) findViewById(R.id.truck_name);
        mDesc =(TextView) findViewById(R.id.truck_description);
        mCategory = (Button) findViewById(R.id.category_btn);
        mAdd = (Button) findViewById(R.id.add_tracking);

        mAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trackingIntent = new Intent(getContext(), AddTrackingActivity.class);

                trackingIntent.putExtra("Intent",1);
                trackingIntent.putExtra("Trackable", trackableID);
                trackingIntent.putExtra("Trackable Name",mName.getText());
                trackingIntent.putExtra("Trackable Description",mDesc.getText());
                getContext().startActivity(trackingIntent);
            }
        });

        mCategory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public void showData(Trackable item) {
        mName.setText(item.getName());
        mCategory.setText(item.getCategory());
        mDesc.setText(item.getDescription());
        trackableID = item.getId();
    }
}
