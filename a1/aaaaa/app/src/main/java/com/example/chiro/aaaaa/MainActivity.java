package com.example.chiro.aaaaa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chiro.aaaaa.Home.homePage;
import com.example.chiro.aaaaa.Login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmail, mPassword;
    private Button btnSignIn, btnSignUp;

    public void showUserList() {
        Intent intent1 = new Intent(getApplicationContext(), homePage.class);
        startActivity(intent1);
    }

    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * -------------------Setup Firebase------------------------------------------
     */
    /**
     * check to see if the @param user is login
     * @param user
     */
    private  void checkCurrentUser(FirebaseUser user){
        Log.d(TAG, "checkCurrentUser:  chekcing if user is logged in");
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

            startActivity(intent);
        }

    }
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebaseAuth");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            //check if the user is logged in
            checkCurrentUser(user);
            if (user != null) {
                Log.d(TAG, "onAuthStateChanged: signed_in:" + user.getUid());
                //showUserList();
            } else {
                Log.d(TAG, "onAuthStateChanged: signed_out");
            }
        }
    };
   }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        checkCurrentUser(mAuth.getCurrentUser());
    }
    public  void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupFirebaseAuth();

    }


    public void record(View view) {
    }


    public void uploading(View view) {
    }
}
