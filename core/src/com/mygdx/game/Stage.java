package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;

import java.util.Date;

public class Stage {
    public float height;
    public int noofobstacles;
    public float [][] obstacles;
    public Stage(float h, int noof){
        height=h;
        noofobstacles=noof;
        obstacles = new float[noof][3];
    }
    public Stage(){
        obstacles=new float[1000][3];
    }
}