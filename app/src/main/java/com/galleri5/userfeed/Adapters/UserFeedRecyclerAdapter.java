package com.galleri5.userfeed.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galleri5.userfeed.Model.UserFeed;
import com.galleri5.userfeed.R;
import com.galleri5.userfeed.UiHelper.ViewHolders.UserFeedItemViewHolder;

import java.util.ArrayList;

public class UserFeedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    public static ArrayList<UserFeed> list;

    public void clearList() {
        list.clear();
    }

    public void deleteSingleRow(int position) {
        list.remove(position);
    }

    public void addSingleRow(UserFeed singleRow) {
        list.add(singleRow);

    }

    public UserFeedRecyclerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void notifyItemPostChange(int positionFeed) {
        notifyItemChanged(positionFeed);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_user_feed, viewGroup, false);
        return UserFeedItemViewHolder.newInstance(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        UserFeedItemViewHolder userFeedItemViewHolder = (UserFeedItemViewHolder) viewHolder;
        userFeedItemViewHolder.setViewHolder(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
