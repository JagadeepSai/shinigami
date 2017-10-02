package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

/**
 * Created by root on 3/10/17.
 */

public class SettingScreen extends ScreenAdapter {
    MainClass game;
 //   Stage stage; last change
    GeneralButton backbutton;
    GeneralButton fbbutton;
    GeneralButton gplusbutton;
    GeneralButton speakerbutton;
    GeneralButton musicbutton;
  //  Viewport viewport ;
 //   Table table = new Table();

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio1 = 16/9;


    public SettingScreen(MainClass game){
        this.game = game;
        game.viewport = new StretchViewport(GameWidth ,GameWidth/AspectRatio1);
        game.stage.setViewport(game.viewport);
//        viewport = new StretchViewport(game.GameWidth ,game.GameWidth/game.AspectRatio1);
//        stage = new Stage(viewport);

        backbutton = new GeneralButton("icons/Back Arrow-260.png","");
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth()/2,(5*GameHeight)/(6*AspectRatio) - backbutton.getHeight()/2);

        speakerbutton = new GeneralButton("icons/Speaker-260.png","");
        speakerbutton.setWidth(GameWidth/6);
        speakerbutton.setHeight(speakerbutton.getWidth()/AspectRatio);


        musicbutton = new GeneralButton("icons/Music-500.png","");
        musicbutton.setWidth(GameWidth/6);
        musicbutton.setHeight(musicbutton.getWidth()/AspectRatio);

        fbbutton = new GeneralButton("icons/Facebook - 260.png","");
        fbbutton.setWidth(3*GameWidth/13);
        fbbutton.setHeight(fbbutton.getWidth()/AspectRatio);
        fbbutton.setPosition(fbbutton.getWidth()/3 ,GameWidth/(AspectRatio*2) - fbbutton.getHeight()/2);

        gplusbutton = new GeneralButton("icons/Google Plus-500.png","");
        gplusbutton.setWidth(3*GameWidth/13);
        gplusbutton.setHeight(gplusbutton.getWidth()/AspectRatio);
        gplusbutton.setPosition(GameWidth - gplusbutton.getWidth()/3 - gplusbutton.getWidth() ,GameWidth/(AspectRatio*2) - gplusbutton.getHeight()/2);





        ///Created Table to render UI -  Failed :(

/*
        table.setFillParent(true);
        table.defaults().expandX().fill().space(5f);

        table.add(backbutton.button).top().left().width(backbutton.getWidth()).height(backbutton.getHeight()).pad(10,10,10,10);
        table.row();
        table.add(speakerbutton.button).width(speakerbutton.getWidth()).height(speakerbutton.getHeight()).pad(10);
        table.row();
        table.add(musicbutton.button).width(musicbutton.getWidth()).height(musicbutton.getHeight()).pad(10);
        table.row();
        table.add(fbbutton.button).width(fbbutton.getWidth()).height(fbbutton.getHeight()).pad(10);
        table.row();
        table.add(gplusbutton.button).width(gplusbutton.getWidth()).height(gplusbutton.getHeight()).pad(10);
        table.row();
        table.center();
        stage.addActor(table);
*/
        game.stage.addActor(backbutton.button);
        game.stage.addActor(fbbutton.button);
        game.stage.addActor(gplusbutton.button);


        Gdx.input.setInputProcessor(game.stage);


    }

    @Override
    public void render(float delta) {
        game.stage.act(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
       // game.stage.dispose();

    }
}
