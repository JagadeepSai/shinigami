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

/**
 * Created by root on 1/10/17.
 */

public class MainScreen extends ScreenAdapter {

    MainClass game;
    Stage stage;

    private Skin skin;
    private Texture badlogic;

    public MainScreen(MainClass game){
    this.game = game;
        skin = new Skin(Gdx.files.internal("neon-ui.json"));
       // skin.add("text");
        game.batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(200,500));
        badlogic = new Texture("badlogic.jpg");

        final TextButton button = new TextButton("PLAY",skin,"default");
        button.setWidth(100);
        button.setHeight(50);

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
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // game.batch.begin();
       // game.batch.draw(badlogic,0,0);
       // game.batch.end();
         stage.act(Gdx.graphics.getDeltaTime());
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
