package com.mygdx.game.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainButton.ToggleButton;

import java.util.Date;

/**
 * Created by root on 13/10/17.
 */

public class BWCard extends Card {

    public GeneralButton sharebutton;
    public GeneralButton deletebutton;
    public GeneralButton playbutton;
    public ToggleButton Lovebutton;
    public String game;

    Long id;
    Assets assets; //////////////////////////////////////// Lot to change;;;

    public BWCard(String string,String username,boolean dis,int likes,Long id, float g_width, float aspect_ratio, boolean white, BitmapFont font, Assets assets, String game) {
        super(string,username,dis,likes,g_width,aspect_ratio,font,assets);
        this.assets=assets;
        this.id = id;
        this.game = game;
        // Play button;
        if (white) {
            playbutton = new GeneralButton(assets.CircledPlay);
            playbutton.setWidth(g_width/4.2f);
            playbutton.setHeight(playbutton.getWidth()*aspect_ratio/2);

            playbutton.setPosition(g_width - playbutton.getWidth()*1.2f ,-group.getHeight()/16);
            setL_color(Color.BLACK);
            setDp(assets.White);
        }
        else
        {
            playbutton = new GeneralButton(assets.CircledPlayWhite);
            playbutton.setWidth(g_width/4.2f);
            playbutton.setHeight(playbutton.getWidth()*aspect_ratio/2);
            playbutton.setPosition(g_width - playbutton.getWidth()*1.2f ,group.getHeight()/6.5f);

            setL_color(Color.WHITE);
            setDp(assets.Black);
        }

//        playbutton.addClickListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                //setScreen(new )
//
//            }
//        });

        group.addActor(playbutton.button);

        if(!disp_user){

            if(white){
                sharebutton = new GeneralButton(assets.Connect);
                deletebutton = new GeneralButton(assets.Delete);

            }
            else{
                sharebutton = new GeneralButton(assets.ConnectWhite);
                deletebutton = new GeneralButton(assets.DeleteWhite);
            }

            sharebutton.setWidth(group.getWidth()/6.8f);
            sharebutton.setHeight(sharebutton.getWidth()*aspect_ratio/2);

            deletebutton.setWidth(group.getWidth()/8f);
            deletebutton.setHeight(deletebutton.getWidth()*aspect_ratio/2);

            if (white){
                sharebutton.setPosition(playbutton.getPosition().x -sharebutton.getWidth()*1.5f , 1.75f*group.getHeight()/5);
                deletebutton.setPosition(sharebutton.getPosition().x ,-group.getHeight() + 4.5f*deletebutton.getHeight()/3);
            }
            else{
                setPosBlack();
                sharebutton.setPosition(playbutton.getPosition().x -sharebutton.getWidth()*1.5f , 2.25f*group.getHeight()/5);
                deletebutton.setPosition(sharebutton.getPosition().x ,0.8f*deletebutton.getHeight()/3);
            }

            group.addActor(sharebutton.button);
            group.addActor(deletebutton.button);


        }
        else {

                TextureRegion love = new TextureRegion(new Texture(Gdx.files.internal("Love.png")));
                TextureRegion loveIn = new TextureRegion(new Texture(Gdx.files.internal("LoveIn.png")));
                Lovebutton = new ToggleButton(love, loveIn, false);

                if(white) {
                    Lovebutton.setWidth(group.getWidth() / 6.8f);
                    Lovebutton.setHeight(Lovebutton.getWidth() * aspect_ratio /2.5f);
                    Lovebutton.setPosition(playbutton.getPosition().x - Lovebutton.getWidth() * 1.5f, 1.75f * group.getHeight() / 5);

                }
                else{
                    setPosBlack();
                    setPosBlackUsername();
                    setLikes();
                    Lovebutton.setWidth(group.getWidth() / 6.8f);
                    Lovebutton.setHeight(Lovebutton.getWidth() * aspect_ratio /2);
                    Lovebutton.setPosition(playbutton.getPosition().x - Lovebutton.getWidth() * 1.5f, 1.75f * group.getHeight() / 5);

                }


                Lovebutton.setTouchable();
                Lovebutton.button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Lovebutton.change();
                    }
                });

                group.addActor(Lovebutton.button);
            }
        }



}
