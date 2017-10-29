package com.mygdx.game.Cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainButton.ToggleButton;
import com.mygdx.game.MainClass;

/**
 * Used to make ui in screens where stages are shown
 */

public class BWCard extends Card {

    public GeneralButton sharebutton;
    public GeneralButton deletebutton;
    public GeneralButton playbutton;
    public ToggleButton Lovebutton;
    Long idea;
    MainClass mainClass;
    Assets assets;
    boolean rede;

    public BWCard(String string, String username, boolean dis, int likes, Long id, float g_width, float aspect_ratio, boolean white, BitmapFont font, final MainClass mainClass, boolean red) {
        super(string, username, dis, likes, g_width, aspect_ratio, font, mainClass.assets);

        this.mainClass = mainClass;
        this.assets = mainClass.assets;
        this.idea = id;
        this.rede = red;
        if (white) {
            playbutton = new GeneralButton(assets.CircledPlay);
            playbutton.setWidth(g_width / 4.2f);
            playbutton.setHeight(playbutton.getWidth() * aspect_ratio / 2);

            playbutton.setPosition(g_width - playbutton.getWidth() * 1.2f, -group.getHeight() / 16);
            setL_color(Color.BLACK);
            setDp(assets.White);
        } else {
            playbutton = new GeneralButton(assets.CircledPlayWhite);
            playbutton.setWidth(g_width / 4.2f);
            playbutton.setHeight(playbutton.getWidth() * aspect_ratio / 2);
            playbutton.setPosition(g_width - playbutton.getWidth() * 1.2f, group.getHeight() / 6.5f);
            setL_color(Color.WHITE);
            setDp(assets.Black);
        }
        group.addActor(playbutton.button);

        if (!disp_user) {
            if (white) {
                sharebutton = new GeneralButton(assets.Connect);
                deletebutton = new GeneralButton(assets.Delete);

            } else {
                sharebutton = new GeneralButton(assets.ConnectWhite);
                deletebutton = new GeneralButton(assets.DeleteWhite);
            }

            sharebutton.setWidth(group.getWidth() / 6.8f);
            sharebutton.setHeight(sharebutton.getWidth() * aspect_ratio / 2);

            deletebutton.setWidth(group.getWidth() / 8f);
            deletebutton.setHeight(deletebutton.getWidth() * aspect_ratio / 2);

            if (white) {
                sharebutton.setPosition(playbutton.getPosition().x - sharebutton.getWidth() * 1.5f, 1.75f * group.getHeight() / 5);
                deletebutton.setPosition(sharebutton.getPosition().x, -group.getHeight() + 4.5f * deletebutton.getHeight() / 3);
            } else {
                setPosBlack();
                sharebutton.setPosition(playbutton.getPosition().x - sharebutton.getWidth() * 1.5f, 2.25f * group.getHeight() / 5);
                deletebutton.setPosition(sharebutton.getPosition().x, 0.8f * deletebutton.getHeight() / 3);
            }
            group.addActor(sharebutton.button);
            group.addActor(deletebutton.button);
        } else {
            if (white) {
                Lovebutton = new ToggleButton(assets.LoveBlack, assets.LoveIn, red);

                Lovebutton.setWidth(group.getWidth() / 6.8f);
                Lovebutton.setHeight(Lovebutton.getWidth() * aspect_ratio / 2.5f);
                Lovebutton.setPosition(playbutton.getPosition().x - Lovebutton.getWidth() * 1.5f, 1.75f * group.getHeight() / 5);

            } else {
                setPosBlack();
                setPosBlackUsername();
                setLikes();
                Lovebutton = new ToggleButton(assets.LoveWhite, assets.LoveIn, red);
                Lovebutton.setWidth(group.getWidth() / 6.8f);
                Lovebutton.setHeight(Lovebutton.getWidth() * aspect_ratio / 2);
                Lovebutton.setPosition(playbutton.getPosition().x - Lovebutton.getWidth() * 1.5f, 1.75f * group.getHeight() / 5);

            }
            Lovebutton.setTouchable();
            Lovebutton.button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Lovebutton.change();
                    mainClass.saveToDatabase.rate(Long.toString(idea), !Lovebutton.getState());
                    if (Lovebutton.getState()) {
                        BWCard.super.Likes.setText(Integer.toString(Integer.parseInt(BWCard.super.Likes.getText().toString()) + 1));
                    }
                    else
                        BWCard.super.Likes.setText(Integer.toString(Integer.parseInt(BWCard.super.Likes.getText().toString()) - 1));
                }
            });
            group.addActor(Lovebutton.button);
        }
    }
}
