package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;

public class Stage {
    public String name;
    public float height;
    public int noofobstacles;
    public float [][] obstacles;
    public Stage(float h, int noof,String n){
        height=h;
        name = n;
        noofobstacles=noof;
        obstacles = new float[noof][3];
    }
}
