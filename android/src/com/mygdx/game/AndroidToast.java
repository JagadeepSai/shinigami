package com.mygdx.game;

import android.os.Handler;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.mygdx.game.Interface.Toast;

/**
 * Created by suraj on 26/10/17.
 */

public class AndroidToast implements Toast {
    Handler handler;
    AndroidLauncher androidLauncher;
    AndroidToast(AndroidLauncher androidLauncher){
        this.androidLauncher=androidLauncher;
        handler=new Handler();

    }
    @Override
    public void showtost(String s) {
        final String ss =s;
        handler.post(new Runnable()
        {
            @Override
            public void run() {
                android.widget.Toast.makeText(androidLauncher, ss,
                        android.widget.Toast.LENGTH_SHORT).show();
            }
        });
    }
}
