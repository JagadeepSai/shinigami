package com.mygdx.game;

import android.content.Intent;
import android.content.res.Resources;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mygdx.game.Interface.GoogleSignin;

/**
 * Created by suraj on 25/10/17.
 */

public class AndroidGoogleSignin implements GoogleSignin {
//    AndroidLauncher androidLauncher;
//    GoogleSignInOptions gso ;
//    Resources res = Resources.getSystem();
//    private static final int RC_SIGN_IN=1;
//    private GoogleApiClient googleApiClient;
//
//    public AndroidGoogleSignin(AndroidLauncher androidLauncher){
//        this.androidLauncher=androidLauncher;
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(res.getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        googleApiClient=new GoogleSignInOptions.Builder(androidLauncher)
//                .enable
//
//    }
//    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
    @Override
    public boolean Gsignin() {

        return false;
    }
}
