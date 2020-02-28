package com.example.rajat.demo.mgpl.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feed {

@SerializedName("Bettr")
@Expose
private Bettr data;

public Bettr getBettr() {
return data;
}

public void setBettr(Bettr data) {
this.data = data;
}

}