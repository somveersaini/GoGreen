package com.galleri5.userfeed.UiHelper;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.galleri5.userfeed.R;

public class CustomAppCompatActivity extends AppCompatActivity {

    protected static final int DEFAULT_STATUS_BAR_COLOR = R.color.primary_dark;
    protected static final String DEFAULT_TOOLBAR_FONT = "ProximaNova-Semibold.otf";
    protected static final String PACIFICO = "Pacifico.ttf";
    private Toolbar toolbar;

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void initToolbar(String title) {

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if( getSupportActionBar() != null ) {
            getSupportActionBar().setTitle(title);
        }
    }

    protected void initStatusBar(int colorResource) {

        if( android.os.Build.VERSION.SDK_INT >= 21 ) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(colorResource));
        }
    }

    protected void initStatusBar() {

        if( android.os.Build.VERSION.SDK_INT >= 21 ) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(DEFAULT_STATUS_BAR_COLOR));
        }
    }

}
