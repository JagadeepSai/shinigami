package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainButton.ToggleButton;
import com.mygdx.game.MainClass;

/**
 * Created by root on 3/10/17.
 */

public class SettingScreen extends ScreenAdapter {
    MainClass game;
    Assets assets;
    GeneralButton backbutton;
    ToggleButton fbbutton;
    GeneralButton loginbutton;
    ToggleButton gplusbutton;
    ToggleButton speakerbutton;
    ToggleButton musicbutton;

    private boolean fblogin = false;
    private boolean accountlogin = false;
    private boolean gpluslogin = false;

    private  boolean speakerstate = true;
    private boolean musicstate = true;
  //  Viewport viewport ;
 //   Table table = new Table();

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;


    public SettingScreen(MainClass game){
        this.game = game;
        assets=game.assets;
        game.stage.getViewport().update((int)GameWidth,(int)(GameHeight));

        backbutton = new GeneralButton(assets.BackArrow,assets.BackArrow);
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio) + backbutton.getHeight()/2);

        speakerbutton = new ToggleButton(assets.Speaker,assets.Speaker,true);
        speakerbutton.setWidth(GameWidth/4);
        speakerbutton.setHeight(speakerbutton.getWidth()/AspectRatio);
        speakerbutton.setPosition(GameWidth/3 - 2*speakerbutton.getWidth()/3,GameHeight/2 + speakerbutton.getHeight()/6);
        speakerbutton.setToggle(assets.SpeakerIn);


        musicbutton = new ToggleButton(assets.MusicCircle,assets.MusicCircle,true);
        musicbutton.setWidth(GameWidth/4);
        musicbutton.setHeight(musicbutton.getWidth()/AspectRatio);
        musicbutton.setPosition(2*GameWidth/3 -musicbutton.getWidth()/3,GameHeight/2 + musicbutton.getHeight()/6);
        musicbutton.setToggle(assets.MusicIn);


        fbbutton = new ToggleButton(assets.Facebook,assets.Facebook,false);
        fbbutton.setWidth(3*GameWidth/13);
        fbbutton.setHeight(fbbutton.getWidth()/AspectRatio);
        fbbutton.setPosition(fbbutton.getWidth()/3 ,GameWidth/(AspectRatio*2) - fbbutton.getHeight()/2);
        fbbutton.setToggle(assets.FacebookIn);

        loginbutton = new ToggleButton(assets.Facebook,assets.Facebook,false);
        loginbutton.setWidth(3*GameWidth/13);
        loginbutton.setHeight(loginbutton.getWidth()/AspectRatio);
        loginbutton.setPosition(GameWidth/2 - loginbutton.getWidth()/2 ,GameWidth/(AspectRatio*2) - loginbutton.getHeight()/2);

        gplusbutton = new ToggleButton(assets.GooglePlus,assets.GooglePlus,false);
        gplusbutton.setWidth(3*GameWidth/13);
        gplusbutton.setHeight(gplusbutton.getWidth()/AspectRatio);
        gplusbutton.setPosition(GameWidth - gplusbutton.getWidth()/3 - gplusbutton.getWidth() ,GameWidth/(AspectRatio*2) - gplusbutton.getHeight()/2);
        gplusbutton.setToggle(assets.GooglePlusIn);

        game.stage.addActor(backbutton.button);
        game.stage.addActor(speakerbutton.button);
        game.stage.addActor(musicbutton.button);
        game.stage.addActor(fbbutton.button);
        game.stage.addActor(loginbutton.button);
        game.stage.addActor(gplusbutton.button);


        Gdx.input.setInputProcessor(game.stage);


    }

    @Override
    public void render(float delta) {
        //game.stage.act(Gdx.graphics.getDeltaTime());




        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.draw();
    }

    @Override
    public void show() {
        speakerbutton.setTouchable();
        speakerbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                speakerbutton.change();
            }
        });

        musicbutton.setTouchable();
        musicbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                musicbutton.change();
            }
        });

        fbbutton.setTouchable();
        fbbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                fbbutton.change();
            }
        });

        gplusbutton.setTouchable();
        gplusbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                gplusbutton.change();
            }
        });



        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getScreen().hide();
                game.stage.clear();
                MainScreen mainScreen = new MainScreen(game);
                game.setScreen(mainScreen);
            }
        });

        loginbutton.setTouchable();
        loginbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getScreen().hide();
                game.stage.clear();
                LoginScreen loginScreen = new LoginScreen(game);
                game.setScreen(loginScreen);
            }
        });
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
