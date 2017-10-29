package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
 * Shows all the stages share by users
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
    boolean safe=true;
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
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/BebasNeue Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)((float)cardWidth/10f);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        UserCard = new Card("Stage Online",UserName,false,0,cardWidth,AspectRatio1*1.75f,font,assets);
        UserCard.setCenter_L_name(false,2*UserCard.getHeight()/5);
        UserCard.setL_color(Color.BLACK);
        UserCard.L_name_scale(6/2);

        bufferEnd = new Card("",UserName,false,0,cardWidth,AspectRatio1*1.75f,font,assets);

        scrollTable.add(UserCard.group).padBottom(padding).padTop(7*padding).expandX();
        scrollTable.row();
        try {
            //getting maps liked by the user
            final String[][] s = game.saveToDatabase.get();
            BWCard[] UserCards = new BWCard[s.length];
            int i = 0;
            final Json json = new Json();

            for (int j = 0; j < s.length; j++) {
                if (!((i + 1) % 2 == 0)) {

                    UserCards[j] = new BWCard(s[j][1], s[j][3], true, Integer.parseInt(s[j][2]), Long.parseLong(s[j][0]), cardWidth, AspectRatio1 * 1.35f, false, font, game, gam.set.contains(s[j][0]));
                } else {
                    UserCards[j] = new BWCard(s[j][1], s[j][3], true, Integer.parseInt(s[j][2]), Long.parseLong(s[j][0]), cardWidth, AspectRatio1 * 1.75f, true, font, game, gam.set.contains(s[j][0]));
                }
                scrollTable.add(UserCards[i].group).padBottom(padding).expandX();
                scrollTable.row();
                UserCards[j].playbutton.button.isTouchable();
                final String string = s[j][4];
                UserCards[j].playbutton.button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (game.button_tune_play) game.assets.button_tune.play();
                        game.getScreen().hide();
                        game.stage.clear();
                        game.setScreen(new PlayScreen(gam, json.fromJson(Stage.class, string), "OnlineStageScreen"));
                    }
                });
                i++;
            }
        }catch (Exception ex){ //Check for internet connection
            gam.toast.showtost("Please check your internet or try again in few seconds");
            safe=false;
        }

        scrollTable.add(bufferEnd.group).padBottom(padding);
        scrollTable.row();
        scrollPane = new ScrollPane(scrollTable);
        scrollPane.setFillParent(true);
        backbutton = new GeneralButton(assets.BackArrow,assets.BackArrow);
        backbutton.setWidth(GameWidth/6);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(GameWidth/5 - backbutton.getWidth(),(5*GameHeight)/(6*AspectRatio) + backbutton.getHeight()/2);
        nav_control.addActor(backbutton.button);
        game.stage.addActor(scrollPane);
        game.stage.addActor(nav_control);
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
        if(!safe){
            game.stage.clear();
            game.setScreen(new MainScreen(game));
        }
        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
               if(game.button_tune_play) game.assets.button_tune.play();
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


