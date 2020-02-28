package com.example.rajat.demo.mgpl.feed;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(value = Parcel.Serialization.BEAN)

public class Trk {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("typ")
    @Expose
    private String typ;
    @SerializedName("pos")
    @Expose
    private Integer pos;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("gfs")
    @Expose
    private List<Gf> gfs = null;
    @SerializedName("temp")
    @Expose
    private Template template = null;

    @SerializedName("postId")
    @Expose
    private String postId;

    @SerializedName("username")
    @Expose
    private String username;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Gf> getGfs() {
        return gfs;
    }

    public void setGfs(List<Gf> gfs) {
        this.gfs = gfs;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Template getTemplate() {
        return template;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}