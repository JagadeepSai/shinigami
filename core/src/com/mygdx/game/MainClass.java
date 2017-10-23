package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

	@Override
	public void create () {
        stage = new Stage();
		viewport = new StretchViewport(0,0);
		StageCreatorScreen stageCreatorScreen = new StageCreatorScreen(this);
		//UserGameScreen userGameScreen = new UserGameScreen(this);
	//	MainScreen mainScreen = new MainScreen(this);
		setScreen(stageCreatorScreen);
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
