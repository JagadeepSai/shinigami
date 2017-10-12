package com.mygdx.game.InputListerners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by root on 11/10/17.
 */

public class StageScrollListener implements InputProcessor {
    private Vector3 prevDragPos;
    Stage stage;
    boolean state = false;

    public StageScrollListener(Stage st) {
        this.stage = st;
    }

    @Override
   public boolean touchDown (int x, int y, int pointer, int button) {
        state= true;
        return  true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        prevDragPos = null ;
        if(state){

        }
        return  false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        state = false;
        x = Gdx.input.getX(pointer);
        y = Gdx.input.getY(pointer);
        if(prevDragPos == null) prevDragPos = new Vector3(0, y, 0);

        stage.getCamera().position.add(0, y - prevDragPos.y, 0);
        prevDragPos.set(0, y, 0);
        return  false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
