package com.example.rajat.demo.model;

import com.google.gson.annotations.SerializedName;

public class Temp{

	@SerializedName("type")
	private int type;

	@SerializedName("font")
	private String font;

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setFont(String font){
		this.font = font;
	}

	public String getFont(){
		return font;
	}

	@Override
 	public String toString(){
		return 
			"Temp{" + 
			"type = '" + type + '\'' + 
			",font = '" + font + '\'' + 
			"}";
		}
}