package com.example.chiro.aaaaa.Likes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chiro.aaaaa.R;
import com.example.chiro.aaaaa.Utils.BottomNavigationViewHelper;

public class LikeActivity extends AppCompatActivity{
    private static final String TAG = "LikeActivity";
    private Context mContext = LikeActivity.this;
    private static final int ACTIVITY_NUM=3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: started search");
        setupBottomNavigationView();
    }

    /**
     * Bottom Navigation View setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up bottom navigation view");
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationView);
        Menu menu= bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }


}

