package com.mygdx.game.MainButton;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;

/**
 * Created by root on 9/10/17.
 */

public class BallButton {

   public GeneralButton ontapbutton;

    public BallButton(float width,float height,TextureRegion buttonup){
        ontapbutton = new GeneralButton(buttonup);
        ontapbutton.setWidth(width);
        ontapbutton.setHeight(height);
        ontapbutton.setOrigin(-width/2,-height/2);
    }
    public void setPosition(float x, float y){
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


}
