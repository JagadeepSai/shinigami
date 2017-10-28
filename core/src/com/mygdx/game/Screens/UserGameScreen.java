package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Assets;
import com.mygdx.game.Cards.BWCard;
import com.mygdx.game.Cards.Card;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;
import com.mygdx.game.Stage;

import org.lwjgl.Sys;

import java.util.Date;

/**
 * Created by root on 8/10/17.
 */

public class UserGameScreen extends ScreenAdapter {
    MainClass game ;
    Assets assets;
    //List<Card> cardList;
    Table scrollTable = new Table();
    Stage PStage;
    String UserName;

    GeneralButton designbutton;
    GeneralButton backbutton;


    Group nav_control;
    ScrollPane scrollPane ;

    Card UserCard;
    Card bufferEnd;



    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;

    float cardWidth = 8*GameWidth/9;
    float padding = GameHeight/33;


    public UserGameScreen(final MainClass gam){
        this.game= gam;
        assets=gam.assets;
        nav_control = new Group();
        UserName = "Username";
       // cardList = new ArrayList<Card>();

        if(game.load){

            if (game.completed) {
                System.out.println("Inside Sharing after complete");
                game.saveToDatabase.save(game.idload, game.nameload,game.usernameload,game.jsonload);
            }
            game.load = false;
            game.completed = false;
        }


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/BebasNeue Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)((float)cardWidth/10f);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();


        UserCard = new Card(game.prefs.getString("username"),UserName,false,0,cardWidth,AspectRatio1*1.75f,font,assets);
        UserCard.setCenter_L_name(false,2*UserCard.getHeight()/5);
        UserCard.setL_color(Color.BLACK);
        UserCard.L_name_scale(6/2);

        bufferEnd = new Card("",UserName,false,0,cardWidth,AspectRatio1*1.75f,font,assets);

        scrollTable.add(UserCard.group).padBottom(padding).padTop(7*padding).expandX();
        scrollTable.row();

        FileHandle fileHandle= Gdx.files.local("usersaved.txt");
        String [] stagesarray = fileHandle.readString().split("\\r?\\n");
        final BWCard [] UserCards = new BWCard[stagesarray.length];
        int i=0;
        final Json json = new Json();
        for (String words : stagesarray) {
            final int j =i;
            if(words.isEmpty())break;
            final String [] stage = words.split("\\s+");
            if (!((i+1)%2 == 0)) {


                UserCards[i] = new BWCard(stage[1], "", false,0, Long.parseLong(stage[0]) , cardWidth, AspectRatio1 * 1.35f,false, font, gam,false);
            }
            else {
                UserCards[i] = new BWCard(stage[1], "", false,0, Long.parseLong(stage[0]) , cardWidth, AspectRatio1 * 1.75f,true, font, gam,false);
            }
            scrollTable.add(UserCards[i].group).padBottom(padding).expandX();
            scrollTable.row();
            UserCards[i].playbutton.button.isTouchable();
            UserCards[i].playbutton.button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(game.button_tune_play)  game.assets.button_tune.play();
                   game.stage.clear();
                    game.setScreen(new PlayScreen(gam,json.fromJson(Stage.class,stage[2]),"UserGameScreen"));
                }
            });

            UserCards[i].deletebutton.button.isTouchable();
            UserCards[i].deletebutton.button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    Long id = Long.parseLong(stage[0]);
                    System.out.println(stage[1]);
                    System.out.println(id);

                    FileHandle fileHandle2= Gdx.files.local("usersaved.txt");
                    String [] stagesarray2 = fileHandle2.readString().split("\\r?\\n");


                    fileHandle2.writeString("",false);
                    System.out.println("Inside : Empty");
                    System.out.println("Hi"+fileHandle2.readString()+"In");

                    for (String words : stagesarray2) {
                        if (words.isEmpty()) break;
                        final String[] stage2 = words.split("\\s+");

                        if(id != Long.parseLong(stage2[0])) fileHandle2.writeString(words +'\n',true);
                        else
                        {
                            scrollTable.removeActor(UserCards[j].group);
                           // System.out.println(fileHandle.readString());
                        }

                    }
                   // System.out.println(fileHandle2.readString());


                }
            });

            UserCards[i].sharebutton.button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {

                if(!game.Username.equals("")) {
                    gam.toast.showtost("Pass Once To Share");
                    game.load = true;
                    game.idload = stage[0];
                    game.nameload = stage[1];
                    game.usernameload = game.Username;
                    game.jsonload = stage[2];

                    game.getScreen().hide();
                    game.stage.clear();
                    game.setScreen(new PlayScreen(gam, json.fromJson(Stage.class, stage[2]), "UserGameScreen"));
                }

                 //   game.saveToDatabase.save(stage[0], stage[1],gam.Username,stage[2]);
                }
            });


            i++;
        }



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
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if(game.button_tune_play)  game.assets.button_tune.play();
            game.getScreen().hide();
            game.stage.clear();
            MainScreen mainScreen = new MainScreen(game);
            game.setScreen(mainScreen);
        }

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
                if(game.button_tune_play)  game.assets.button_tune.play();
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
                if(game.button_tune_play)  game.assets.button_tune.play();
                game.getScreen().hide();
                game.stage.clear();
                StageNameScreen stageNameScreen = new StageNameScreen(game);
                game.setScreen(stageNameScreen);
            }
        });


    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
