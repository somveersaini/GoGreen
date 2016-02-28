package com.galleri5.userfeed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galleri5.userfeed.Activities.PostDetailActivity;
import com.galleri5.userfeed.Model.UserPost;
import com.galleri5.userfeed.R;
import com.galleri5.userfeed.UiHelper.ViewHolders.UserPostItemHolder;

import java.util.ArrayList;

public class UserPostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserPost> list;

    public UserPostRecyclerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(ArrayList<UserPost> list) {
        this.list = list;
    }

    public void clearList() {
        list.clear();
    }

    public void deleteSingleRow(int position) {
        list.remove(position);
    }

    public void addSingleRow(UserPost singleRow) {
        list.add(singleRow);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_user_post, parent, false);
        return UserPostItemHolder.newInstance(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        UserPostItemHolder userPostItemHolder = (UserPostItemHolder) holder;
        userPostItemHolder.setViewHolder(list.get(position));
        userPostItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PostDetailActivity.class).putExtra("position_post", position).putExtra("position_feed", list.get(position).getParentPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
