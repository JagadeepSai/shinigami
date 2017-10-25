package com.mygdx.game.Interface;

/**
 * Created by suraj on 24/10/17.
 */

public interface CreateAccount {
    public boolean createAccount(String username,String password, String password2);
    public boolean check(String username,String password);
}
