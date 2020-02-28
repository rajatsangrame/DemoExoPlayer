package com.example.rajat.demo.mgpl.feed;

import androidx.annotation.NonNull;

import org.parceler.Parcel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Parcel(value = Parcel.Serialization.BEAN)
public class Datum {
    boolean alreadyVoted = false;

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("like")
    @Expose
    private String like;
    @SerializedName("cmt")
    @Expose
    private String cmt;
    @SerializedName("sId")
    @Expose
    private String sId;
    @SerializedName("trks")
    @Expose
    private List<Trk> trks = null;
    @SerializedName("share")
    @Expose
    private String shareCount;
    @SerializedName("duration")
    @Expose
    private String duration;

    private String state;
    private String recordId;
    private String userId;
    private String userName = "null";
    //for temp name
    private int position;
    String challengeDescription;
    String challengeName;
    private int challengeRounds;
    private int challengeParticipants;
    private String roundDescription;
    private long leaderBoardExipry;
    private int rewardedAmount;
    private boolean amountRewared;
    private int randomNumber;
    private String meta;

    public long getLeaderBoardExipry() {
        return leaderBoardExipry;
    }

    public void setLeaderBoardExipry(long leaderBoardExipry) {
        this.leaderBoardExipry = leaderBoardExipry;
    }



    public String getChallengeDescription() {
        return challengeDescription;
    }

    public void setChallengeDescription(String challengeDescription) {
        this.challengeDescription = challengeDescription;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public int getChallengeRounds() {
        return challengeRounds;
    }

    public void setChallengeRounds(int challengeRounds) {
        this.challengeRounds = challengeRounds;
    }

    public int getChallengeParticipants() {
        return challengeParticipants;
    }

    public void setChallengeParticipants(int challengeParticipants) {
        this.challengeParticipants = challengeParticipants;
    }

    public String  getRoundDescription() {
        return roundDescription;
    }

    public void setRoundDescription(String roundDescription) {
        this.roundDescription = roundDescription;
    }


// private String alias;

    private String type = "null";

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

/*
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getSId() {
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public List<Trk> getTrks() {
        return trks;
    }

    public void setTrks(List<Trk> trks) {


        this.trks = trks;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean equals(Object obj) {
        if (id.equals(((Datum) obj).getId())) {
            return true;
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return id;
    }

    public void setAlreadyVoted(boolean alreadyVoted) {
        this.alreadyVoted = alreadyVoted;
    }

    public boolean isAlreadyVoted() {
        return alreadyVoted;
    }

    public int getRewardedAmount() {
        return rewardedAmount;
    }

    public void setRewardedAmount(int rewardedAmount) {
        this.rewardedAmount = rewardedAmount;
    }

    public boolean isAmountRewared() {
        return amountRewared;
    }

    public void setAmountRewared(boolean amountRewared) {
        this.amountRewared = amountRewared;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}