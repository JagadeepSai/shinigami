package com.mygdx.game;


/**
 * The way stages are stored in the whole game.
 */
public class Stage {
    public float height;
    public int noofobstacles;
    public float [][] obstacles;

    /**
     * Constructor
     * @param h the height of the stage
     * @param noof number of obstacles in the stage
     */
    public Stage(float h, int noof){
        height=h;
        noofobstacles=noof;
        obstacles = new float[noof][3];
    }
}