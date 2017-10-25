package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.mygdx.game.Assets;
import com.mygdx.game.Interface.Login;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

/**
 * Created by root on 24/10/17.
 */

public class StageNameScreen extends ScreenAdapter {

    MainClass game;
    Assets assets;
    GeneralButton backbutton;
    GeneralButton submitbutton;

    Drawable empty ;
    Drawable cursor;
    Drawable text_background;
    TextField.TextFieldStyle style;

    ImageButton cancelFocusButton;
    ImageButton.ImageButtonStyle style2;



    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;

    TextField stagename;
    BitmapFont font = new BitmapFont();

    boolean state = true;

    Skin skin = new Skin(Gdx.files.internal("neon-ui.json"));
    public  StageNameScreen(MainClass gam){
        this.game = gam;
        assets=gam.assets;

        game.stage.getViewport().update((int)GameWidth,(int)(GameHeight));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/BebasNeue Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)((float)(8*GameWidth/9)/10f);
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        //skin.add("myFont12",font12,BitmapFont.class);

        skin.addRegions(new TextureAtlas(Gdx.files.internal("neon-ui.atlas")));
        skin.load(Gdx.files.internal("neon-ui.json"));

        skin.getFont("font").getData().setScale(AspectRatio1*2f,AspectRatio1*2f);

        backbutton = new GeneralButton(assets.BackArrow,assets.BackArrow);
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio) + backbutton.getHeight()/2);



        submitbutton = new GeneralButton(assets.PlayButton,assets.PlayButton);
        submitbutton.setWidth(GameWidth/4.5f);
        submitbutton.setHeight(submitbutton.getWidth()/AspectRatio);
        submitbutton.setPosition(GameWidth/2 - submitbutton.getWidth()/2,GameWidth/(AspectRatio*2) + submitbutton.getHeight()/3);



        cursor = skin.getDrawable("color");
        empty = new Drawable() {
            @Override
            public void draw(Batch batch, float x, float y, float width, float height) {

            }

            @Override
            public float getLeftWidth() {
                return 0;
            }

            @Override
            public void setLeftWidth(float leftWidth) {

            }

            @Override
            public float getRightWidth() {
                return 0;
            }

            @Override
            public void setRightWidth(float rightWidth) {

            }

            @Override
            public float getTopHeight() {
                return 0;
            }

            @Override
            public void setTopHeight(float topHeight) {

            }

            @Override
            public float getBottomHeight() {
                return 0;
            }

            @Override
            public void setBottomHeight(float bottomHeight) {

            }

            @Override
            public float getMinWidth() {
                return 0;
            }

            @Override
            public void setMinWidth(float minWidth) {

            }

            @Override
            public float getMinHeight() {
                return 0;
            }

            @Override
            public void setMinHeight(float minHeight) {

            }
        };
        text_background = skin.getDrawable("textfield-c");

        style2 = new ImageButton.ImageButtonStyle();
        style2.up = null;
        style2.down = null;
        cancelFocusButton = new ImageButton(style2);



        style = new TextField.TextFieldStyle(font12, Color.BLACK,cursor,empty,text_background);
        //login = new TextField("", skin,"login");
        stagename = new TextField("",style);
        stagename.setMessageText("Stage Name");
        stagename.setWidth(GameWidth/1.5f);
        stagename.setHeight(GameHeight/20);
        stagename.setPosition(GameWidth/2 - stagename.getWidth()/2, GameHeight/1.7f - stagename.getWidth()/8);

        stagename.addListener(new FocusListener() {

            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                super.keyboardFocusChanged(event, actor, focused);
                if (!focused)
                    Gdx.input.setOnscreenKeyboardVisible(false);
                stagename.getOnscreenKeyboard().show(false);
                // game.stage.unfocusAll();
            }
        });

        /*error = new Image("Please Try Again",skin);
        error.setWidth(GameWidth);
        error.setHeight(backbutton.getWidth()/AspectRatio);
        error.setPosition(GameWidth/2 - newuserbutton.getWidth()/2,GameHeight/2 + login.getWidth()/2);*/
        //  error.setPosition(0,0);
        // error.setVisible(false);

        // password = new TextField("",skin,"password");

        game.stage.addActor(cancelFocusButton);
        cancelFocusButton.setFillParent(true);
        game.stage.addActor(stagename);
        game.stage.addActor(submitbutton.button);
        game.stage.addActor(backbutton.button);

        //  game.stage.addActor(error);
        //game.stage.getKeyboardFocus();
        //  style = new TextFieldStyle();
        //  login = new TextArea("Login",skin);

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.getBatch().begin();
        font.draw(game.stage.getBatch(),"Error, Please Enter Again",0,0);
        //error.setVisible(true);  GameWidth/5,GameHeight/2 + login.getWidth()/2
        game.stage.getBatch().end();

        game.stage.draw();
    }

    @Override
    public void show() {
        cancelFocusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setOnscreenKeyboardVisible(false);
                game.stage.unfocusAll();
            }
        });

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

        submitbutton.setTouchable();
        submitbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {


                game.getScreen().hide();
                game.stage.clear();
                String name = stagename.getText();
                StageCreatorScreen stageCreatorScreen = new StageCreatorScreen(game,name);
                game.setScreen(stageCreatorScreen);
            }

        });

        //super.show();
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

