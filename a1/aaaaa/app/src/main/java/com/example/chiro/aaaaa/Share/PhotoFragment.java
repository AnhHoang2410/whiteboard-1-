package com.example.chiro.aaaaa.Share;

import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chiro.aaaaa.R;

import com.example.chiro.aaaaa.Utils.FirebaseMethods;
import com.example.chiro.aaaaa.Utils.Permissions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class PhotoFragment extends Fragment {
    private static final String TAG = "PhotoFragment";

    //constant
    private static final int PHOTO_FRAGMENT_NUM = 1;
    private static final int GALLERY_FRAGMENT_NUM = 2;
    private static final int  CAMERA_REQUEST_CODE = 5;

    private Uri videouri;
    private static final int REQUEST_CODE = 101;
    private StorageReference videoref;
    private int contentView;
    private int videoCount = 0;

    String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(new Date());


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_photo);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        videoref =storageRef.child("/videos" +uid+ "/"+ timeStamp);

    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        Log.d(TAG, "onCreateView: started.");

        Button btnUpload = (Button) view.findViewById(R.id.btnUpload);
        Button btnLaunchCamera = (Button) view.findViewById(R.id.btnLaunchCamera);


        btnLaunchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: launching camera.");

                if(((ShareActivity)getActivity()).getCurrentTabNumber() == PHOTO_FRAGMENT_NUM){
                    if(((ShareActivity)getActivity()).checkPermissions(Permissions.CAMERA_PERMISSION[0])&&
                    ((ShareActivity)getActivity()).checkPermissions(Permissions.CAMERA_PERMISSION[1])){

                        Log.d(TAG, "onClick: starting camera");
                        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0 );
                        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                    }else{
                        Intent intent = new Intent(getActivity(), ShareActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videouri != null) {
                    UploadTask uploadTask = videoref.putFile(videouri);

                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),
                                    "Upload failed: " + e.getLocalizedMessage(),
                                    Toast.LENGTH_LONG).show();

                        }
                    }).addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(getActivity(), "Upload complete",
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
                    Toast.makeText(getActivity(), "Nothing to upload",
                            Toast.LENGTH_LONG).show();
                }
            }

            });

        return view;
    }


    public void updateProgress(UploadTask.TaskSnapshot taskSnapshot) {

        @SuppressWarnings("VisibleForTests") long fileSize =
                taskSnapshot.getTotalByteCount();

        @SuppressWarnings("VisibleForTests")
        long uploadBytes = taskSnapshot.getBytesTransferred();

        long progress = (100 * uploadBytes) / fileSize;

        ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.pbar);
        progressBar.setProgress((int) progress);
    }

    @Override
    public void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        videouri = data.getData();
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getActivity(), "Video saved to:\n" +
                        videouri, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getActivity(), "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setContentView(int contentView) {
        this.contentView = contentView;
    }
}