package com.del.a2b.recyclesample.view;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.del.a2b.recyclesample.R;
import com.del.a2b.recyclesample.model.Trackable;

public class DetailsActivity extends AppCompatActivity {


    public static int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Log.d("Custom ID", "Truck ID:" +
                getIntent().getIntExtra("EXTRA_DETAILS_ID", -1));

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(TrackableActivity.notificationId);
    }
}
