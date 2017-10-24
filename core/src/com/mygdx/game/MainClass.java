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
import com.mygdx.game.Screens.Transitions.TransitionScreen;
import com.mygdx.game.Screens.UserGameScreen;

public class MainClass extends Game {
	public Stage stage;
	public Viewport viewport ;
	public Login authenticate;
	public Assets assets;
	@Override
	public void create () {
        stage = new Stage();
		viewport = new StretchViewport(0,0);
		//UserGameScreen userGameScreen = new UserGameScreen(this);
		assets.load();
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
