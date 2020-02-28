package com.example.rajat.demo.mgpl.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(value = Parcel.Serialization.BEAN)

public class Gf {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("x")
    @Expose
    private Float x;
    @SerializedName("y")
    @Expose
    private Float y;
    @SerializedName("h")
    @Expose
    private Float h;
    @SerializedName("w")
    @Expose
    private Float w;
    @SerializedName("nxt")
    @Expose
    private Integer nxt;
    @SerializedName("txt")
    @Expose
    private String txt;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("fontSize")
    @Expose
    private Float fontSize;

    private Float rotation;
    private Float scale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getH() {
        return h;
    }

    public void setH(Float h) {
        this.h = h;
    }

    public Float getW() {
        return w;
    }

    public void setW(Float w) {
        this.w = w;
    }

    public Integer getNxt() {
        return nxt;
    }

    public void setNxt(Integer nxt) {
        this.nxt = nxt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Gf(String id, Float x, Float y, Float w, Float h, int nxt, String txt) {
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;
        this.nxt = nxt;
        this.txt = txt;
        this.id = id;
    }

    public Gf() {
    }

    public void setRotation(Float rotation) {
        this.rotation = rotation;
    }

    public void setScale(Float scale) {
        this.scale = scale;
    }

    public Float getRotation() {
        return rotation;
    }

    public Float getScale() {
        return scale;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getFontSize() {
        return fontSize;
    }

    public void setFontSize(Float fontSize) {
        this.fontSize = fontSize;
    }
}