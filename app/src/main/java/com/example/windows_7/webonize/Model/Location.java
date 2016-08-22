package com.example.windows_7.webonize.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows-7 on 8/20/2016.
 */
public class Location implements Serializable{

    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;

    public Location(){}

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
