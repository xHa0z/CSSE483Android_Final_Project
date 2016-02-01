package edu.rosehulman.weny.comewithme;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sophiawen on 2/1/16.
 */
public class create_event {
    private String eventTitle;
    private Date time;
    private Location location;
    private ArrayList<String> friends = new ArrayList<>();

    public create_event(String title, Date time, Location location, ArrayList<String> friends){
        this.eventTitle = title;
        this.time = time;
        this.location = location;
        this.friends = friends;
    }

    public String getEvent(){
        return eventTitle;
    }

    public Date getDate(){
        return time;
    }

    public Location getLocation(){
        return location;
    }

    public void setEventTitle(String eventTitle){
        this.eventTitle = eventTitle;
    }

    public void setTime(Date time){
        this.time = time;
    }

    public void setLocation(Location location){
        this.location = location;
    }

}
