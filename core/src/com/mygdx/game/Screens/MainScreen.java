package com.mygdx.game.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.MainClass;

/**
 * Created by root on 1/10/17.
 */

public class MainScreen extends ScreenAdapter {
    MainClass game;
    OrthographicCamera guiCam;
    public MainScreen(MainClass game){
        this.game = game;
    }
}
