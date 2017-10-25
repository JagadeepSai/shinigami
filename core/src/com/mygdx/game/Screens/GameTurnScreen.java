package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

/**
 * Created by root on 25/10/17.
 */

public class GameTurnScreen extends ScreenAdapter {
    MainClass game;
    Group group;
    Image background ;
    Sprite sprite ;

    GeneralButton backbutton;
    GeneralButton restartbutton;
    Label Display;
    boolean complete;


    String back;

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;

    public GameTurnScreen(MainClass game, String string, boolean comp) {
    this.game = game;
        complete = comp;
        sprite = new Sprite(game.assets.Black);

        back = string;

        Label.LabelStyle labelStyle;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/BebasNeue Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)((float)(8*GameWidth/9)/10f);
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!


        labelStyle = new Label.LabelStyle(font12,Color.BLACK);


        group = new Group();
        group.setWidth(GameWidth/2);
        group.setHeight(group.getWidth()*AspectRatio);
        group.setPosition(GameWidth/2 - group.getWidth()/2,GameHeight/2 - group.getHeight()/2);


        background = new Image(game.assets.White);
        background.setFillParent(true);


       /* background.setWidth(GameWidth/3);
        background.setHeight(background.getWidth()*AspectRatio);
        background.setPosition(GameWidth/2 - background.getWidth()/2,GameHeight/2 - background.getHeight()/2);
*/
        if (complete)  Display = new Label("Let's Party",labelStyle);
        else  Display = new Label("Game Over",labelStyle);
        Display.setPosition(group.getWidth()/5.5f,3.5f*group.getHeight()/5);

        backbutton = new GeneralButton(game.assets.BackArrow,game.assets.BackArrow);
        backbutton.setWidth(group.getWidth()/3);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(group.getWidth()/3 - backbutton.getWidth()/1.5f,(2*group.getHeight()/6) - backbutton.getHeight()/2);

        restartbutton = new GeneralButton(game.assets.BackArrow,game.assets.BackArrow);
        restartbutton.setWidth(group.getWidth()/3);
        restartbutton.setHeight(restartbutton.getWidth()/AspectRatio);
        restartbutton.setPosition(group.getWidth()- 1.25f*restartbutton.getWidth(),(2*group.getHeight()/6) - backbutton.getHeight()/2);


       // Texture background2;

        //background2 = new Texture(game.assets.Black);
        //game.stage.getBatch().setColor(0,0,0,0.5f);
        sprite.setColor(0,0,0,0.1f);
        sprite.setSize(GameWidth,GameHeight);



        group.addActor(background);
        group.addActor(Display);
        group.addActor(backbutton.button);
        group.addActor(restartbutton.button);

    //    game.stage.addActor(background2);
        game.stage.addActor(group);


    }

    @Override
    public void render(float delta) {
       Gdx.gl.glClearColor(0,0,0,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      game.stage.getBatch().enableBlending();

        game.stage.getBatch().begin();
        sprite.draw(game.stage.getBatch());
        game.stage.getBatch().end();

        game.stage.draw();

    }

    @Override
    public void show() {

        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getScreen().hide();
                game.stage.clear();
                UserGameScreen userGameScreen = new UserGameScreen(game);
                game.setScreen(userGameScreen);
            }
        });
    }

    @Override
    public void dispose() {

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
}
