package com.galleri5.userfeed.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.galleri5.userfeed.R;
import com.galleri5.userfeed.UiHelper.CircularImageView;
import com.squareup.picasso.Picasso;

public class EndLessAdapter extends PagerAdapter {

    Context context;
    int positionPost, positionFeed, feedSize, feedPostSize;

    TextView userNameTextView, timeAgoView, mutualFriendTextView, titleTextView, noOfFavTextView, commentTextView;
    CircularImageView profilePic;
    String userName, timeAgo, mutualFriends, title, noOfFav, comment, backgroundUrl;

    public EndLessAdapter(Context context, int positionPost, int positionFeed) {
        this.context = context;
        this.positionPost = positionPost;
        this.positionFeed = positionFeed;
        feedSize = UserFeedRecyclerAdapter.list.size();
        feedPostSize = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().size();
    }

    public int getCount() {
        return Integer.MAX_VALUE;
    }

    private int pos = 0;

    public Object instantiateItem(View collection, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.post_detail_view, null);
        ((ViewPager) collection).addView(view, 0);
        initViews(view);
        initVariables();
        setupViews();
        positionPost++;
        if( positionPost > feedPostSize - 1 ) {
            positionPost = 0;
            if( positionFeed > feedSize - 2 ) {
                positionFeed = 0;
            } else {
                positionFeed++;
            }
        }
        return view;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    private void setupViews() {
        userNameTextView.setText(userName);
        timeAgoView.setText(timeAgo);
        mutualFriendTextView.setText(mutualFriends);
        titleTextView.setText(title);
        noOfFavTextView.setText(noOfFav);
        commentTextView.setText(comment);
        Picasso.with(context).load(backgroundUrl).into(profilePic);
    }

    private void initVariables() {

        userName = UserFeedRecyclerAdapter.list.get(positionFeed).getUserName();
        timeAgo = UserFeedRecyclerAdapter.list.get(positionFeed).getTimeAgo();
        mutualFriends = UserFeedRecyclerAdapter.list.get(positionFeed).getMutualFriends();
        title = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getTitle();
        noOfFav = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getFavourites();
        comment = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getNumberOfComment();
        backgroundUrl = UserFeedRecyclerAdapter.list.get(positionFeed).getProfileURL();
    }

    private void initViews(View rootView) {
        userNameTextView = (TextView) rootView.findViewById(R.id.userName);
        timeAgoView = (TextView) rootView.findViewById(R.id.minute_ago);
        mutualFriendTextView = (TextView) rootView.findViewById(R.id.mutual_friends);
        titleTextView = (TextView) rootView.findViewById(R.id.main_title);
        noOfFavTextView = (TextView) rootView.findViewById(R.id.no_of_fav);
        commentTextView = (TextView) rootView.findViewById(R.id.no_of_comment);
        profilePic = (CircularImageView) rootView.findViewById(R.id.profile_pic);
    }

}