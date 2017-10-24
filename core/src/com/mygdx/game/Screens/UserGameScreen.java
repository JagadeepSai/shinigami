package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.Cards.BWCard;
import com.mygdx.game.Cards.Card;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

/**
 * Created by root on 8/10/17.
 */

public class UserGameScreen extends ScreenAdapter {
    MainClass game ;
    Assets assets;
    //List<Card> cardList;
    Table scrollTable = new Table();

    GeneralButton designbutton;
    GeneralButton backbutton;


    Group nav_control;
    ScrollPane scrollPane ;




    Card UserCard;
    Card bufferEnd;
    BWCard UserCard2;
    BWCard UserCard3;
    BWCard UserCard4;
    BWCard UserCard5;
    BWCard UserCard6;
    BWCard UserCard7;
    BWCard UserCard8;



    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;

    float cardWidth = 8*GameWidth/9;
    float padding = GameHeight/33;


    public UserGameScreen(MainClass gam){
        this.game= gam;
        assets=gam.assets;
        nav_control = new Group();
       // cardList = new ArrayList<Card>();


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/BebasNeue Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)((float)cardWidth/10f);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();


        UserCard = new Card("Username",cardWidth,AspectRatio1*1.75f,font,assets);
        UserCard.setCenter_L_name(false,2*UserCard.getHeight()/5);
        UserCard.setL_color(Color.BLACK);
        UserCard.L_name_scale(6/2);

        bufferEnd = new Card("",cardWidth,AspectRatio1*1.75f,font,assets);


        UserCard2 = new BWCard("Shinigami",cardWidth,AspectRatio1*1.75f,false,font,assets);
        UserCard3 = new BWCard("Noragami",cardWidth,AspectRatio1*1.75f,true,font,assets);
        UserCard4 = new BWCard("Suraj Soni",cardWidth,AspectRatio1*1.75f,false,font,assets);

        UserCard5 = new BWCard("Suseendran",cardWidth,AspectRatio1*1.75f,true,font,assets);
        UserCard6 = new BWCard("Jagadeep",cardWidth,AspectRatio1*1.75f,false,font,assets);


       // scrollTable.setDebug(true); //For Debugging

        scrollTable.add(UserCard.group).padBottom(padding).padTop(7*padding).expandX();
        scrollTable.row();
        scrollTable.add(UserCard2.group).padBottom(padding).expandX();
        scrollTable.row();
        scrollTable.add(UserCard3.group).padBottom(padding);
        scrollTable.row();
        scrollTable.add(UserCard4.group).padBottom(padding);
        scrollTable.row();
        scrollTable.add(UserCard5.group).padBottom(padding);
        scrollTable.row();
        scrollTable.add(UserCard6.group).padBottom(padding);
        scrollTable.row();
        scrollTable.add(bufferEnd.group).padBottom(padding);
        scrollTable.row();


        scrollPane = new ScrollPane(scrollTable);
        scrollPane.setFillParent(true);
        //scrollPane.setPosition(0,5*GameHeight/6);

        //scrollTable.setFillParent(true);
      //  scrollTable.add(scrollPane).fill().expand();

        designbutton = new GeneralButton(assets.PalleteBB,assets.PalleteBB);
        designbutton.setWidth(GameWidth/5.3f);
        designbutton.setHeight(designbutton.getWidth()/AspectRatio);
        designbutton.setPosition(3.9f*GameWidth/5 - designbutton.getWidth()/2 , GameHeight/(AspectRatio*6) - designbutton.getHeight() );
        designbutton.button.setZIndex(5);

        backbutton = new GeneralButton(assets.BackArrow,assets.BackArrow);
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio) + backbutton.getHeight()/2);

        nav_control.addActor(designbutton.button);
        nav_control.addActor(backbutton.button);

        game.stage.addActor(scrollPane);
        game.stage.addActor(nav_control);


        /*game.stage.addActor(designbutton.button);
        game.stage.addActor(backbutton.button);*/
        Gdx.input.setInputProcessor(game.stage);
    }

    @Override
    public void render(float delta) {
        game.stage.act();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.draw();
    }

    @Override
    public void show() {
        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getScreen().hide();
                game.stage.clear();
                MainScreen mainScreen = new MainScreen(game);
                game.setScreen(mainScreen);
            }
        });


        designbutton.setTouchable();
        designbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getScreen().hide();
                game.stage.clear();
                StageCreatorScreen stageCreatorScreen = new StageCreatorScreen(game);
                game.setScreen(stageCreatorScreen);
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
