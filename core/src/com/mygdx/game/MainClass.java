package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Interface.CreateAccount;
import com.mygdx.game.Interface.SaveToDatabase;
import com.mygdx.game.Interface.Toast;
import com.mygdx.game.Screens.MainScreen;

import java.util.Set;

/**
 * Main class in libgdx , called right after Android launcher
 */
public class MainClass extends Game {
	public Preferences prefs ;
	public Stage stage;
	public Viewport viewport ;
	public CreateAccount createAccount;
	public SaveToDatabase saveToDatabase;
	public Toast toast;
	public Set<String> set;
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
		//check for the pervious logged in username
		prefs= Gdx.app.getPreferences("preferences");
		if(prefs.get()==null) {
			prefs.putString("username","").flush();
		}
		Username = prefs.getString("username");
		FileHandle fileHandle = Gdx.files.local("usersaved.txt");
		fileHandle.writeString("",true);
        stage = new Stage();
		viewport = new StretchViewport(0,0);
		//load the assets
		assets.load();
		assets.back_tune.setVolume(0.35f);
		assets.back_tune.play();
        saveToDatabase.load();
		Gdx.gl.glClearColor(205/255,205/255,205/255,0);
		//start the animation screen
		Animate anime = new Animate(this);
		setScreen(anime);
	}

	@Override
	public void render () {

		super.render();
	}
	
	@Override
	public void dispose () {

		stage.dispose();
	}

	/**
	 * Utility function, to set createAccount to android create account
	 * @param createAccount interface for authentication related functions
	 */
	public void setCreateAccount(CreateAccount createAccount) {

		this.createAccount = createAccount;
	}

	/**
	 * Utility function to set savetodatabase to androidsavetodatabase
	 * @param saveToDatabase interface for saving maps in database
	 */
	public void setSaveToDatabase(SaveToDatabase saveToDatabase){

		this.saveToDatabase=saveToDatabase;
		set=saveToDatabase.getset();

	}

	/**
	 * Utility function to set Toast to android toast
	 * @param toast interface having functions to show toasts
	 */
	public void setToast(Toast toast){

		this.toast=toast;
	}
}
