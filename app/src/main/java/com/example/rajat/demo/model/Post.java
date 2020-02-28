package com.example.rajat.demo.model;

import com.google.gson.annotations.SerializedName;

public class Post {

	@SerializedName("comments")
	private int comments;

	@SerializedName("challengeDescription")
	private String challengeDescription;

	@SerializedName("roundDescription")
	private String roundDescription;

	@SerializedName("message")
	private String message;

	@SerializedName("type")
	private String type;

	@SerializedName("userId")
	private String userId;

	@SerializedName("tracks")
	private Tracks tracks;

	@SerializedName("challengeExpiry")
	private String challengeExpiry;

	@SerializedName("challengeParticipants")
	private int challengeParticipants;

	@SerializedName("roundExpiry")
	private String roundExpiry;

	@SerializedName("challengeName")
	private String challengeName;

	@SerializedName("id")
	private String id;

	@SerializedName("state")
	private String state;

	@SerializedName("recoID")
	private String recoID;

	@SerializedName("challengeRounds")
	private int challengeRounds;

	@SerializedName("likes")
	private int likes;

	@SerializedName("username")
	private String username;

	public void setComments(int comments){
		this.comments = comments;
	}

	public int getComments(){
		return comments;
	}

	public void setChallengeDescription(String challengeDescription){
		this.challengeDescription = challengeDescription;
	}

	public String getChallengeDescription(){
		return challengeDescription;
	}

	public void setRoundDescription(String roundDescription){
		this.roundDescription = roundDescription;
	}

	public String getRoundDescription(){
		return roundDescription;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setTracks(Tracks tracks){
		this.tracks = tracks;
	}

	public Tracks getTracks(){
		return tracks;
	}

	public void setChallengeExpiry(String challengeExpiry){
		this.challengeExpiry = challengeExpiry;
	}

	public String getChallengeExpiry(){
		return challengeExpiry;
	}

	public void setChallengeParticipants(int challengeParticipants){
		this.challengeParticipants = challengeParticipants;
	}

	public int getChallengeParticipants(){
		return challengeParticipants;
	}

	public void setRoundExpiry(String roundExpiry){
		this.roundExpiry = roundExpiry;
	}

	public String getRoundExpiry(){
		return roundExpiry;
	}

	public void setChallengeName(String challengeName){
		this.challengeName = challengeName;
	}

	public String getChallengeName(){
		return challengeName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setRecoID(String recoID){
		this.recoID = recoID;
	}

	public String getRecoID(){
		return recoID;
	}

	public void setChallengeRounds(int challengeRounds){
		this.challengeRounds = challengeRounds;
	}

	public int getChallengeRounds(){
		return challengeRounds;
	}

	public void setLikes(int likes){
		this.likes = likes;
	}

	public int getLikes(){
		return likes;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"PostsItem{" + 
			"comments = '" + comments + '\'' + 
			",challengeDescription = '" + challengeDescription + '\'' + 
			",roundDescription = '" + roundDescription + '\'' + 
			",message = '" + message + '\'' + 
			",type = '" + type + '\'' + 
			",userId = '" + userId + '\'' + 
			",tracks = '" + tracks + '\'' + 
			",challengeExpiry = '" + challengeExpiry + '\'' + 
			",challengeParticipants = '" + challengeParticipants + '\'' + 
			",roundExpiry = '" + roundExpiry + '\'' + 
			",challengeName = '" + challengeName + '\'' + 
			",id = '" + id + '\'' + 
			",state = '" + state + '\'' + 
			",recoID = '" + recoID + '\'' + 
			",challengeRounds = '" + challengeRounds + '\'' + 
			",likes = '" + likes + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}