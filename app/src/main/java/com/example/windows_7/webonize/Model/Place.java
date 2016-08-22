package com.example.windows_7.webonize.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by windows-7 on 8/20/2016.
 */
public class Place implements Serializable{

    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("icon")
    private String icon;
    @SerializedName("vicinity")
    private String vicinity;
    @SerializedName("geometry")
    private Geometry geometry;
    @SerializedName("place_id")
    private String place_id;

    public Place(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String toString(){

        return "name: "+name+" id: "+id+" vicinity: "+vicinity+" lat: "+getGeometry().getLocation().getLat()+"" +
                " lng"+getGeometry().getLocation().getLng();
    }
}
