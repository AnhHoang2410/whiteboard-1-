package com.example.chiro.aaaaa.Share;

import android.app.Activity;
import android.os.Bundle;

import com.example.chiro.aaaaa.R;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_camera);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, VideoRecord.newInstance())
                    .commit();
        }
    }

}