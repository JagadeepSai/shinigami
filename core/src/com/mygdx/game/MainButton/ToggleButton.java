package com.mygdx.game.MainButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by root on 9/10/17.
 */

public class ToggleButton extends GeneralButton {
    boolean State ;
    boolean togglestate;

    TextureRegionDrawable t1 ;
    TextureRegionDrawable t2 ;

    public ToggleButton(TextureRegion buttonup, TextureRegion buttondown,boolean state) {
        super(buttonup);
        t1 = new TextureRegionDrawable(buttonup);
        this.State = state;
        togglestate = false;
    }

    public void setToggle(TextureRegion buttondown){
      t2  = new TextureRegionDrawable(buttondown);
        togglestate = true;
    }

    public void change() {
        if (togglestate) {
            if (State) {
           /* float size1 = super.button.getImage().getWidth();
            float size2 = super.button.getImage().getHeight();
            super.button.getImage().setDrawable(t2);
            super.button.getImage().setSize(size1,size2);*/
                super.button.getStyle().up = t2;
                //super.button.getImage().se
//            super.buttonStyle.up = t2;
//            super.buttonStyle.down = t2;
            } else {/*
            float size1 = super.button.getImage().getWidth();
            float size2 = super.button.getImage().getHeight();
            super.button.getImage().setDrawable(t1);
            super.button.getImage().setSize(size1,size2);*/
                super.button.getStyle().up = t1;
                //    super.buttonStyle.up = t1;
                //   super.buttonStyle.down = t1;
            }
            State = !State;
        }
    }

    public boolean getState(){
        return State;
    }

}
