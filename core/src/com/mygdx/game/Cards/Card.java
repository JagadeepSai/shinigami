package com.mygdx.game.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainButton.ToggleButton;
import com.mygdx.game.Screens.UserGameScreen;

import java.awt.Font;

/**
 * Created by root on 13/10/17.
 */

public class Card {

    public Group group;
    Image dp;
    Label L_name;
    LabelStyle labelStyle;
    Assets assets;
    Label Username;
    Label Likes;
    Boolean disp_user = false;

    public Card(String string,String username,boolean dis,int likes, float g_width,float aspect_ratio,BitmapFont font, Assets assets) {
        this.assets=assets;
        group = new Group();
        group.setWidth(g_width);
        disp_user = dis;
        group.setHeight(g_width/aspect_ratio);

        dp = new Image(assets.White);
        dp.setSize(group.getWidth(),group.getHeight());
        dp.setPosition(0,0);


        //,group.getHeight()/2.1f

        labelStyle = new LabelStyle(font, Color.WHITE);
        L_name = new Label(string,labelStyle);


       // L_name.setSize(g_width,g_width/aspect_ratio);
        L_name.setPosition(g_width/11,2.5f*group.getHeight()/5); //The Upper Left Corner is 0,0


      //  L_name.setPosition( group.getWidth() / 2f - L_name.getWidth() / 2f, group.getHeight() / 2f - L_name.getHeight() / 2f );

        group.addActor(dp);
        group.addActor(L_name);
        if(disp_user){

            Username = new Label(username,labelStyle);
            Username.setPosition(g_width/11,group.getHeight()/30);
            Username.setColor(Color.GRAY);
            group.addActor(Username);

            Likes = new Label(Integer.toString(likes),labelStyle);
            Likes.setPosition(3.31f*group.getWidth()/6,-group.getHeight() + 4.5f*Likes.getHeight()/1.75f);
            group.addActor(Likes);

        }

    }


    public void L_name_scale(float scale){
        L_name.scaleBy(scale);
    }
    public void setLikes(){
        Likes.setPosition(3.31f*group.getWidth()/6 ,0.8f*Likes.getHeight()/2.5f);
    }
    public void setPosBlack(){
        L_name.setY(2.5f*group.getHeight()/5);

    }
    public  void setPosBlackUsername(){
        Username.setY(group.getHeight()/7f);
    }

    public void setCenter_L_name(boolean ver,float other) {
        if (!ver) {
            L_name.setPosition(dp.getWidth()/2f - L_name.getWidth()/2f, other);
        } else {
            L_name.setPosition(other,dp.getHeight() / 2f - L_name.getHeight() / 2f);
        }
    }
    public void setDp(TextureRegion textureRegion){
        dp.setDrawable(new TextureRegionDrawable(textureRegion));
    }

    public float getHeight(){
        return dp.getHeight();
    }
    public float getWidth(){
        return  dp.getWidth();
    }

    public void setL_color(Color color){
        L_name.setColor(color);
        if(disp_user) Likes.setColor(color);

    }
}
