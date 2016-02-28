package com.galleri5.userfeed.UiHelper.ViewHolders;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.galleri5.userfeed.Adapters.UserPostRecyclerAdapter;
import com.galleri5.userfeed.Model.UserFeed;
import com.galleri5.userfeed.R;
import com.galleri5.userfeed.UiHelper.CircularImageView;
import com.galleri5.userfeed.UiHelper.SnappyLinearLayoutManager;
import com.galleri5.userfeed.UiHelper.SnappyRecyclerView;
import com.squareup.picasso.Picasso;

public class UserFeedItemViewHolder extends RecyclerView.ViewHolder {
    TextView userName, time, mutualFriends;
    CircularImageView profilePic;
    SnappyRecyclerView recyclerView;
    Context context;
    UserPostRecyclerAdapter userPostRecyclerAdapter;

    private UserFeedItemViewHolder(View parent, Context context, TextView userName, TextView time, TextView mutualFriends, CircularImageView profilePic, SnappyRecyclerView recyclerView) {
        super(parent);
        this.userName = userName;
        this.recyclerView = recyclerView;
        this.context = context;
        this.time = time;
        this.mutualFriends = mutualFriends;
        this.profilePic = profilePic;
    }

    public static UserFeedItemViewHolder newInstance(View parent, Context context) {
        TextView userName, time, mutualFriends;
        CircularImageView profilePic;
        SnappyRecyclerView recyclerView;
        time = (TextView) parent.findViewById(R.id.time_ago);
        mutualFriends = (TextView) parent.findViewById(R.id.mutual_friends);
        profilePic = (CircularImageView) parent.findViewById(R.id.profile_pic);
        userName = (TextView) parent.findViewById(R.id.userName);
        recyclerView = (SnappyRecyclerView) parent.findViewById(R.id.recyclerView);
        return new UserFeedItemViewHolder(parent, context, userName, time, mutualFriends, profilePic, recyclerView);
    }

    public void setViewHolder(UserFeed userFeed) {
        userName.setText(userFeed.getUserName());
        if( userFeed.getProfileURL() != null && !userFeed.getProfileURL().isEmpty() ) {
            Picasso.with(context).load(userFeed.getProfileURL()).into(profilePic);
        }
        time.setText(userFeed.getTimeAgo());
        mutualFriends.setText(userFeed.getMutualFriends());
        setupRecyclerView(userFeed);

    }

    private void setupRecyclerView(final UserFeed userPosts) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setClickable(false);
        final SnappyLinearLayoutManager snappyLinearLayoutManager = new SnappyLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        snappyLinearLayoutManager.offsetChildrenHorizontal(userPosts.getOverallXScroll());
        snappyLinearLayoutManager.scrollToPosition(userPosts.getOverallXScroll());
        recyclerView.setLayoutManager(snappyLinearLayoutManager);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                userPosts.setOverallXScroll(snappyLinearLayoutManager.getFixScrollPos());
            }
        });
        userPostRecyclerAdapter = new UserPostRecyclerAdapter(context);
        userPostRecyclerAdapter.setList(userPosts.getUserPosts());
        recyclerView.setAdapter(userPostRecyclerAdapter);
    }

}
