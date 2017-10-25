package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Assets;
import com.mygdx.game.Cards.BWCard;
import com.mygdx.game.Cards.Card;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;
import com.mygdx.game.Stage;

/**
 * Created by root on 25/10/17.
 */

public class OnlineStageScreen extends ScreenAdapter {
    MainClass game ;
    Assets assets;
    Table scrollTable = new Table();

    String UserName;
    GeneralButton backbutton;


    Group nav_control;
    ScrollPane scrollPane ;


    Card UserCard;
    Card bufferEnd;
/*
    BWCard UserCard2;
    BWCard UserCard3;
    BWCard UserCard4;
    BWCard UserCard5;
    BWCard UserCard6;
    BWCard UserCard7;
    BWCard UserCard8;
*/



    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;

    float cardWidth = 8*GameWidth/9;
    float padding = GameHeight/33;


    public OnlineStageScreen(final MainClass gam){
        this.game= gam;
        assets=gam.assets;
        nav_control = new Group();
        UserName = "Username";
        // cardList = new ArrayList<Card>();


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/BebasNeue Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)((float)cardWidth/10f);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();


        UserCard = new Card(UserName,UserName,false,0,cardWidth,AspectRatio1*1.75f,font,assets);
        UserCard.setCenter_L_name(false,2*UserCard.getHeight()/5);
        UserCard.setL_color(Color.BLACK);
        UserCard.L_name_scale(6/2);

        bufferEnd = new Card("",UserName,false,0,cardWidth,AspectRatio1*1.75f,font,assets);


//Temporary Creation
//        PStage = new Stage(10, 15);
//        PStage.obstacles[0] = new float[]{0.1f, 0.3f, 0.1f};
//        PStage.obstacles[1] = new float[]{0.2f, -0.28f, 0.5f};
//        PStage.obstacles[2] = new float[]{0.15f, -0.3f, 0.9f};
//        PStage.obstacles[3] = new float[]{0.05f, 0.2f, 1f};
//        PStage.obstacles[4] = new float[]{0.25f, 0.15f, 1.3f};
//        PStage.obstacles[5] = new float[]{0.1f, -0.35f, 1.6f};
//        PStage.obstacles[6] = new float[]{0.05f, 0f, 1.75f};
//        PStage.obstacles[7] = new float[]{0.08f, -0.3f, 1.85f};
//        PStage.obstacles[8] = new float[]{0.12f, 0.1f, 2.05f};
//        PStage.obstacles[9] = new float[]{0.1f, 0.25f, 2.18f};
//        PStage.obstacles[10] = new float[]{0.26f, -0.2f, 2.4f};
//        PStage.obstacles[11] = new float[]{0.2f, 0f, 2.9f};
//        PStage.obstacles[12] = new float[]{0.17f, 0.22f, 4.3f};
//        PStage.obstacles[13] = new float[]{0.2f, 0.15f, 4.6f};
//        PStage.obstacles[14] = new float[]{0.1f, -0.2f, 4.85f};
////
        scrollTable.add(UserCard.group).padBottom(padding).padTop(7*padding).expandX();
        scrollTable.row();

        final FileHandle fileHandle= Gdx.files.local("usersaved.txt");
        final String [] stagesarray = fileHandle.readString().split("\\r?\\n");
        BWCard [] UserCards = new BWCard[stagesarray.length];
        int i=0;
        final Json json = new Json();
        for (String words : stagesarray) {

            if(words.isEmpty())break;
            final String [] stage = words.split("\\s+");
            if (!((i+1)%2 == 0)) {

                UserCards[i] = new BWCard(stage[1], "", true,0, Long.parseLong(stage[0]) , cardWidth, AspectRatio1 * 1.35f,false, font, assets,stage[2]);
            }
            else
            {
                UserCards[i] = new BWCard(stage[1], "", true,0, Long.parseLong(stage[0]) , cardWidth, AspectRatio1 * 1.75f,true, font, assets,stage[2]);
            }
            scrollTable.add(UserCards[i].group).padBottom(padding).expandX();
            scrollTable.row();
            UserCards[i].playbutton.button.isTouchable();
            UserCards[i].playbutton.button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new PlayScreen(gam,json.fromJson(Stage.class,stage[2])));
                }
            });

            i++;
        }

//
//        UserCard2 = new BWCard("Shinigami",UserName,true,0,new Date(),cardWidth,AspectRatio1*1.35f,false,font,assets);
//        UserCard3 = new BWCard("Noragami",UserName,true,0,new Date(),cardWidth,AspectRatio1*1.75f,true,font,assets);
//        UserCard4 = new BWCard("Suraj Soni",UserName,true,0,new Date(),cardWidth,AspectRatio1*1.35f,false,font,assets);
//
//        UserCard5 = new BWCard("Suseendran",UserName,true,0,new Date(),cardWidth,AspectRatio1*1.75f,true,font,assets);
//        UserCard6 = new BWCard("Jagadeep",UserName,true,0,new Date(),cardWidth,AspectRatio1*1.75f,false,font,assets);
//

        // scrollTable.setDebug(true); //For Debugging
/*


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

*/

        scrollTable.add(bufferEnd.group).padBottom(padding);
        scrollTable.row();
        scrollPane = new ScrollPane(scrollTable);
        scrollPane.setFillParent(true);
        //scrollPane.setPosition(0,5*GameHeight/6);

        //scrollTable.setFillParent(true);
        //  scrollTable.add(scrollPane).fill().expand();

        backbutton = new GeneralButton(assets.BackArrow,assets.BackArrow);
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio) + backbutton.getHeight()/2);

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



    }

    @Override
    public void dispose() {
        super.dispose();
    }
}


