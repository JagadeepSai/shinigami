package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Interface.Login;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

/**
 * Created by root on 7/10/17.
 */

public class LoginScreen extends ScreenAdapter {

    MainClass game;
    GeneralButton backbutton;
    GeneralButton submitbutton;
    GeneralButton newuserbutton;
    public Login authenticate;

   // TextFieldStyle style;
    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;

    TextField login;
    TextField password;
    TextField re_password;
   // Image error;
    BitmapFont font = new BitmapFont();

    boolean state = true;

    Skin skin = new Skin(Gdx.files.internal("neon-ui.json"));
    public  LoginScreen(MainClass gam){
        this.game = gam;
        this.authenticate=gam.authenticate;
        game.stage.getViewport().update((int)GameWidth,(int)(GameHeight));


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Play.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        skin.add("myFont12",font12,BitmapFont.class);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("neon-ui.atlas")));
        skin.load(Gdx.files.internal("neon-ui.json"));

        skin.getFont("font").getData().setScale(AspectRatio1,AspectRatio1);

        backbutton = new GeneralButton("icons/Back Arrow-260.png","");
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio) + backbutton.getHeight()/2);



        newuserbutton = new GeneralButton("icons/Newuser-260.png","");
        newuserbutton.setWidth(GameWidth/4.5f);
        newuserbutton.setHeight(newuserbutton.getWidth()/AspectRatio);
        newuserbutton.setPosition(GameWidth/2 - newuserbutton.getWidth()/2,GameWidth/(AspectRatio*2) - newuserbutton.getHeight());

        submitbutton = new GeneralButton("icons/Play Button-500.png","");
        submitbutton.setWidth(GameWidth/4.5f);
        submitbutton.setHeight(submitbutton.getWidth()/AspectRatio);
        submitbutton.setPosition(GameWidth/2 - submitbutton.getWidth()/2,GameWidth/(AspectRatio*2) + submitbutton.getHeight()/3);


        login = new TextField("", skin,"login");
        login.setMessageText("Username");
        login.setWidth(GameWidth/1.5f);
        login.setHeight(GameHeight/30);
        login.setPosition(GameWidth/2 - login.getWidth()/2, GameHeight/1.7f + login.getWidth()/8);

        /*error = new Image("Please Try Again",skin);
        error.setWidth(GameWidth);
        error.setHeight(backbutton.getWidth()/AspectRatio);
        error.setPosition(GameWidth/2 - newuserbutton.getWidth()/2,GameHeight/2 + login.getWidth()/2);*/
      //  error.setPosition(0,0);
       // error.setVisible(false);

        password = new TextField("",skin,"password");
        password.setMessageText("Password");
        password.setWidth(GameWidth/1.5f);
        password.setHeight(GameHeight/20);
        password.setPosition(GameWidth/2 - password.getWidth()/2, GameHeight/1.7f - password.getWidth()/8);

        re_password = new TextField("",skin,"password");
        re_password.setMessageText("Confirm-Password");
        re_password.setWidth(GameWidth/1.5f);
        re_password.setHeight(GameHeight/20);
        re_password.setPosition(GameWidth/2 - re_password.getWidth()/2, GameHeight/1.7f - re_password.getHeight()*2.5f);
        re_password.setVisible(false);

        game.stage.addActor(submitbutton.button);
        game.stage.addActor(backbutton.button);
        game.stage.addActor(newuserbutton.button);
      //  game.stage.addActor(error);
        game.stage.addActor(login);
        game.stage.addActor(password);
        game.stage.addActor(re_password);
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
        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getScreen().hide();
                game.stage.clear();
                SettingScreen settingScreen = new SettingScreen(game);
                game.setScreen(settingScreen);
            }
        });

        submitbutton.setTouchable();
        submitbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                if(state == true) {
                    String user = login.getText();
                    String pass = password.getText();
                    //Gdx.app.log(user,pass);
                    //Gdx.app.log("qq","11");
                    authenticate.check(user,pass);
                }
                else{
                    String user = login.getText();
                    String pass = password.getText();
                    String pass2 = re_password.getText();

                    if(!pass.equals(pass2) || user == "" ){
                        game.stage.getBatch().begin();
                        font.draw(game.stage.getBatch(),"Error, Please Enter Again",0,0);
                        //error.setVisible(true);  GameWidth/5,GameHeight/2 + login.getWidth()/2
                        game.stage.getBatch().end();
                    }
                    else{
                        game.getScreen().hide();
                        game.stage.clear();
                        SettingScreen settingScreen = new SettingScreen(game);
                        game.setScreen(settingScreen);
                    }
                }
            }
        });

        newuserbutton.setTouchable();
        newuserbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                newuserbutton.button.setVisible(false);
                re_password.setVisible(true);
                //submitbutton.button.setPosition(submitbutton.getPosition().x,submitbutton.getPosition().y - re_password.getHeight()*6f);
                submitbutton.button.setPosition(submitbutton.getPosition().x, GameHeight/5f);
                state = false;

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
