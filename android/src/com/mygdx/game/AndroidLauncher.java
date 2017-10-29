package com.mygdx.game;

import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * This is the main class, this opens when app is opened, and this transfer the handling to libgdx
 */
public class AndroidLauncher extends AndroidApplication {
    public AndroidCreateAccount androidCreateAccount;
    public AndroidSaveToDatabase androidSaveToDatabase;
    public AndroidToast androidToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        androidCreateAccount = new AndroidCreateAccount(this);
        androidToast = new AndroidToast(this);
        MainClass mainClass = new MainClass();
        mainClass.setCreateAccount(androidCreateAccount);
        androidSaveToDatabase = new AndroidSaveToDatabase(mainClass);
        mainClass.setSaveToDatabase(androidSaveToDatabase);
        mainClass.setToast(androidToast);
        initialize(mainClass, config);
    }
}
