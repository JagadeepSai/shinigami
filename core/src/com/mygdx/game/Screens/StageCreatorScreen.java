package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.tools.flame.EventManager;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Assets;
import com.mygdx.game.InputListerners.StageScrollListener;
import com.mygdx.game.MainButton.BallButton;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainButton.ToggleButton;
import com.mygdx.game.MainClass;
import com.mygdx.game.Stage;

import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.List;

import sun.rmi.runtime.Log;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

/**
 * Created by root on 8/10/17.
 */



public class StageCreatorScreen extends ScreenAdapter{
    Assets assets;
    MainClass game ;
    String StageName;
    Stage PStage;
    float negative_height;
    float posiive_height;
    ArrayList<BallButton> obs ;
    int total_count = 0;
    int total = 0;


    GeneralButton backbutton;

    ToggleButton box1;
    ToggleButton box2;
    ToggleButton box3;
    ToggleButton box4;

    Group fg = new Group();
    Group bg = new Group();

    boolean scrollmode = true;
    boolean pinchmode = false;
    boolean delmode = false;

    BallButton currentTap;
    int current_index = 0;
    int current_id = 0;
    Vector2 current_center  ;
    float Zoom_len = 0;
    float pre_zoom = 0;

    //For Scroll
    boolean state;
    float height = 0;

    boolean inbox = false;
    private Vector3 prevDragPos;

    Skin skin = new Skin(Gdx.files.internal("neon-ui.json"));
    String item;


    Image back;

    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio1 = 16/9;


    class ObsListener implements GestureDetector.GestureListener {

        //For Scroll
        float height = 0;
        private Vector3 prevDragPos;

        public ObsListener() {

        }

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            // state = true;
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            // prevDragPos = null ;
            if( game.stage.touchDown((int)x,(int)y,0,button)){

                System.out.println("Stage Tap");
                System.out.println("Current_id: " + Integer.toString(current_id));
                if (pinchmode ) {
                    System.out.println("Stage Tap Inside Pinchmode");


                    if(!(total==0) && obs.get(0).select ) {
                        pre_zoom = 0;
                        current_id = obs.get(0).getCurrent_id();
                        for (int i = 0; i < obs.size(); i++) {
                            if (obs.get(i).getId() == current_id) {
                                currentTap = obs.get(i);
                                current_index = i;
                                current_center = obs.get(i).center;
                            }
                        }

                    }
                    System.out.println("Current_id: " + Integer.toString(current_id));
                }
                if(delmode) {
                    System.out.println("Stage Tap Inside Delmode ");

                    if (obs.get(0).select) {
                        pre_zoom = 0;
                        current_id = obs.get(0).getCurrent_id();
                        for (int i = 0; i < obs.size(); i++) {
                            if (obs.get(i).getId() == current_id) {
                                //total_count--;
                                total--;
                                obs.get(i).ontapbutton.button.remove();
                                obs.remove(i);

                            }
                        }
                    }
                    System.out.println("Current_id: " + Integer.toString(current_id));
                }

            }
            else {
                System.out.println("Inside Tap");
                System.out.println("Current_id: " + Integer.toString(current_id));

                if (scrollmode) {
                       /*if(delmode) {
                           if (obs.get(0).select) {
                               pre_zoom = 0;
                               current_id = obs.get(0).getCurrent_id();
                               for (int i = 0; i < obs.size(); i++) {
                                   if (obs.get(i).getId() == current_id) {
                                       obs.get(current_id).ontapbutton.button.remove();
                                       obs.remove(i);

                                   }
                               }
                           }
                       }else*/
                    if (pinchmode ) {
                        if(!(total==0) && obs.get(0).select ) {
                            pre_zoom = 0;
                            current_id = obs.get(0).getCurrent_id();
                            for (int i = 0; i < obs.size(); i++) {
                                if (obs.get(i).getId() == current_id) {
                                    currentTap = obs.get(i);
                                    current_index = i;
                                    current_center = obs.get(i).center;
                                }
                            }
                            System.out.println(current_id);
                            // obs.get(current_index).ontapbutton.setImage("icons/Star.png");
                        }
                    } else {
                        if (!delmode) {
                            obs.add(new BallButton(GameWidth / 3, GameWidth / (3 * AspectRatio), assets.Obstacle));
                            obs.get(total).setPosition(x, GameHeight - y + height);
                            obs.get(total).ontapbutton.button.setZIndex(10);
                            obs.get(total).setId(total_count);
                            //    Gdx.app.log("X", Float.toString(x));
                            //    Gdx.app.log("Y", Float.toString(y));
                     /* obs.get(total_count).setPosition(Gdx.input.getX(),GameHeight- Gdx.input.getY());
                     obs.setPosition(positions.get(count).x,positions.get(count).y)*/
                            bg.addActor(obs.get(total).ontapbutton.button);
                            if(posiive_height < obs.get(total).getPosition().y + obs.get(total).ontapbutton.getHeight()) posiive_height = obs.get(total).getPosition().y + obs.get(total).ontapbutton.getHeight();

                            if(negative_height  > obs.get(total).getPosition().y ) negative_height = obs.get(total).getPosition().y;

                            total_count++;
                            total++;
                        }
                    }
                }


            }

            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            if (scrollmode) {
              /*  y = Gdx.input.getY();

                if (prevDragPos == null) prevDragPos = new Vector3(0, y, 0);
                game.stage.getCamera().position.add(0, y - prevDragPos.y, 0);
                height = height + y - prevDragPos.y;
                System.out.println("Height: " + Float.toString(height));
                backbutton.setPosition(backbutton.getPosition().x, backbutton.getPosition().y + y - prevDragPos.y);

                box1.setPosition(box1.getPosition().x, box1.getPosition().y + y - prevDragPos.y);
                box2.setPosition(box2.getPosition().x, box2.getPosition().y + y - prevDragPos.y);
                box3.setPosition(box3.getPosition().x, box3.getPosition().y + y - prevDragPos.y);

                prevDragPos.set(0, y, 0);*/

                game.stage.getCamera().position.add(0,deltaY,0);

                height = height + deltaY;
                //  System.out.println("Height: " + Float.toString(height));

                backbutton.setPosition(backbutton.getPosition().x, backbutton.getPosition().y +deltaY);
                box1.setPosition(box1.getPosition().x, box1.getPosition().y +deltaY);
                box2.setPosition(box2.getPosition().x, box2.getPosition().y +deltaY);
                box3.setPosition(box3.getPosition().x, box3.getPosition().y +deltaY);
                box4.setPosition(box4.getPosition().x, box4.getPosition().y +deltaY);

            }

            return false;

        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            if(pinchmode) {
                Zoom_len = distance - initialDistance;
                if (abs(-pre_zoom + Zoom_len) < 50) {

                    System.out.println("Zoom:  Current id : " + Integer.toString(current_id));

                    float width = obs.get(current_index).ontapbutton.getWidth() - pre_zoom + Zoom_len;
                    if (width > 50) {
                        float factor = width / obs.get(current_index).ontapbutton.getWidth();
                        //   obs.get(current_index).ontapbutton.button.scaleBy(factor);
                        obs.get(current_index).ontapbutton.button.setWidth(width);

                        obs.get(current_index).ontapbutton.setHeight(obs.get(current_index).ontapbutton.getWidth() / AspectRatio1);
                        //obs.get(current_index).ontapbutton.button.getImage().setFillParent(true);
                        obs.get(current_index).ontapbutton.button.getImageCell().expand().fill();
                        // game.stage.addActor(obs.get(current_index).ontapbutton.button);
                        obs.get(current_index).setPosition(obs.get(current_index).center.x, obs.get(current_index).center.y);
                        // System.out.println(obs.get(current_index).getPosition().x);
                        // System.out.println(obs.get(current_index).getPosition().y);


                        bg.addActor(obs.get(current_index).ontapbutton.button);

                        pre_zoom = Zoom_len;


                        if(posiive_height < obs.get(current_index).getPosition().y + obs.get(current_index).ontapbutton.getHeight()) posiive_height = obs.get(current_index).getPosition().y + obs.get(current_index).ontapbutton.getHeight();

                        if(negative_height  > obs.get(current_index).getPosition().y ) negative_height = obs.get(total).getPosition().y;


                    }
                }
            }
            return false;

        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }

        @Override
        public void pinchStop() {

        }
    }



    public  StageCreatorScreen (MainClass gam, String nam) {
        this.game = gam;
        this.StageName = nam;
        this.assets=game.assets;
        obs = new ArrayList<BallButton>();
        item = "icons/MeshFill-260.png";
        ObsListener obsListener = new ObsListener();

        skin.addRegions(new TextureAtlas(Gdx.files.internal("neon-ui.atlas")));
        skin.load(Gdx.files.internal("neon-ui.json"));

        skin.getFont("font").getData().setScale(AspectRatio1,AspectRatio1);



        backbutton = new GeneralButton(assets.BackArrow,assets.BackArrow);
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio1);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio1) + backbutton.getHeight()/2);
        backbutton.button.setZIndex(1);


        box1 = new ToggleButton(assets.NavObs,assets.NavObs,true);
        box1.setWidth(GameWidth/5);
        box1.setHeight(box1.getWidth()/AspectRatio1);
        box1.setPosition(GameWidth/30,GameHeight/35);
        box1.button.setZIndex(1);

        box2 = new ToggleButton(assets.Resize,assets.Resize,true);
        box2.setWidth(GameWidth/5);
        box2.setHeight(box2.getWidth()/AspectRatio1);
        box2.setPosition(GameWidth/4+GameWidth/30,GameHeight/35);
        box2.button.setZIndex(1);


        box3 = new ToggleButton(assets.DeleteInBox,assets.DeleteInBox,true);
        box3.setWidth(GameWidth/5);
        box3.setHeight(box3.getWidth()/AspectRatio1);
        box3.setPosition(GameWidth/2+GameWidth/30,GameHeight/35);
        box3.button.setZIndex(1);

        box4 = new ToggleButton(assets.Save,    assets.Save,true);
        box4.setWidth(GameWidth/5);
        box4.setHeight(box4.getWidth()/AspectRatio1);
        box4.setPosition(3*GameWidth/4+GameWidth/30,GameHeight/35);
        box4.button.setZIndex(1);




       /* game.stage.addListener(new InputListener () {


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state= true;
                return  true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer)
            {
                state = false;

                y = Gdx.input.getY(pointer);

                if(prevDragPos == null) prevDragPos = new Vector3(0, y, 0);
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
                        obs.get(total_count).ontapbutton.button.setZIndex(10);
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
*/
        //Input Handling


        InputMultiplexer multiplexer = new InputMultiplexer();

        multiplexer.addProcessor(new GestureDetector(obsListener));
        multiplexer.addProcessor(game.stage);

        Gdx.input.setInputProcessor(multiplexer);

        back = new Image(new Texture(Gdx.files.internal("background.png")));
        //  game.stage.addActor(back);
        //  game.stage.setDebugAll(true);

        game.stage.addActor(bg);
        game.stage.addActor(fg);

        fg.addActor(box1.button);
        fg.addActor(box2.button);
        fg.addActor(box3.button);
        fg.addActor(box4.button);
        fg.addActor(backbutton.button);



        /*game.stage.addActor(box1.button);
        game.stage.addActor(box2.button);
        game.stage.addActor(box3.button);
        game.stage.addActor(box4.button);

        game.stage.addActor(backbutton.button);*/
        //   game.stage.addActor(name);

    }


    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if(game.button_tune_play)  game.assets.button_tune.play();

            // game.stage.getCamera().translate(0,0,0);
            game.stage.getCamera().position.set(GameWidth/2,GameHeight/2,0);
            game.stage.getCamera().update();

            game.getScreen().hide();
            game.stage.clear();
            //game.stage.getCamera().position.add(0,height,0);

            //  game.stage.;
            UserGameScreen userGameScreen = new UserGameScreen(game);
            game.setScreen(userGameScreen);
        }

        game.stage.act();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.stage.draw();
    }

    @Override
    public void show() {

        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(game.button_tune_play)  game.assets.button_tune.play();

                // game.stage.getCamera().translate(0,0,0);
                game.stage.getCamera().position.set(GameWidth/2,GameHeight/2,0);
                game.stage.getCamera().update();

                game.getScreen().hide();
                game.stage.clear();
                //game.stage.getCamera().position.add(0,height,0);

                //  game.stage.;
                UserGameScreen userGameScreen = new UserGameScreen(game);
                game.setScreen(userGameScreen);
            }
        });

        box4.setTouchable();
        box4.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(game.button_tune_play)   game.assets.button_tune.play();

                if(scrollmode && !pinchmode && !delmode) {
                    total_count--;
                    total--;
                    obs.get(total).ontapbutton.button.remove();
                    System.out.println("Removed ,tap on box4 : " + Integer.toString(total));
                    System.out.println("Current id : " + Integer.toString(current_id));
                    //obs.get(total_count).ontapbutton.button.addAction(Actions.removeActor());
                    obs.remove(total);

                }

                scrollmode = true;
                delmode = pinchmode = false;
                float Stage_height = posiive_height - negative_height;
                /*for (int i = 0 ; i < obs.size() ; i++){
                    if(obs.get(i).getId() == posiive_height) Stage_height += obs.get(i).getPosition().y + obs.get(posiive_height).ontapbutton.getHeight();
                    if(obs.get(i).getId() == negative_height) Stage_height -= obs.get(i).getPosition().y ;
                }*/

               // float Stage_height = obs.get(posiive_height).getPosition().y + obs.get(posiive_height).ontapbutton.getHeight() - obs.get(negative_height).getPosition().y ;
                PStage = new Stage(Stage_height,obs.size());
                float xcor, ycor ,radius;
                for (int i = 0; i < obs.size(); i++) {
                    radius = obs.get(i).ontapbutton.button.getImage().getWidth()/2/GameWidth;
                    xcor = obs.get(i).center.x/GameWidth - 0.5f;
                    ycor = obs.get(i).center.y/GameWidth;
                    PStage.obstacles[i] = new float[]{radius, xcor, ycor};
                }
                FileHandle fileHandle = Gdx.files.local("usersaved.txt");
                Json json = new Json();
                fileHandle.writeString( Long.toString(System.currentTimeMillis())+ ' ' +' '+StageName +' '+ json.toJson(PStage) +'\n',true);
                System.out.println(fileHandle.readString());

                game.stage.getCamera().position.set(GameWidth/2,GameHeight/2,0);
                game.stage.getCamera().update();

                game.getScreen().hide();
                game.stage.clear();
                //game.stage.getCamera().position.add(0,height,0);

                //  game.stage.;
                UserGameScreen userGameScreen = new UserGameScreen(game);
                game.setScreen(userGameScreen);

            }


        });

        box3.setTouchable();
        box3.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                if(scrollmode && !pinchmode && !delmode) {
                    total_count--;
                    total--;
                    obs.get(total).ontapbutton.button.remove();
                    System.out.println("Removed ,tap on box3 : " + Integer.toString(total));
                    System.out.println("Current id : " + Integer.toString(current_id));
                    //obs.get(total_count).ontapbutton.button.addAction(Actions.removeActor());
                    obs.remove(total);

                }

                scrollmode = delmode = true;
                pinchmode = false;
            }
        });

        box2.setTouchable();
        box2.button.addListener(new ClickListener(){


            @Override
            public void clicked(InputEvent event, float x, float y){

                if(scrollmode && !pinchmode && !delmode) {
                    total_count--;
                    total--;
                    obs.get(total).ontapbutton.button.remove();
                    System.out.println("Removed ,tap on box2 : " + Integer.toString(total));
                    System.out.println("Current id : " + Integer.toString(current_id));
                    //obs.get(total_count).ontapbutton.button.addAction(Actions.removeActor());
                    obs.remove(total);


                }

                scrollmode = true;
                pinchmode = true;
                delmode = false;
            }
        });

        box1.setTouchable();
        box1.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(scrollmode && !pinchmode && !delmode) {
                    total_count--;
                    total--;
                    obs.get(total).ontapbutton.button.remove();
                    System.out.println("Removed ,tap on box1 : " + Integer.toString(total));
                    System.out.println("Current id : " + Integer.toString(current_id));
                    //obs.get(total_count).ontapbutton.button.addAction(Actions.removeActor());
                    obs.remove(total);
                }
                scrollmode = true;
                delmode = pinchmode = false;
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