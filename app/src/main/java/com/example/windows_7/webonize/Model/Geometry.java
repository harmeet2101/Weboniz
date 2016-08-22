package com.example.windows_7.webonize.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows-7 on 8/20/2016.
 */
public class Geometry implements Serializable{

    @SerializedName("location")
    private Location location;

    public Geometry(){}

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
