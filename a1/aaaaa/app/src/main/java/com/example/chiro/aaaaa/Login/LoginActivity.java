package com.example.chiro.aaaaa.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chiro.aaaaa.R;
import com.example.chiro.aaaaa.Home.homePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext;
    private ProgressBar mProgressBar;
    private EditText mEmail, mPassword;
    private TextView mPleasewait;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = (ProgressBar) findViewById(R.id.Progressbar);
        mPleasewait =  (TextView) findViewById(R.id.pleaseWait);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText)findViewById(R.id.input_password);
        mContext = LoginActivity.this;



        Log.d(TAG, "onCreate: started.");
        mPleasewait.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        setupFirebaseAuth();
        init();
    }
    
    private boolean isStringNull(String string){
        Log.d(TAG, "isStringNull: checking string if null");

        if(string.equals("")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * -------------------Setup Firebase------------------------------------------
     */
    private void init(){

        // initialize the butotn for logging in
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting to log in");

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if(isStringNull(email) && isStringNull(password)){
                    Toast.makeText(mContext,"Email and Password required", Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.GONE);
                    mPleasewait.setVisibility(View.GONE);
                }else{
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPleasewait.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    FirebaseUser user = mAuth.getCurrentUser();


                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "signInWithEmail:success");
                                        //Toast.makeText(LoginActivity.this, "Authentication Successed.",
                                               // Toast.LENGTH_SHORT).show();
                                        try{
                                            if(user.isEmailVerified()){
                                                Log.d(TAG, "onComplete: success verified");
                                                Intent intent = new Intent(LoginActivity.this, homePage.class);
                                                startActivity(intent);
                                            }else{
                                                Toast.makeText(mContext,"Email is not verified \n Check your email inbox",Toast.LENGTH_SHORT).show();
                                                mProgressBar.setVisibility(View.GONE);
                                                mPleasewait.setVisibility(View.GONE);
                                                mAuth.signOut();
                                            }

                                        }catch(NullPointerException e){
                                            Log.e(TAG, "onComplete: NullPointerException"+e.getMessage() );
                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                        mProgressBar.setVisibility(View.GONE);
                                        mPleasewait.setVisibility(View.GONE);

                                    }

                                    // ...
                                }
                            });
                }
            }
        });
       TextView linkSignUp =(TextView) findViewById(R.id.link_signup);
       linkSignUp.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Log.d(TAG, "onClick: navigating to the register page.");
               Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
               startActivity(intent);
           }

       });

        /*
            if user exited and log in then go to the main page
         */
        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(LoginActivity.this,homePage.class );
            startActivity(intent);
            finish();
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

    }
    public  void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
