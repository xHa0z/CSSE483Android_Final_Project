package edu.rosehulman.weny.comewithme.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by xuez on 2/8/16.
 */
public class Owner {
    @JsonIgnore
    private String key;

    private String username;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
