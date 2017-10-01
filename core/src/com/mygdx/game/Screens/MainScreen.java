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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MainClass;

/**
 * Created by root on 1/10/17.
 */

public class MainScreen extends ScreenAdapter {

    MainClass game;
    OrthographicCamera cam;
    Stage stage;
    private Skin skin;
    private Texture badlogic;


    public MainScreen(MainClass game){
    this.game = game;
        skin = new Skin(Gdx.files.internal("neon-ui.json"));
       // skin.add("text");
        cam = new OrthographicCamera(800,400);
        game.batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());

        badlogic = new Texture("badlogic.jpg");

        final TextButton button = new TextButton("Click me ",skin,"default");
        button.setWidth(200);
        button.setHeight(50);

        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
