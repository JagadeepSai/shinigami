package com.mygdx.game.Interface;

/**
 * Created by suraj on 25/10/17.
 */

public interface SaveToDatabase {
    public boolean save(String id, String name,String username,String json);
    public String[][] get();
}
