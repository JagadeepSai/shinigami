package com.mygdx.game.MainButton;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by root on 9/10/17.
 */

public class BallButton {

   public GeneralButton ontapbutton;
    int id;
    public static  int current_id;
    public static boolean select;
    public  Vector2 center;

    public BallButton(float width,float height,TextureRegion buttonup){
        ontapbutton = new GeneralButton(buttonup);
        ontapbutton.setWidth(width);
        ontapbutton.setHeight(height);
        ontapbutton.setOrigin(-width/2,-height/2);
        center = new Vector2(0,0);

        ontapbutton.setTouchable();
        ontapbutton.button.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Touched");
                System.out.println(id);

                current_id = id;
                select = true;
                return true;
            }
        });
    }
    public void setPosition(float x, float y){
        center.set(x,y);
        ontapbutton.setPosition(x-ontapbutton.getWidth()/2,y-ontapbutton.getHeight()/2);
    }

    public void reSize(float x,float y){
        ontapbutton.setWidth(x);
        ontapbutton.setHeight(y);
        ontapbutton.setOrigin(x/2,y/2);
    }

    public Vector2 getPosition(){
        return new Vector2(ontapbutton.getPosition().x , ontapbutton.getPosition().y);
    }

    public int getId() {
        return id;
    }

    public static int getCurrent_id() {
        return current_id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
