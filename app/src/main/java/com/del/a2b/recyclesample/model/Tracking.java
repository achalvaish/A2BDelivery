package com.del.a2b.recyclesample.model;

import java.util.Date;

public class Tracking {
    private String Id;
    private int trackableId;
    private String title;
    private Date startTime;
    private Date endTime;
    private Date meetTime;
    private String currentLocation;
    private String meetLocation;

    public Tracking(String ID,int trackableId, String title, Date startTime, Date endTime, Date meetTime, String currentLocation, String meetLocation) {
        this.Id= ID;
        this.trackableId = trackableId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetTime = meetTime;
        this.currentLocation = currentLocation;
        this.meetLocation = meetLocation;
    }

    public Tracking()
    {

    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getTrackableId() {
        return trackableId;
    }

    public void setTrackableId(int trackableId) {
        this.trackableId = trackableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {

        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(Date meetTime) {
        this.meetTime = meetTime;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getMeetLocation() {
        return meetLocation;
    }

    public void setMeetLocation(String meetLocation) {
        this.meetLocation = meetLocation;
    }
}
