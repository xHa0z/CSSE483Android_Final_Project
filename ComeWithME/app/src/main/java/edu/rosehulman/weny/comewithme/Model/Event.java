package edu.rosehulman.weny.comewithme.Model;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xuez on 2/8/16.
 */
public class Event {
    private String title;
    private Date time;
    private String location;
    private ArrayList<Owner> attendee;
    private String username;

    public Event(){

    }

    public Event(String title, Date time, String location, ArrayList<Owner> attendee, String username){
        this.title = title;
        this.time = time;
        this.location = location;
        this.attendee = new ArrayList<Owner>();
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Owner> getAttendee() {
        return attendee;
    }

    public void setAttendee(ArrayList<Owner> attendee) {
        this.attendee = attendee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
