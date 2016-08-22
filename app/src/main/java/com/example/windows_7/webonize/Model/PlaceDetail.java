package com.example.windows_7.webonize.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by windows-7 on 8/21/2016.
 */
public class PlaceDetail implements Serializable{


    @SerializedName("name")
    private String name;
    @SerializedName("place_id")
    private String place_id;
    @SerializedName("photos")
    private List<Photos> photos;


    public PlaceDetail(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }
}
