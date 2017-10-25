package com.mygdx.game;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;


import com.badlogic.gdx.backends.android.AndroidApplication;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mygdx.game.Interface.CreateAccount;


import static android.content.ContentValues.TAG;

/**
 * Created by suraj on 24/10/17.
 */

public class AndroidCreateAccount implements CreateAccount{
    private AndroidLauncher androidLauncher;
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    Handler handler;
    boolean b = true;

    public AndroidCreateAccount(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;
        mAuth = FirebaseAuth.getInstance();
        handler = new Handler();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public boolean createAccount(String username, String password, String password2) {
        if(username.isEmpty()){
            handler.post(new Runnable()
            {
                @Override
                public void run() {
                    Toast.makeText(androidLauncher, "Username Should be non empty",
                            Toast.LENGTH_SHORT).show();
                }
            });
            return false;
        }

        if(! password.equals(password2)){
            Log.d(password,password2);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(androidLauncher, "Passwords doesn't match",
                            Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }

        if(password.length()<6){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(androidLauncher, "Passwords Should have atlest 6 charctors",
                            Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }

        mAuth.createUserWithEmailAndPassword(username+ "@firebase.com", password)
                .addOnCompleteListener(androidLauncher, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            b=false;
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(androidLauncher, "Please try again",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(androidLauncher, "Succesfully account is created",
                                            Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
        if(b){
         return false;
     }
     else return true;
    }

    @Override
    public boolean check(String username, String password) {

        return true;
    }
}
