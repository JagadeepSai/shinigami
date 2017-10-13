package com.mygdx.game.Screens.Transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MainClass;

import com.mygdx.game.GifDecoder;

/**
 * Created by root on 13/10/17.
 */

public class TransitionScreen extends ScreenAdapter {
    MainClass game;
    Animation<TextureRegion> animation;
    float elapsed;
   // GifDecoder gifDecoder;

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio1 = 16/9;

    public TransitionScreen(MainClass game) {
        this.game = game;
    }

    @Override
    public void show () {

        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Transi/drop.gif").read());

    }

    @Override
    public void render(float delta) {

        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.stage.getBatch().begin();
        game.stage.getBatch().draw(animation.getKeyFrame(elapsed), 0,0,GameWidth,GameWidth/AspectRatio1);
        game.stage.getBatch().end();

    }

    @Override
    public void dispose () {

    }
}


