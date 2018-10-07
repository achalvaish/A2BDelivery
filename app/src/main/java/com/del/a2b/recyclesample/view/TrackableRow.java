package com.del.a2b.recyclesample.view;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.del.a2b.recyclesample.controller.RecyclerViewAdapter;
import com.del.a2b.recyclesample.controller.TrackableManager;
import com.del.a2b.recyclesample.controller.TrackingManager;
import com.del.a2b.recyclesample.model.LastScreen;
import com.del.a2b.recyclesample.model.Trackable;
import com.del.a2b.recyclesample.model.Tracking;
import com.del.a2b.recyclesample.R;
import com.del.a2b.recyclesample.model.LastScreen;
import com.del.a2b.recyclesample.model.RecyclerViewRow;
import com.del.a2b.recyclesample.model.Trackable;

import static android.content.Context.NOTIFICATION_SERVICE;

public class TrackableRow extends CardView implements RecyclerViewRow<Trackable>{
    private TextView mName,mDesc;
    private Button mCategory,mAdd, mSuggestnow;
    private Context context;
    private int trackableID;
    public static int notificationId = 1;

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
        mSuggestnow = (Button) findViewById(R.id.suggestnowbutton);

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

        mSuggestnow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detailsIntent = new Intent(getContext(), AddTrackingActivity.class);
                detailsIntent.putExtra("Intent",1);
                detailsIntent.putExtra("Trackable", trackableID);
                detailsIntent.putExtra("Trackable Name", mName.getText());
                detailsIntent.putExtra("Trackable Description",  mDesc.getText());

                PendingIntent detailsPendingIntent = PendingIntent.getActivity(
                        getContext(),
                        0,
                        detailsIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext())
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Truck Name")
                        .setContentText("Truck Details")
                        .setContentIntent(detailsPendingIntent)
                        .setAutoCancel(true)
                        .addAction(android.R.drawable.ic_menu_compass, "Details", detailsPendingIntent)
                        .addAction(android.R.drawable.ic_menu_directions, "Show Map", detailsPendingIntent);

                // Obtain NotificationManager system service in order to show the notification
                NotificationManager notificationManager = (NotificationManager)getContext().getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(notificationId, mBuilder.build());
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
