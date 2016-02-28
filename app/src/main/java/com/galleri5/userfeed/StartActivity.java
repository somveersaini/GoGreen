package com.galleri5.userfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.galleri5.userfeed.Activities.MainActivity;
import com.galleri5.userfeed.Activities.PhotoActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void select(View view){
        int id = view.getId();
        switch (id){
            case R.id.view:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.post:
                Intent intent1 = new Intent(this, PhotoActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
