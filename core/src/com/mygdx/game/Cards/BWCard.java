package com.mygdx.game.Cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Assets;
import com.mygdx.game.MainButton.GeneralButton;

/**
 * Created by root on 13/10/17.
 */

public class BWCard extends Card {

    GeneralButton sharebutton;
    GeneralButton deletebutton;
    GeneralButton playbutton;
    Assets assets; //////////////////////////////////////// Lot to change;;;

    public BWCard(String string, float g_width, float aspect_ratio, boolean white, BitmapFont font, Assets assets) {
        super(string,g_width,aspect_ratio,font,assets);
        this.assets=assets;
        if(white){
            playbutton = new GeneralButton(assets.CircledPlay);



            sharebutton = new GeneralButton(assets.Connect);


            deletebutton = new GeneralButton(assets.Delete);
            setL_color(Color.BLACK);
            setDp(assets.White);
        }
        else{
            playbutton = new GeneralButton(assets.CircledPlayWhite);

            sharebutton = new GeneralButton(assets.ConnectWhite);

            deletebutton = new GeneralButton(assets.DeleteWhite);

            setL_color(Color.WHITE);
            setDp(assets.Black);
        }
        playbutton.setWidth(g_width/4.2f);
        playbutton.setHeight(playbutton.getWidth()*aspect_ratio/2);
        playbutton.setPosition(g_width - playbutton.getWidth()*1.2f ,-group.getHeight()/16);

        sharebutton.setWidth(group.getWidth()/6.8f);
        sharebutton.setHeight(sharebutton.getWidth()*aspect_ratio/2);
        sharebutton.setPosition(playbutton.getPosition().x -sharebutton.getWidth()*1.5f , 1.75f*group.getHeight()/5);

        deletebutton.setWidth(group.getWidth()/8f);
        deletebutton.setHeight(deletebutton.getWidth()*aspect_ratio/2);
        deletebutton.setPosition(sharebutton.getPosition().x ,-group.getHeight() + 4.5f*deletebutton.getHeight()/3);

        group.addActor(sharebutton.button);
        group.addActor(deletebutton.button);
        group.addActor(playbutton.button);
    }
}
