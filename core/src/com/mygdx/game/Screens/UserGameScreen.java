package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

import sun.applet.Main;

/**
 * Created by root on 8/10/17.
 */

public class UserGameScreen extends ScreenAdapter {
    MainClass game ;

    GeneralButton designbutton;

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;

    public UserGameScreen(MainClass gam){
        this.game= gam;

        designbutton = new GeneralButton("icons/PalleteBB.png","");
        designbutton.setWidth(GameWidth/5.3f);
        designbutton.setHeight(designbutton.getWidth()/AspectRatio);
        designbutton.setPosition(3.9f*GameWidth/5 - designbutton.getWidth()/2 , GameHeight/(AspectRatio*6) - designbutton.getHeight() );
        designbutton.button.setZIndex(5);

        game.stage.addActor(designbutton.button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.draw();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
