package com.galleri5.userfeed.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.galleri5.userfeed.Adapters.EndLessAdapter;
import com.galleri5.userfeed.Adapters.UserFeedRecyclerAdapter;
import com.galleri5.userfeed.R;
import com.galleri5.userfeed.UiHelper.ViewPageTransformer;
import com.klinker.android.sliding.SlidingActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PostDetailActivity extends SlidingActivity {

    ViewPager viewPager;
    int positionPost, positionFeed, feedSize, feedPostSize, backwardOffsetSize = 0;
    int prevPosition = 0;

    @Override
    public void init(Bundle savedInstanceState) {
        setContent(R.layout.activity_post_detail);
        initViews();
        initVariables();
        setPrimaryColors(
                getResources().getColor(R.color.primary),
                getResources().getColor(R.color.primary_dark)
        );
        enableFullscreen();
        setTitle("Image");
        setFab(
                getPrimaryDark(),
                R.drawable.fav_unselected,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isFav = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getIsFav();
                        isFav = !isFav;
                        UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).setIsFav(isFav);
                        setFab();
                    }
                }
        );
        setFab();
        initViewPager();
    }

    public void setFab() {
        boolean isFav = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getIsFav();
        if( isFav ) {
            getFab().setImageDrawable(getResources().getDrawable(R.drawable.fav_selected));
        } else {
            getFab().setImageDrawable(getResources().getDrawable(R.drawable.fav_unselected));
        }
    }

    private void initVariables() {
        positionFeed = getIntent().getIntExtra("position_feed", -1);
        positionPost = getIntent().getIntExtra("position_post", -1);
        feedSize = UserFeedRecyclerAdapter.list.size();
        feedPostSize = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().size();
        int currentPositionOfPost = positionPost;
        for ( int i = positionFeed; i >= 0; i-- ) {

            for ( int j = currentPositionOfPost; j >= 0; j-- ) {
                backwardOffsetSize++;
            }
            currentPositionOfPost = UserFeedRecyclerAdapter.list.get(i).getUserPosts().size();

        }
        initImage(positionPost, positionFeed);

    }

    private void initViews() {

        getClickableImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostDetailActivity.this, FullScreenPostActivity.class).putExtra("position_post", positionPost).putExtra("position_feed", positionFeed));
            }
        });
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.frame);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setPageTransformer(true, new ViewPageTransformer(ViewPageTransformer.TransformType.ZOOM));
        viewPager.setAdapter(new EndLessAdapter(this, positionPost, positionFeed));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int currentPosition) {
                if( prevPosition <= currentPosition ) {
                    positionPost++;
                    if( positionPost > feedPostSize - 1 ) {
                        positionPost = 0;
                        if( positionFeed > feedSize - 2 ) {
                            positionFeed = 0;
                        } else {
                            positionFeed++;
                        }
                    }
                } else {
                    positionPost--;
                    if( positionPost < 0 ) {
                        positionPost = feedPostSize - 1;
                        positionFeed--;
                        if( positionFeed < 0 ) {
                            positionFeed = feedSize - 1;
                        }
                    }
                }
                prevPosition = currentPosition;


                initImage(positionPost, positionFeed);
                setFab();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initImage(final int positionPost, final int positionFeed) {
        Bitmap bitmap = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getBitmap();
        if( bitmap != null ) {
            setImage(bitmap);
            setPrimaryColors(
                    getPrimary(),
                    getPrimaryDark());
            setFabColor(getPrimaryDark());
        } else {
            String imageUrl = UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).getBackgroundURL();
            if( imageUrl != null && !imageUrl.isEmpty() ) {
                Picasso.with(PostDetailActivity.this).load(imageUrl).into(new Target() {

                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        setImage(bitmap);
                        UserFeedRecyclerAdapter.list.get(positionFeed).getUserPosts().get(positionPost).setBitmap(bitmap);
                        setPrimaryColors(
                                getPrimary(),
                                getPrimaryDark());
                        setFabColor(getPrimary());

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        //setImage(null);

                    }
                });
            }
        }
    }


//    private void initCollapsingToolbar() {
//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("Title");
//        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
//        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.primary));
//        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.primary_dark));
//    }

    private void initStatusBar() {

        if( android.os.Build.VERSION.SDK_INT >= 21 ) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.dark_transparent));

        }
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Image");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if( id == R.id.action_settings ) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
