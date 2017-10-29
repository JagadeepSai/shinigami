package com.mygdx.game.Interface;

import java.util.Set;

/**
 * Inplimented in Android savetodatabase class, in android directory
 */
public interface SaveToDatabase {
    void load();
    boolean save(String id, String name,String username,String json);
    String[][] get();
    boolean rate(String s,boolean b);
    Set<String> getset();
}
