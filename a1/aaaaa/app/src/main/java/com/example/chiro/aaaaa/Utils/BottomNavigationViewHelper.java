package com.example.chiro.aaaaa.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.example.chiro.aaaaa.Likes.LikeActivity;
import com.example.chiro.aaaaa.Profile.ProfileActivity;
import com.example.chiro.aaaaa.R;
import com.example.chiro.aaaaa.Search.SearchActivity;
import com.example.chiro.aaaaa.Share.ShareActivity;
import com.example.chiro.aaaaa.Home.homePage;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";
    public static void setupBottomNavigationView(BottomNavigationView bottomNavigationView){
        Log.d(TAG, "setupBottomNavigationView: Setting up btoom view helper");
        //bottomNavigationView.enableAni
    }

    public static void enableNavigation(final Context context, BottomNavigationView view){
        view.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_house:
                        Intent intent1 =  new Intent(context, homePage.class);//ACTIVITY NUMBER 0
                        context.startActivity(intent1);

                        break;
                    case R.id.ic_search:
                        Intent intent2 =  new Intent(context, SearchActivity.class);//ACTIVITY NUMBER 1
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_circle:
                        Intent intent3 =  new Intent(context, ShareActivity.class);//ACTIVITY NUMBER 2
                        context.startActivity(intent3);
                        break;
                    case R.id.ic_alert:
                        Intent intent4 =  new Intent(context, LikeActivity.class);//ACTIVITY NUMBER 3
                        context.startActivity(intent4);
                        break;
                    case R.id.ic_android:
                        Intent intent5 =  new Intent(context, ProfileActivity.class);//ACTIVITY NUMBER 4
                        context.startActivity(intent5);
                        break;
                }


            }
        });
    }
}
