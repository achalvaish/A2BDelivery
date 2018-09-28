package com.del.a2b.recyclesample.controller;

import android.content.Context;

import com.del.a2b.recyclesample.model.Tracking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrackingManager {

    static ArrayList<Tracking> trackingListName= new ArrayList<>();
    private Context context;
    private TrackingService trackingService;

    public TrackingManager(Context ctx) {
        context = ctx;
        trackingService = TrackingService.getSingletonInstance(context);
    }


    public void createTracking(int trackableID, String title, Date startTime, Date endTime, Date meetTime, String currentLocation, String meetLocation) {
        String ID = "Tracking_" + trackableID + meetTime.getTime();
        Tracking tracking = new Tracking(ID, trackableID, title, startTime, endTime, meetTime, currentLocation, meetLocation);
        trackingListName.add(tracking);
    }

    public void updateTracking(String ID,String meetTitle,Date meetTime,String location)
    {
        for(Tracking tracking:trackingListName)
        {
            if(tracking.getId().equals(ID))
            {
                tracking.setTitle(meetTitle);
                tracking.setMeetTime(meetTime);
                tracking.setMeetLocation(location);
            }
        }

    }

    public List<Date> getNearestTime(int id,Date meetTime) {
        List<Date> trackingEventDates = new ArrayList<>();
        for (TrackingService.TrackingInfo trackingInfo : trackingService.getTrackingInfoForTimeRange(meetTime,5,0)) {
            if (trackingInfo.trackableId == id && trackingInfo.stopTime > 0) {
                Calendar calendar=Calendar.getInstance();

                calendar.setTime(trackingInfo.date);
                trackingEventDates.add(trackingInfo.date);

                calendar.add(Calendar.MINUTE,trackingInfo.stopTime);
                trackingEventDates.add(calendar.getTime());
                break;
            }
        }

        return trackingEventDates;
    }

    public List<Date> getAvailableTimes(int id) {
        List<Date> availableTime = new ArrayList<>();
        Calendar sourceCal =Calendar.getInstance();
        Calendar targetCal=Calendar.getInstance();

        for (TrackingService.TrackingInfo trackingInfo : TrackingService.getSingletonInstance(context).getTrackingList()) {
            if (trackingInfo.trackableId == id && trackingInfo.stopTime > 0) {

                sourceCal.setTime(trackingInfo.date);
                targetCal.setTime(trackingInfo.date);

                sourceCal.add(Calendar.MINUTE,trackingInfo.stopTime);

                while(targetCal.compareTo(sourceCal)<=0) {
                    availableTime.add(targetCal.getTime());
                    targetCal.add(Calendar.MINUTE,2);
                }
            }
        }

        return availableTime;
    }

    public List<String> getAvailableLocation(int id,Date meetTime)
    {
        List<String> availableLocation = new ArrayList<>();
        String location;

        for(TrackingService.TrackingInfo trackingInfo: trackingService.getTrackingInfoForTimeRange(meetTime,5,0) )
        {
            if(trackingInfo.trackableId == id && trackingInfo.stopTime>0) {
                location = trackingInfo.latitude + ", " + trackingInfo.longitude;
                availableLocation.add(location);
            }
        }

        return availableLocation;
    }


    public String getCurrentLocation(int id)
    {
        String defaultLocation="Invalid location";
        Date previousTime = Calendar.getInstance().getTime();
        for (TrackingService.TrackingInfo trackingInfo : TrackingService.getSingletonInstance(context).getTrackingList()) {
            if(trackingInfo.trackableId == id)
            {
                Calendar currentTime = Calendar.getInstance();
                if(currentTime.getTime().compareTo(previousTime)>=0 && currentTime.getTime().compareTo(trackingInfo.date)<=0)
                {
                    return trackingInfo.latitude+", "+trackingInfo.longitude;
                }
                previousTime = currentTime.getTime();
            }
        }
        return defaultLocation;
    }
    public static  List<Tracking> getTrackingListName() {
        return trackingListName;
    }
    public static void deleteTracking(String trackingID)
    {
        for(Tracking tracking:trackingListName) {
            if(tracking.getId().equals(trackingID)) {
                trackingListName.remove(tracking);
                break;
            }
        }
    }
}
