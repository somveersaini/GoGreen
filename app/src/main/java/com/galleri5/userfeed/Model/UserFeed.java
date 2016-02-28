package com.galleri5.userfeed.Model;

import java.util.ArrayList;


public class UserFeed {
    private String userName;
    String profileURL;
    private ArrayList<UserPost> userPosts;
    private int overallXScroll = 0;
    private int mutualFriends;
    private int timeAgo;


    public UserFeed(String userName, String profileURL, int mutualFriends, int timeAgo, ArrayList<UserPost> userPosts) {
        this.userPosts = userPosts;
        this.userName = userName;
        this.profileURL = profileURL;
        this.mutualFriends = mutualFriends;
        this.timeAgo = timeAgo;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public String getTimeAgo() {
        return timeAgo + " minute ago";
    }

    public String getMutualFriends() {
        return mutualFriends + " contribution";
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<UserPost> getUserPosts() {
        return userPosts;
    }

    public int getOverallXScroll() {
        return overallXScroll;
    }

    public void setOverallXScroll(int overallXScroll) {
        this.overallXScroll = overallXScroll;
    }

}
