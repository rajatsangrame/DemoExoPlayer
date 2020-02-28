package com.example.rajat.demo.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Tracks{

	@SerializedName("data")
	private List<Data> data;

	public void setData(List<Data> data){
		this.data = data;
	}

	public List<Data> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Tracks{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}