package com.galleri5.userfeed.Activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.galleri5.userfeed.Adapters.UserFeedRecyclerAdapter;
import com.galleri5.userfeed.Model.UserFeed;
import com.galleri5.userfeed.Model.UserPost;
import com.galleri5.userfeed.R;
import com.galleri5.userfeed.UiHelper.CustomAppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends CustomAppCompatActivity {

    RecyclerView recyclerView;
    UserFeedRecyclerAdapter userFeedRecyclerAdapter;
    String[] beautifulImagese = {"http://www.thehindu.com/multimedia/dynamic/02252/CB22_CLEAN_INDIA_C_2252875f.jpg", "http://blog.indiaproperty.com/wp-content/uploads/2016/02/WNC_personnel_cleaning_the_premises_of_the_Gateway_of_India_on_World_Environment_Day_2015.jpg", "https://s-media-cache-ak0.pinimg.com/736x/0e/0e/44/0e0e4425b3b68a7c24f85a39cfe75191.jpg", "http://greenweddingshoes.com/wp-content/uploads/2014/09/craterlake-elopement-30.jpg", "http://files.askmeontravel.com/Beautiful%20Tirchi.JPG", "http://timesofindia.indiatimes.com/thumb/msid-48407751,width-400,resizemode-4/48407751.jpg", "http://www.igovernment.in/sites/default/files/styles/article_image/public/Clean-up-drive-government-offices_1.jpg?itok=F8ww1MSg", "http://www.24x7newsonline.com/public/nhrd/UserFiles/swachh%20Bharat%20Abhiyaan%203%20.png", "http://resize.outlookindia.com/Uploads/outlookindia/2014/20141013/page_42_20141013.jpg.ashx?quality=40&width=1000", "https://santoshchaubey.files.wordpress.com/2014/10/cleaning-1.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar("My City");
        initStatusBar();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setClickable(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        userFeedRecyclerAdapter = new UserFeedRecyclerAdapter(MainActivity.this);
        ArrayList<String> listImages = new ArrayList<>(Arrays.asList(beautifulImagese));
        for ( int i = 0; i < 10; i++ ) {
            ArrayList<UserPost> userPosts = new ArrayList<>();
            Collections.shuffle(listImages);
            for ( int j = 0; j < listImages.size(); j++ ) {
                userPosts.add(new UserPost(listImages.get(j), "Beauty at best", (i + 1) * j * j * 23 + 120, j * 14 * (i + 1) + 92, i));
            }
            userFeedRecyclerAdapter.addSingleRow(new UserFeed("Common Man", "https://qph.is.quoracdn.net/main-thumb-58927306-200-pfnltqlqonxakyubpxylogurtuybcxvh.jpeg", i * 4, i * 5, userPosts));
        }
        recyclerView.setAdapter(userFeedRecyclerAdapter);
    }
}
