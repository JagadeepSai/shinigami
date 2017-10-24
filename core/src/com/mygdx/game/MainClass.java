package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Interface.Login;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MainScreen;
import com.mygdx.game.Screens.StageCreatorScreen;
import com.mygdx.game.Screens.Transitions.TransitionScreen;
import com.mygdx.game.Screens.UserGameScreen;

public class MainClass extends Game {
	public Stage stage;
	public Viewport viewport ;
	public Login authenticate;

	public  Music back_tune ;

	public Music button_tune;
	public boolean back_tune_play = true;
	public boolean button_tune_play = true;

	@Override
	public void create () {
		back_tune = Gdx.audio.newMusic(Gdx.files.internal("sounds/homescreen_music-not_to_be_played_when_user_plays_game.mp3"));
		button_tune = Gdx.audio.newMusic(Gdx.files.internal("sounds/Remaining_all_buttons.mp3"));


		stage = new Stage();
		viewport = new StretchViewport(0,0);
		//StageCreatorScreen stageCreatorScreen = new StageCreatorScreen(this);
		//UserGameScreen userGameScreen = new UserGameScreen(this);
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
	public void setLogin(Login login) {
		this.authenticate = login;
	}
}
