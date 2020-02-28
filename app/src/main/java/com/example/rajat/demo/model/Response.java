package com.example.rajat.demo.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("posts")
	private List<Post> posts;

	public void setPosts(List<Post> posts){
		this.posts = posts;
	}

	public List<Post> getPosts(){
		return posts;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"posts = '" + posts + '\'' + 
			"}";
		}
}