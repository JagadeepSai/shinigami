package com.mygdx.game;

import android.os.Handler;
import com.mygdx.game.Interface.Toast;

/**
 * For showing errors in form of toats
 */
public class AndroidToast implements Toast {
    Handler handler;
    AndroidLauncher androidLauncher;
    AndroidToast(AndroidLauncher androidLauncher){
        this.androidLauncher=androidLauncher;
        handler=new Handler();
    }

    /**
     * Shows a toast with the given string as papameter
     * @param s the messege to be shown on the screen
     */
    @Override
    public void showtost(String s) {
        final String ss =s;
        handler.post(new Runnable() {
            @Override
            public void run() {
                android.widget.Toast.makeText(androidLauncher, ss,
                        android.widget.Toast.LENGTH_SHORT).show();
            }
        });
    }
}
