package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.InputListerners.StageScrollListener;
import com.mygdx.game.MainButton.BallButton;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainButton.ToggleButton;
import com.mygdx.game.MainClass;

import java.util.ArrayList;
import java.util.List;

import sun.rmi.runtime.Log;

/**
 * Created by root on 8/10/17.
 */

public class StageCreatorScreen extends ScreenAdapter {

    MainClass game ;
    List<BallButton> obs ;
    int total_count = 0;
    boolean state;
    GeneralButton backbutton;

    ToggleButton box1;
    ToggleButton box2;
    ToggleButton box3;

    boolean inbox = false;
    String item;


    Image back;

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio1 = 16/9;
    float height = 0;


    Vector2 touch = new Vector2();
    private Vector3 prevDragPos;

    public  StageCreatorScreen (MainClass gam) {
        this.game = gam;
        obs = new ArrayList<BallButton>();
        item = "icons/MeshFill-260.png";

        backbutton = new GeneralButton("icons/Back Arrow-260.png","");
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio1);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio1) + backbutton.getHeight()/2);
        backbutton.button.setZIndex(10);


        box1 = new ToggleButton("icons/MenuCrop.png","",true);
        box1.setWidth(GameWidth/3);
        box1.setHeight(GameHeight/6);
        box1.setPosition(0,0);

        box2 = new ToggleButton("icons/MenuCrop.png","",true);
        box2.setWidth(GameWidth/3);
        box2.setHeight(GameHeight/6);
        box2.setPosition(GameWidth/3,0);

        box3 = new ToggleButton("icons/MenuCrop.png","",true);
        box3.setWidth(GameWidth/3);
        box3.setHeight(GameHeight/6);
        box3.setPosition(2*GameWidth/3,0);


        game.stage.addListener(new InputListener() {

            /*@Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                if(y>GameWidth/4) {
                    game.stage.getCamera().translate(0, -deltaY, 0);
                    game.stage.getCamera().update();
                    //System.out.println(x + ", " + y + ", " + "      " + deltaX + ", " + deltaY);

                }
            }*/
           /* @Override
            public void  tap(InputEvent event, float x, float y, int count, int button) {
                obs.add(new BallButton(GameWidth/3,GameWidth/(3*AspectRatio)));
                obs.get(total_count).setPosition(x,y);
                //  obs.get(total_count).setPosition(Gdx.input.getX(),GameHeight- Gdx.input.getY());
                // obs.setPosition(positions.get(count).x,positions.get(count).y)

                game.stage.addActor(obs.get(total_count).ontapbutton.button) ;
                total_count++;
            }
        }*/
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state= true;
                /*if(y<GameHeight/4){
                    System.out.println("X: " + Float.toString(x) + ", Y: "+Float.toString(y)+" "+Float.toString(GameHeight/4));
                    return false;}
                else{ return  true;}*/
                return  true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer)
            {
                state = false;

                y = Gdx.input.getY(pointer);

                if(prevDragPos == null) prevDragPos = new Vector3(0, y, 0);
                //System.out.println("X: " + Float.toString(x) + ", Y: "+Float.toString(prevDragPos.y));
                game.stage.getCamera().position.add(0, y - prevDragPos.y, 0);
                height = height + y - prevDragPos.y;
                System.out.println("Height: " + Float.toString(height));
                backbutton.setPosition(backbutton.getPosition().x,backbutton.getPosition().y + y - prevDragPos.y);

                box1.setPosition(box1.getPosition().x,box1.getPosition().y + y - prevDragPos.y);
                box2.setPosition(box2.getPosition().x,box2.getPosition().y + y - prevDragPos.y);
                box3.setPosition(box3.getPosition().x,box3.getPosition().y + y - prevDragPos.y);

                prevDragPos.set(0, y, 0);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                prevDragPos = null ;
                if(inbox){inbox = false;}
                else {
                    if (state) {
                        obs.add(new BallButton(GameWidth / 3, GameWidth / (3 * AspectRatio), item));
                        obs.get(total_count).setPosition(x, y);
                        // Gdx.app.log("X", Float.toString(x));
                        // Gdx.app.log("Y", Float.toString(y));
                        //  obs.get(total_count).setPosition(Gdx.input.getX(),GameHeight- Gdx.input.getY());
                        // obs.setPosition(positions.get(count).x,positions.get(count).y)

                        game.stage.addActor(obs.get(total_count).ontapbutton.button);
                        total_count++;
                    }
                }
            }
        });


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(game.stage);
        multiplexer.addProcessor(new StageScrollListener(game.stage));

        Gdx.input.setInputProcessor(multiplexer);
      //  Gdx.input.setInputProcessor(game.stage);

        back = new Image(new Texture(Gdx.files.internal("background.png")));
        game.stage.addActor(back);

        game.stage.addActor(box1.button);
        game.stage.addActor(box2.button);
        game.stage.addActor(box3.button);

        game.stage.addActor(backbutton.button);

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

        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getScreen().hide();
                game.stage.clear();
                game.stage.getCamera().translate(0,-height,0);

                //  game.stage.;
                UserGameScreen userGameScreen = new UserGameScreen(game);
                game.setScreen(userGameScreen);
            }
        });

        box2.setTouchable();
        box2.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                item = "icons/Star.png";
                inbox = true;
            }
        });

        box1.setTouchable();
        box1.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                item = "icons/MeshFill-260.png";
                inbox = true;
            }
        });

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


