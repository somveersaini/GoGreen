package com.galleri5.userfeed.Activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.galleri5.userfeed.Adapters.UserFeedRecyclerAdapter;
import com.galleri5.userfeed.R;
import com.galleri5.userfeed.UiHelper.CircularImageView;
import com.galleri5.userfeed.UiHelper.CustomAppCompatActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import uk.co.senab.photoview.PhotoViewAttacher;

public class FullScreenPostActivity extends CustomAppCompatActivity {

    ImageView imageView;
    PhotoViewAttacher mAttacher;
    int positionPost, positionFeed;
    String userName, timeAgo, mutualFriends, title, noOfFav, comment, backgroundUrl;
    boolean isDetailVisible = true;
    LinearLayout footer;
    TextView userNameTextView, timeAgoView, mutualFriendTextView, titleTextView, noOfFavTextView, commentTextView;
    CircularImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_post);
        initViews();
        initVariables();
        setupViews();
        initStatusBar(R.color.dark_transparent);
    }

    private void setupViews() {
        userNameTextView.setText(userName);
        timeAgoView.setText(timeAgo);
        mutualFriendTextView.setText(mutualFriends);
        titleTextView.setText(title);
        noOfFavTextView.setText(noOfFav);
        commentTextView.setText(comment);
        Picasso.with(FullScreenPostActivity.this).load(backgroundUrl).into(profilePic);
    }

    private void initVariables() {
        positionFeed = getIntent().getIntExtra("position_feed", -1);
        positionPost = getIntent().getIntExtra("position_post", -1);
        imageView.setImageBitmap(UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getBitmap());
        userName = UserFeedRecyclerAdapter.list.get(positionFeed).getUserName();
        timeAgo = UserFeedRecyclerAdapter.list.get(positionFeed).getTimeAgo();
        mutualFriends = UserFeedRecyclerAdapter.list.get(positionFeed).getMutualFriends();
        title = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getTitle();
        noOfFav = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getFavourites();
        comment = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getNumberOfComment();
        backgroundUrl = UserFeedRecyclerAdapter.list.get(positionFeed).getProfileURL();
    }

    private void initViews() {
        imageView = (ImageView) findViewById(R.id.post_image);
        mAttacher = new PhotoViewAttacher(imageView);
        userNameTextView = (TextView) findViewById(R.id.userName);
        timeAgoView = (TextView) findViewById(R.id.minute_ago);
        mutualFriendTextView = (TextView) findViewById(R.id.mutual_friends);
        titleTextView = (TextView) findViewById(R.id.main_title);
        noOfFavTextView = (TextView) findViewById(R.id.no_of_fav);
        commentTextView = (TextView) findViewById(R.id.no_of_comment);
        profilePic = (CircularImageView) findViewById(R.id.profile_pic);
        footer = (LinearLayout) findViewById(R.id.footer);
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                isDetailVisible = !isDetailVisible;
                if( isDetailVisible ) {
                    footer.setVisibility(View.VISIBLE);
                } else {
                    footer.setVisibility(View.INVISIBLE);
                }

            }
        });
        initImage(positionPost, positionFeed);
    }

    private void initImage(final int positionPost, final int positionFeed) {
        Bitmap bitmap = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getBitmap();
        if( bitmap != null ) {
            imageView.setImageBitmap(bitmap);
            mAttacher.update();

        } else {
            String imageUrl = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getBackgroundURL();
            if( imageUrl != null && !imageUrl.isEmpty() ) {
                Picasso.with(FullScreenPostActivity.this).load(imageUrl).into(new Target() {

                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).setBitmap(bitmap);
                        imageView.setImageBitmap(bitmap);
                        mAttacher.update();
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
        }
    }

}
