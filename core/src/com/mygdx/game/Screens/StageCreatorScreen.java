package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
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
import com.mygdx.game.InputListerners.StageScrollListener;
import com.mygdx.game.MainButton.BallButton;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainButton.ToggleButton;
import com.mygdx.game.MainClass;

import java.util.ArrayList;
import java.util.List;

import sun.rmi.runtime.Log;

import static java.lang.Math.abs;

/**
 * Created by root on 8/10/17.
 */



public class StageCreatorScreen extends ScreenAdapter{

    MainClass game ;
    ArrayList<BallButton> obs ;
    int total_count = 0;
    int total = 0;


    GeneralButton backbutton;
    TextField name;
    ToggleButton box1;
    ToggleButton box2;
    ToggleButton box3;
    ToggleButton box4;

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
                               obs.add(new BallButton(GameWidth / 3, GameWidth / (3 * AspectRatio), item));
                               obs.get(total).setPosition(x, GameHeight - y + height);
                               obs.get(total).ontapbutton.button.setZIndex(10);
                               obs.get(total).setId(total_count);
                               //    Gdx.app.log("X", Float.toString(x));
                               //    Gdx.app.log("Y", Float.toString(y));
                     /* obs.get(total_count).setPosition(Gdx.input.getX(),GameHeight- Gdx.input.getY());
                     obs.setPosition(positions.get(count).x,positions.get(count).y)*/
                               game.stage.addActor(obs.get(total).ontapbutton.button);
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
                        obs.get(current_index).setPosition(current_center.x, current_center.y);
                        // System.out.println(obs.get(current_index).getPosition().x);
                        // System.out.println(obs.get(current_index).getPosition().y);

                        game.stage.addActor(obs.get(current_index).ontapbutton.button);

                        pre_zoom = Zoom_len;

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



    public  StageCreatorScreen (MainClass gam) {
        this.game = gam;
        obs = new ArrayList<BallButton>();
        item = "icons/MeshFill-260.png";
        ObsListener obsListener = new ObsListener();

        skin.addRegions(new TextureAtlas(Gdx.files.internal("neon-ui.atlas")));
        skin.load(Gdx.files.internal("neon-ui.json"));

        skin.getFont("font").getData().setScale(AspectRatio1,AspectRatio1);



        backbutton = new GeneralButton("icons/Back Arrow-260.png","");
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio1);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio1) + backbutton.getHeight()/2);
        backbutton.button.setZIndex(1);

        name = new TextField("", skin);

        name.setMessageText("Stage Name");
        name.setWidth(4*GameWidth/6);
        name.setHeight(backbutton.getHeight());
        name.setPosition(GameWidth/4,(5*GameHeight)/(6*AspectRatio1) + backbutton.getHeight()/2);


        box1 = new ToggleButton("icons/MenuCrop.png","icons/MenuCrop.png",true);
        box1.setWidth(GameWidth/4);
        box1.setHeight(GameHeight/6);
        box1.setPosition(0,0);
        box1.button.setZIndex(1);

        box2 = new ToggleButton("icons/MenuCrop.png","icons/MenuCrop.png",true);
        box2.setWidth(GameWidth/4);
        box2.setHeight(GameHeight/6);
        box2.setPosition(GameWidth/4,0);
        box2.button.setZIndex(1);


        box3 = new ToggleButton("icons/MenuCrop.png","icons/MenuCrop.png",true);
        box3.setWidth(GameWidth/4);
        box3.setHeight(GameHeight/6);
        box3.setPosition(GameWidth/2,0);
        box3.button.setZIndex(1);

        box4 = new ToggleButton("icons/MenuCrop.png","icons/MenuCrop.png",true);
        box4.setWidth(GameWidth/4);
        box4.setHeight(GameHeight/6);
        box4.setPosition(3*GameWidth/4,0);
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
        game.stage.setDebugAll(true);

        game.stage.addActor(box1.button);
        game.stage.addActor(box2.button);
        game.stage.addActor(box3.button);
        game.stage.addActor(box4.button);

        game.stage.addActor(backbutton.button);
     //   game.stage.addActor(name);

    }


    @Override
    public void render(float delta) {
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

                if(scrollmode && !pinchmode) {
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
            }
        });

        box3.setTouchable();
        box3.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

               if(scrollmode && !pinchmode) {
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

                if(scrollmode && !pinchmode) {
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

               if(scrollmode && !pinchmode) {
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


