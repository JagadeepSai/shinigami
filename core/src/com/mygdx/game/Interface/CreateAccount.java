package com.mygdx.game.Interface;



/**
 * Inplimented in AndroidCreateAccount class, in android directory
 */
public interface CreateAccount {
    boolean createAccount(String username,String password, String password2);
    boolean check(String username,String password);
}
