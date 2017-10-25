package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;

import java.util.Date;

public class Stage {
    public String name;
    public int nooflikes;
    public String username;
    public Date id;
    public float height;
    public int noofobstacles;
    public float [][] obstacles;
    public Stage(float h, int noof,String n,String username){
        height=h;
        name = n;
        this.username = username;
        id=new Date();
        String s = id.toString();
        noofobstacles=noof;
        obstacles = new float[noof][3];
    }
}
