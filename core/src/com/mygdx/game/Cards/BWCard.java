package com.mygdx.game.Cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MainButton.GeneralButton;

/**
 * Created by root on 13/10/17.
 */

public class BWCard extends Card {

    GeneralButton sharebutton;
    GeneralButton deletebutton;
    GeneralButton playbutton;

    public BWCard(String string, float g_width, float aspect_ratio, boolean white, BitmapFont font) {
        super(string,g_width,aspect_ratio,font);
       // card = new Card(string,g_width,aspect_ratio);
        playbutton = new GeneralButton("icons/Circled Play-260.png");
        playbutton.setWidth(g_width/4.2f);
        playbutton.setHeight(playbutton.getWidth()*aspect_ratio/2);
        playbutton.setPosition(g_width - playbutton.getWidth()*1.2f ,-group.getHeight()/16);


        sharebutton = new GeneralButton("icons/Connect-500.png");
        sharebutton.setWidth(group.getWidth()/6.8f);
        sharebutton.setHeight(sharebutton.getWidth()*aspect_ratio/2);
        sharebutton.setPosition(playbutton.getPosition().x -sharebutton.getWidth()*1.5f , 1.75f*group.getHeight()/5);

        deletebutton = new GeneralButton("icons/Delete.png");
        deletebutton.setWidth(group.getWidth()/8f);
        deletebutton.setHeight(deletebutton.getWidth()*aspect_ratio/2);
        deletebutton.setPosition(sharebutton.getPosition().x ,-group.getHeight() + 4.5f*deletebutton.getHeight()/3);



        if(white){
            setL_color(Color.BLACK);
            setDp("White.png");
        }
        else{
            setL_color(Color.WHITE);
            setDp("Black.png");
        }

        group.addActor(sharebutton.button);
        group.addActor(deletebutton.button);
        group.addActor(playbutton.button);
    }
}
