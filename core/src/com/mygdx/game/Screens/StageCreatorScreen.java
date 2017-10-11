package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.mygdx.game.MainButton.BallButton;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/10/17.
 */

public class StageCreatorScreen extends ScreenAdapter {

    MainClass game ;
    List<BallButton> obs ;
    int total_count = 0;

    Image back;

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio1 = 16/9;


    Vector2 touch = new Vector2();
    private Vector3 prevDragPos;

    public  StageCreatorScreen (MainClass gam) {
        this.game = gam;
        obs = new ArrayList<BallButton>();
        game.stage.addListener(new ActorGestureListener() {

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                if(y>GameWidth/4) {
                    game.stage.getCamera().translate(0, -deltaY, 0);
                    game.stage.getCamera().update();
                    //System.out.println(x + ", " + y + ", " + "      " + deltaX + ", " + deltaY);

                }
            }
            

            @Override
            public void  tap(InputEvent event, float x, float y, int count, int button) {
                obs.add(new BallButton(GameWidth/3,GameWidth/(3*AspectRatio)));
                obs.get(total_count).setPosition(x,y);
                //  obs.get(total_count).setPosition(Gdx.input.getX(),GameHeight- Gdx.input.getY());
                // obs.setPosition(positions.get(count).x,positions.get(count).y)

                game.stage.addActor(obs.get(total_count).ontapbutton.button) ;
                total_count++;
            }
        });

        Gdx.input.setInputProcessor(game.stage);

        back = new Image(new Texture(Gdx.files.internal("background.png")));
        game.stage.addActor(back);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*if(Gdx.input.justTouched()) {
            obs.add(new BallButton(GameWidth/3,GameWidth/(3*AspectRatio)));
            obs.get(count).setPosition(Gdx.input.getX(),GameHeight- Gdx.input.getY());
           // obs.setPosition(positions.get(count).x,positions.get(count).y)

            game.stage.addActor(obs.get(count).ontapbutton.button) ;
            count++;
        }*/
        game.stage.draw();
        super.render(delta);
    }

    @Override
    public void show() {
        super.show();
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


