package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

/**
 * Created by root on 2/10/17.
 */

class GameScreen extends ScreenAdapter{
    MainClass game;
   // Stage stage;   last change
  //  Viewport viewport;   last change
    GeneralButton backbutton;

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio1 = 16/9;


    public GameScreen(MainClass game){
        this.game = game;
        game.viewport = new StretchViewport(GameWidth ,GameWidth/AspectRatio1);
        game.stage.setViewport(game.viewport);
//        viewport = new StretchViewport(game.GameWidth ,game.GameWidth/game.AspectRatio1);   last change
//        stage = new Stage(viewport);   last change

        backbutton = new GeneralButton("icons/Back Arrow-260.png","");
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth()/2,(5*GameHeight)/(6*AspectRatio) - backbutton.getHeight()/2);
   //     game.stage.clear();
        game.stage.addActor(backbutton.button);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        game.stage.act(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.draw();
    }

    @Override
    public void show() {
        super.show();
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
