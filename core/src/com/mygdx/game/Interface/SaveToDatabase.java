package com.mygdx.game.Interface;

/**
 * Created by suraj on 25/10/17.
 */

public interface SaveToDatabase {
    public boolean save(String id, String name,String json);
    public String[][] get();
}
