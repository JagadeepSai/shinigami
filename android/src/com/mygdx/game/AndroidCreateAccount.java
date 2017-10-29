package com.mygdx.game;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mygdx.game.Interface.CreateAccount;
import static android.content.ContentValues.TAG;


/**
 * This impliments CreateAccount class the core directory, and have necesarry functions for Creatingaccount and login
 */
public class AndroidCreateAccount implements CreateAccount {
    AndroidLauncher androidLauncher;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    Handler handler; /*! for showing toasts*/
    String helper1 = null;
    String helper2 = null;

    /**
     * Constructor, checks if user has already logged in, and gives their username.
     * @param androidLauncher
     */
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
            }
        };
    }

    /**
     * Creates an account for a perticular username and password
     *
     * Check for correctness of username and passwords
     * @param username username
     * @param password password
     * @param password2 retyped password
     * @return true if succesfully created account
     */
    @Override
    public boolean createAccount(String username, String password, String password2) {
        if (username.isEmpty()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(androidLauncher, "Username Should be non empty",
                            Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }

        if (!password.equals(password2)) {
            Log.d(password, password2);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(androidLauncher, "Passwords doesn't match",
                            Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }

        if (password.length() < 6) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(androidLauncher, "Passwords Should have atlest 6 charctors",
                            Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
        helper1 = null;
        mAuth.createUserWithEmailAndPassword(username + "@firebase.com", password)
                .addOnCompleteListener(androidLauncher, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            helper1 = "false";
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(androidLauncher, "Please try again",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            helper1 = "true";
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
        while (helper1 == null) System.out.println("NULL");
        if (helper1.equals("false")) {
            return true;
        } else return false;
    }

    /**
     * Autheticates with the username and password
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean check(String username, String password) {
        helper2 = null;
        mAuth.signInWithEmailAndPassword(username + "@firebase.com", password)
                .addOnCompleteListener(androidLauncher, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            helper2 = "false";
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(androidLauncher, "Unable to login",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            helper2 = "true";
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(androidLauncher, "Succesfully",
                                            Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }

                });
        while (helper2 == null) System.out.println("C is null");
        if (helper2.equals("true")) return true;
        else return false;
    }
}