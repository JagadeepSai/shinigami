package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

/**
 * Created by root on 8/10/17.
 */

public class StageCreatorScreen extends ScreenAdapter {

    MainClass game ;
    GeneralButton ontapcircle;
    Vector<Vector2> positions ;
    Vector2 touch = new Vector2();

    public  StageCreatorScreen (MainClass gam){
        this.game = gam;
        ontapcircle = new GeneralButton("icons/Mesh-260.png","");
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {

            touch.set(Gdx.input.getX(), Gdx.input.getY());
        }
        super.render(delta);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}


