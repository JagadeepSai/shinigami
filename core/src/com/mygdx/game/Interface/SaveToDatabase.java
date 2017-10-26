package com.mygdx.game.Interface;

import java.util.Set;

/**
 * Created by suraj on 25/10/17.
 */

public interface SaveToDatabase {
    public void load();
    public boolean save(String id, String name,String username,String json);
    public String[][] get();
    public boolean rate(String s,boolean b);
    public Set<String> getset();
}
