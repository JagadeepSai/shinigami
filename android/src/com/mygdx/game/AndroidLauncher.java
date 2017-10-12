package com.mygdx.game;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	public  AndroidLogin androidLogin;
	@Override
	protected void onCreate (Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		androidLogin=new AndroidLogin(this);
		MainClass mainClass= new MainClass();
		mainClass.setLogin(androidLogin);
		initialize(mainClass, config);
	}
}
