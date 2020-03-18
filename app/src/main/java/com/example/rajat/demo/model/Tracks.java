package com.example.rajat.demo.model;

import androidx.annotation.Nullable;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Tracks {

    @SerializedName("data")
    private List<Data> data;

    @SerializedName("id")
    private String trackId;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackId() {
        return trackId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        try {
            Tracks track = (Tracks) obj;
            return this.trackId.equals(track.getTrackId());
        } catch (NullPointerException e) {
            return false;
        }
    }
}