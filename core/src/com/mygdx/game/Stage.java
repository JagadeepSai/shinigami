package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;

public class Stage {
    public int height;
    public int noofobstacles;
    public Vector3 [] obstacles;
    public Stage(int h, int noof){
        height=h;
        noofobstacles=noof;
        obstacles = new Vector3[noof];
    }
}
