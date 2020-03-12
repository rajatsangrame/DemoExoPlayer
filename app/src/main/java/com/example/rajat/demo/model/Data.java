package com.example.rajat.demo.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data {

	@SerializedName("temp")
	private Temp temp;

	@SerializedName("gfs")
	private List<Gfs> gfs;

	@SerializedName("pos")
	private int pos;

	@SerializedName("typ")
	private String typ;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("tracks")
	private Tracks tracks;

	public void setTemp(Temp temp){
		this.temp = temp;
	}

	public Temp getTemp(){
		return temp;
	}

	public void setGfs(List<Gfs> gfs){
		this.gfs = gfs;
	}

	public List<Gfs> getGfs(){
		return gfs;
	}

	public void setPos(int pos){
		this.pos = pos;
	}

	public int getPos(){
		return pos;
	}

	public void setTyp(String typ){
		this.typ = typ;
	}

	public String getTyp(){
		return typ;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public Tracks getTracks() {
		return tracks;
	}

	public void setTracks(Tracks tracks) {
		this.tracks = tracks;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"temp = '" + temp + '\'' + 
			",gfs = '" + gfs + '\'' + 
			",pos = '" + pos + '\'' + 
			",typ = '" + typ + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}