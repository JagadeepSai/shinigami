package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.Interface.GoogleSignin;
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
    ToggleButton loginbutton;
    ToggleButton gplusbutton;
    ToggleButton speakerbutton;
    ToggleButton musicbutton;
    public GoogleSignin googleSignin;

  //  Viewport viewport ;
 //   Table table = new Table();

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;


    public SettingScreen(MainClass game){
        this.game = game;
        assets=game.assets;

        System.out.println("Username :  " + game.prefs.getString("username"));
        this.googleSignin=game.googleSignin;
        game.stage.getViewport().update((int)GameWidth,(int)(GameHeight));

        backbutton = new GeneralButton(assets.BackArrow,assets.BackArrow);
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio) + backbutton.getHeight()/2);

        speakerbutton = new ToggleButton(assets.Speaker,assets.SpeakerIn,game.button_tune_play);
        speakerbutton.setWidth(GameWidth/4);
        speakerbutton.setHeight(speakerbutton.getWidth()/AspectRatio);
        speakerbutton.setPosition(GameWidth/3 - 2*speakerbutton.getWidth()/3,GameHeight/2 + speakerbutton.getHeight()/6);


        musicbutton = new ToggleButton(assets.MusicCircle,assets.MusicIn,game.back_tune_play);
        musicbutton.setWidth(GameWidth/4);
        musicbutton.setHeight(musicbutton.getWidth()/AspectRatio);
        musicbutton.setPosition(2*GameWidth/3 -musicbutton.getWidth()/3,GameHeight/2 + musicbutton.getHeight()/6);


        fbbutton = new ToggleButton(assets.Facebook,assets.FacebookIn,false);
        fbbutton.setWidth(3*GameWidth/13);
        fbbutton.setHeight(fbbutton.getWidth()/AspectRatio);
        fbbutton.setPosition(fbbutton.getWidth()/3 ,GameWidth/(AspectRatio*2) - fbbutton.getHeight()/2);
        fbbutton.button.setVisible(false);

        loginbutton = new ToggleButton(assets.Logout,assets.Login,game.prefs.getString("username").equals(""));
        loginbutton.setWidth(3*GameWidth/13);
        loginbutton.setHeight(loginbutton.getWidth()/AspectRatio);
        loginbutton.setPosition(GameWidth/2 - loginbutton.getWidth()/2 ,GameWidth/(AspectRatio*2) - loginbutton.getHeight()/2);

        gplusbutton = new ToggleButton(assets.GooglePlus,assets.GooglePlusIn,false);
        gplusbutton.setWidth(3*GameWidth/13);
        gplusbutton.setHeight(gplusbutton.getWidth()/AspectRatio);
        gplusbutton.setPosition(GameWidth - gplusbutton.getWidth()/3 - gplusbutton.getWidth() ,GameWidth/(AspectRatio*2) - gplusbutton.getHeight()/2);
        gplusbutton.button.setVisible(false);

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
                if(!speakerbutton.getState()) game.button_tune_play = false;
                else   game.button_tune_play = true;
            }
        });

        musicbutton.setTouchable();
        musicbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                musicbutton.change();
                if(!musicbutton.getState()){
                    game.back_tune_play = false;
                    game.assets.back_tune.stop();
                }
                else{
                    game.back_tune_play = true;
                    game.assets.back_tune.play();
                }

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


                googleSignin.Gsignin();
            }
        });



        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(game.button_tune_play)   game.assets.button_tune.play();
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
                if ((game.Username.equals(""))) {
                    if(game.button_tune_play) game.assets.button_tune.play();
                    game.getScreen().hide();
                game.stage.clear();
                LoginScreen loginScreen = new LoginScreen(game);
                game.setScreen(loginScreen);

                }else{
                    game.toast.showtost("Successfully Logged Out");
                    game.Username = "" ;
                    game.prefs.putString("username","").flush();
                }

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
