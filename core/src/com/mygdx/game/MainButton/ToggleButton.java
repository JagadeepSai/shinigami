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

    TextureRegionDrawable t1 ;
    TextureRegionDrawable t2 ;

    public ToggleButton(TextureRegion buttonup, TextureRegion buttondown,boolean state) {
        super(buttonup,buttondown,state);
        t1 = new TextureRegionDrawable(buttonup);
        t2 = new TextureRegionDrawable(buttondown);
        this.State = state;

    }
    public void change() {
            if (State) {
                super.button.getStyle().up = t1;

            }
            else {
                super.button.getStyle().up = t2;
            }
            State = !State;
    }
    public boolean getState(){
        return State;
    }
}
