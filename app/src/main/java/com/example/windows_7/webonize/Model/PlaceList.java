package com.example.windows_7.webonize.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by windows-7 on 8/20/2016.
 */
public class PlaceList implements Serializable{

    @SerializedName("status")
    private String status;
    @SerializedName("results")
    private List<Place> results;

    public PlaceList(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Place> getResults() {
        return results;
    }

    public void setResults(List<Place> results) {
        this.results = results;
    }
}
