package com.galleri5.userfeed.UiHelper.ViewHolders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.galleri5.userfeed.Model.UserPost;
import com.galleri5.userfeed.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class UserPostItemHolder extends RecyclerView.ViewHolder {

    Context context;
    ImageView backgroundImage;
    TextView noOfTimeFav, title, noOfComment;

    private UserPostItemHolder(View parent, Context context, ImageView backgroundImage, TextView noOfComment, TextView title, TextView noOfTimeFav) {
        super(parent);
        this.context = context;
        this.backgroundImage = backgroundImage;
        this.noOfTimeFav = noOfTimeFav;
        this.title = title;
        this.noOfComment = noOfComment;
    }

    public static UserPostItemHolder newInstance(View parent, Context context) {
        ImageView backgroundImage;
        TextView noOfTimeFav, title, noOfComment;
        backgroundImage = (ImageView) parent.findViewById(R.id.background_image);
        noOfTimeFav = (TextView) parent.findViewById(R.id.no_of_time_fav);
        title = (TextView) parent.findViewById(R.id.title_main);
        noOfComment = (TextView) parent.findViewById(R.id.no_of_comment);
        return new UserPostItemHolder(parent, context, backgroundImage, noOfComment, title, noOfTimeFav);
    }

    public void setViewHolder(final UserPost userPost) {

        if( userPost.getBitmap() != null ) {
            backgroundImage.setImageBitmap(userPost.getBitmap());
        } else {
            if( userPost.getBackgroundURL() != null && !userPost.getBackgroundURL().isEmpty() ) {
                Picasso.with(context).load(userPost.getBackgroundURL()).into(new Target() {

                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        backgroundImage.setImageBitmap(bitmap);
                        userPost.setBitmap(bitmap);

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
        title.setText(userPost.getTitle());
        noOfComment.setText(userPost.getNumberOfComment());
        noOfTimeFav.setText(userPost.getFavourites());
    }
}
