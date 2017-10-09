package com.mygdx.game.MainButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.tools.flame.EventManager;

/**
 * Created by root on 2/10/17.
 */

public class GeneralButton  {

   public ImageButton button ;
    ImageButtonStyle buttonStyle;

    public GeneralButton(String buttonup, String buttondown ) {

        if(buttondown == "") buttondown = buttonup;
        buttonStyle = new ImageButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(buttonup))));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(buttondown))));
        button = new ImageButton(buttonStyle);
    }
    public GeneralButton(String buttonup){
        TextureRegionDrawable up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(buttonup))));
        button = new ImageButton(up);
    }

    public void setHover(String buttonover){
        buttonStyle.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(buttonover))));
        button.setStyle(buttonStyle);
    }

    public void setWidth(float width){
        button.setWidth(width);
    }

    public void setHeight(float height){
        button.setHeight(height);
    }

    public void setPosition(float x , float y){
        button.setPosition(x,y);
    }

    public float getWidth(){
        return  button.getWidth();
    }

    public float getHeight(){
        return button.getHeight();
    }

    public void setTouchable(){
        button.setTouchable(Touchable.enabled);
    }

    public void addChangeListener(ChangeListener listener){
        button.addListener(listener);
    }

    public void addClickListener(ClickListener listener) { button.addListener(listener);  }

    public  void setOrigin(float  x ,float y){
        button.setOrigin(x,y);
    }

    public void rotate(float x){
        button.rotateBy(x);
    }

    public void addAction(Action action){
        button.addAction(action);
    }

    public Vector3 getPosition(){
        Vector3 position = new Vector3(0,0,0);
        position.x = button.getX();
        position.y = button.getY();
        return position;
    }




}
