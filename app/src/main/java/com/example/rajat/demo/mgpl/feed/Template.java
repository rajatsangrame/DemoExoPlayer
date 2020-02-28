package com.example.rajat.demo.mgpl.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Sourabh Gupta on 27/12/19.
 */
@Parcel(value = Parcel.Serialization.BEAN)
public class Template {
    //    @SerializedName("color")
//    @Expose
//    private int colorId;
    @SerializedName("font")
    @Expose
    private String font;
    @SerializedName("type")
    @Expose
    private int templateID;

//    public int getColorId() {
//        return colorId;
//    }
//
//    public void setColorId(int colorId) {
//        this.colorId = colorId;
//    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getTemplateID() {
        return templateID;
    }

    public void setTemplateID(int templateID) {
        this.templateID = templateID;
    }
}
