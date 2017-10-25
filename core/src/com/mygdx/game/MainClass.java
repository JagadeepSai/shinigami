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
import com.mygdx.game.Screens.GameTurnScreen;
import com.mygdx.game.Interface.Login;
import com.mygdx.game.Interface.SaveToDatabase;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MainScreen;
import com.mygdx.game.Screens.Transitions.TransitionScreen;
import com.mygdx.game.Screens.UserGameScreen;

import org.lwjgl.Sys;

import java.io.OutputStream;

public class MainClass extends Game {
	public Preferences prefs ;

	public Stage stage;
	public Viewport viewport ;
	public CreateAccount createAccount;
	public GoogleSignin googleSignin;
	public SaveToDatabase saveToDatabase;


	public boolean back_tune_play = true;
	public boolean button_tune_play = true;
	public  String Username ;



	public Assets assets;

	@Override
	public void create () {
		prefs= Gdx.app.getPreferences("preferences");
		if(prefs.get()==null) {
			prefs.putString("username","").flush();
		}
		Username = prefs.getString("username");


		//Gdx.app.log("qwer",Gdx.files.getExternalStoragePath());
		FileHandle fileHandle = Gdx.files.local("usersaved.txt");
		fileHandle.writeString("",true);

        stage = new Stage();
		viewport = new StretchViewport(0,0);
		//UserGameScreen userGameScreen = new UserGameScreen(this);
		assets.load();
		MainScreen mainScreen = new MainScreen(this);
		//GameTurnScreen gameTurnScreen = new GameTurnScreen(this,"mainscreen",false);
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
	}
}
