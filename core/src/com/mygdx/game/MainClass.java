package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Interface.CreateAccount;
import com.mygdx.game.Interface.GoogleSignin;
import com.mygdx.game.Interface.Login;
import com.mygdx.game.Interface.SaveToDatabase;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MainScreen;
import com.mygdx.game.Screens.Transitions.TransitionScreen;
import com.mygdx.game.Screens.UserGameScreen;

import org.lwjgl.Sys;

import java.io.OutputStream;
import java.util.Set;

public class MainClass extends Game {
	public Preferences prefs ;

	public Stage stage;
	public Viewport viewport ;
	public CreateAccount createAccount;
	public GoogleSignin googleSignin;
	public SaveToDatabase saveToDatabase;

	public  Music back_tune ;
	public Set<String> set;
	public Music button_tune;
	public boolean back_tune_play = true;
	public boolean button_tune_play = true;
	public  String Username ;

	public boolean completed = false;
	public boolean load = false;

	public String idload;
	public String nameload;
	public String usernameload;
	public String jsonload;

	public Assets assets;

	@Override
	public void create () {
		prefs= Gdx.app.getPreferences("preferences");
		if(prefs.get()==null) {
			prefs.putString("username","").flush();
		}
		Username = prefs.getString("username");



		back_tune = Gdx.audio.newMusic(Gdx.files.internal("sounds/homescreen_music-not_to_be_played_when_user_plays_game.mp3"));
		button_tune = Gdx.audio.newMusic(Gdx.files.internal("sounds/Remaining_all_buttons.mp3"));


		//Gdx.app.log("qwer",Gdx.files.getExternalStoragePath());
		FileHandle fileHandle = Gdx.files.local("usersaved.txt");
		fileHandle.writeString("",true);

        stage = new Stage();
		viewport = new StretchViewport(0,0);
		//UserGameScreen userGameScreen = new UserGameScreen(this);
		assets.load();
        saveToDatabase.load();
		MainScreen mainScreen = new MainScreen(this);

        setScreen(mainScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
	public void setCreateAccount(CreateAccount createAccount) {
		this.createAccount = createAccount;
	}
	public void setGoogleSignin(GoogleSignin googleSignin){
		this.googleSignin=googleSignin;
	}
	public void setSaveToDatabase(SaveToDatabase saveToDatabase){
		this.saveToDatabase=saveToDatabase;
		System.out.println("Set"+  set);
		set=saveToDatabase.getset();
	}
}
