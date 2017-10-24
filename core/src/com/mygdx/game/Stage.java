package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;

public class Stage {
    public int height;
    public int noofobstacles;
    public float [][] obstacles;
    public Stage(int h, int noof){
        height=h;
        noofobstacles=noof;
        obstacles = new float[noof][3];
    }
}
