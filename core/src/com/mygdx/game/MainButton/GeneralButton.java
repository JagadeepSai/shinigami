package com.mygdx.game.MainButton;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class GeneralButton  {

   public ImageButton button ;
    ImageButtonStyle buttonStyle;

    public GeneralButton(TextureRegion buttonup, TextureRegion buttondown ) {
        buttonStyle = new ImageButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonup));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(buttondown));
        button = new ImageButton(buttonStyle);
    }
    public GeneralButton(TextureRegion buttonup, TextureRegion buttondown ,boolean state){
        buttonStyle = new ImageButtonStyle();
        if (!state) {
            buttonStyle.up = new TextureRegionDrawable(buttonup);
        }
        else
            buttonStyle.up = new TextureRegionDrawable(buttondown);

        button = new ImageButton(buttonStyle);
    }

    public GeneralButton(TextureRegion buttonup){
        TextureRegionDrawable up = new TextureRegionDrawable(buttonup);
        button = new ImageButton(up);
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

    public  void setOrigin(float  x ,float y){
        button.setOrigin(x,y);
    }

    public Vector3 getPosition(){
        Vector3 position = new Vector3(0,0,0);
        position.x = button.getX();
        position.y = button.getY();
        return position;
    }




}
