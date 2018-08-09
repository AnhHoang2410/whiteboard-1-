package com.example.chiro.aaaaa.Home;


import android.content.Context;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chiro.aaaaa.MainActivity;
import com.example.chiro.aaaaa.R;
import com.example.chiro.aaaaa.Utils.BottomNavigationViewHelper;
import com.example.chiro.aaaaa.Utils.SectionsPagerAdapter;
import com.example.chiro.aaaaa.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class homePage extends MainActivity {


    private static final String TAG = "homePage";
    private static final int ACTIVITY_NUM=0;

    private Context mContext = homePage.this;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            Log.d(TAG, "onCreate: starting");
        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();
        }
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    /**
     * Responsible for adding the tob fragment
     */
     private void setupViewPager(){
            SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
            //adapter.addFragment(new CameraFragments()); // index 0
            adapter.addFragment(new HomeFragments());// index 1
            adapter.addFragment(new MessagesFragments());// index 2
            ViewPager viewPager =  (ViewPager) findViewById(R.id.container);
            viewPager.setAdapter(adapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_logo);
            //tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);




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



    /*
        private Uri videouri;
        private static final int REQUEST_CODE = 101;
        private StorageReference videoref;
        private StorageReference mStorageRef;
    */
    // mStorageRef = FirebaseStorage.getInstance().getReference();

    //videoref =mStorageRef.child("/videos" + "/userIntro.3gp");
    /*
        public void upload(View view) {
            if (videouri != null) {
                UploadTask uploadTask = videoref.putFile(videouri);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(homePage.this,
                                "Upload failed: " + e.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();

                    }
                }).addOnSuccessListener(
                        new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(homePage.this, "Upload complete",
                                        Toast.LENGTH_LONG).show();
                            }
                        }).addOnProgressListener(
                        new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                updateProgress(taskSnapshot);

                            }
                        });
            } else {
                Toast.makeText(homePage.this, "Nothing to upload",
                        Toast.LENGTH_LONG).show();
            }
        }

        public void updateProgress(UploadTask.TaskSnapshot taskSnapshot) {

            @SuppressWarnings("VisibleForTests") long fileSize =
                    taskSnapshot.getTotalByteCount();

            @SuppressWarnings("VisibleForTests")
            long uploadBytes = taskSnapshot.getBytesTransferred();

            long progress = (100 * uploadBytes) / fileSize;

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.pbar);
            progressBar.setProgress((int) progress);
        }

        public void record(View view) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE);
        }


        @Override
        protected void onActivityResult(int requestCode,
                                        int resultCode, Intent data) {
            videouri = data.getData();
            if (requestCode == REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Video saved to:\n" +
                            videouri, Toast.LENGTH_LONG).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Video recording cancelled.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Failed to record video",
                            Toast.LENGTH_LONG).show();
                }
            }
        }*/
