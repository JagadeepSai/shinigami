package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainClass;

import javax.swing.text.View;

/**
 * Created by root on 1/10/17.
 */

public class MainScreen extends ScreenAdapter {

    MainClass game;
    Stage stage;
    final TextButton button ;

    Texture background = new Texture("background.png");

    float GameWidth = Gdx.graphics.getWidth();
    float GameHeight = Gdx.graphics.getHeight();

    float AspectRatio1 = 16/9;

    float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());

    Viewport viewport ;
    private Skin skin;

    public MainScreen(MainClass game){

        this.game = game;
        skin = new Skin(Gdx.files.internal("neon-ui.json"));
        viewport = new StretchViewport(GameWidth ,GameWidth/AspectRatio1);
        stage = new Stage(viewport);

        button = new TextButton("PLAY",skin,"default");
        button.setWidth((150/310.5f)*GameWidth);
        button.setHeight(button.getWidth()*AspectRatio1/2f);
        button.setPosition(GameWidth/2 - button.getWidth()/2 ,GameWidth/(AspectRatio*2) - button.getHeight()/2);
        System.out.println(GameWidth/(AspectRatio*2) - button.getHeight()/2);

        final TextField login = new TextField("Username",skin,"password") ;
        login.setHeight(50);
        login.setWidth(100);

        stage.addActor(button);
        //stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.act(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,GameWidth,GameHeight);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
