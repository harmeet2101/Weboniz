package com.example.windows_7.webonize.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by windows-7 on 8/21/2016.
 */
public class PlaceDetails implements Serializable{

    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private PlaceDetail result;

    public PlaceDetails(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PlaceDetail getResult() {
        return result;
    }

    public void setResults(PlaceDetail result) {
        this.result = result;
    }
}
