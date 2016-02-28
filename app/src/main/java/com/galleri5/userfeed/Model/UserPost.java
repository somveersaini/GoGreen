package com.galleri5.userfeed.Model;

import android.graphics.Bitmap;
import android.os.Parcel;


public class UserPost {
    String backgroundURL;
    String title;
    int favourites;
    int numberOfComment;
    Bitmap bitmap = null;
    int parentPosition;
    boolean isFav = false;

    public UserPost(String backgroundURL, String title, int favourites, int numberOfComment, int parentPosition) {
        this.backgroundURL = backgroundURL;
        this.title = title;
        this.favourites = favourites;
        this.numberOfComment = numberOfComment;
        this.parentPosition = parentPosition;
    }

    public void setIsFav(boolean isFav) {
        this.isFav = isFav;
    }

    public boolean getIsFav() {
        return this.isFav;
    }

    public int getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    protected UserPost(Parcel in) {
        backgroundURL = in.readString();
        title = in.readString();
        favourites = in.readInt();
        numberOfComment = in.readInt();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    public String getBackgroundURL() {
        return backgroundURL;
    }

    public String getTitle() {
        return title;
    }

    public String getFavourites() {
        return favourites + "  |";
    }

    public String getNumberOfComment() {
        return Integer.toString(numberOfComment);
    }
}
