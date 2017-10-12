package com.mygdx.game;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mygdx.game.Interface.Login;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by suraj on 11/10/17.
 */

public class AndroidLogin implements Login {

    private Activity gameActivity;
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;


    public AndroidLogin(Activity gameActivity) {
        mAuth = FirebaseAuth.getInstance();
        this.gameActivity = gameActivity;
    }


    @Override
    public boolean check(String username, String password) {
        Log.d(username,password);
        mAuth.createUserWithEmailAndPassword(username+ "@firebase.com", password);
        return true;
    }
}