package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.i18n.BundleText;
import com.mygdx.game.Assets;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;

import javax.swing.text.View;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.repeat;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static java.awt.SystemColor.info;

/**
 * Created by root on 1/10/17.
 */

public class MainScreen extends ScreenAdapter {

    MainClass game;
    GeneralButton playbutton ;
    GeneralButton settingbutton ;
    GeneralButton groupbutton;
    GeneralButton createbutton;
    Assets assets;

    public float GameWidth = Gdx.graphics.getWidth();
	public float GameHeight = Gdx.graphics.getHeight();
	public float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
	public float AspectRatio1 = 16/9;





    public MainScreen(MainClass gam){
        this.game = gam;
        this.assets=game.assets;
        game.stage.getViewport().update((int)GameWidth,(int)(GameHeight));

        playbutton = new GeneralButton(assets.CircledPlay,assets.CircledPlay);
        playbutton.setWidth(GameWidth/3);
        playbutton.setHeight(playbutton.getWidth()/AspectRatio1);
        playbutton.setPosition(GameWidth/2 - playbutton.getWidth()/2 ,GameWidth/(AspectRatio1*2) + playbutton.getHeight());
        settingbutton = new GeneralButton(assets.Settings,assets.Settings);
        settingbutton.setWidth(GameWidth/6);
        settingbutton.setHeight(settingbutton.getWidth()/AspectRatio1);
        settingbutton.setPosition(GameWidth/5 - settingbutton.getWidth()/2 , GameHeight/(AspectRatio1*6) - settingbutton.getHeight()/2 );
    //    settingbutton.setOrigin(settingbutton.getWidth()/2,settingbutton.getWidth()/2);
       // settingbutton.addAction(Actions.rotateBy(360,1));

        groupbutton = new GeneralButton(assets.UserGroups,assets.UserGroups);
        groupbutton.setWidth(GameWidth/5.3f);
        groupbutton.setHeight(groupbutton.getWidth()/AspectRatio1);
        groupbutton.setPosition(3.9f*GameWidth/5 - groupbutton.getWidth()/2 , GameHeight/(AspectRatio1*6) - groupbutton.getHeight()/2 );

        createbutton = new GeneralButton(assets.Create,assets.Create);
        createbutton.setWidth(GameWidth/4.5f);
        createbutton.setHeight(createbutton.getWidth()/AspectRatio1);
        createbutton.setPosition(GameWidth/2 - createbutton.getWidth()/2,GameWidth/(AspectRatio1*2) - createbutton.getHeight()/2);


        game.stage.addActor(playbutton.button);
        game.stage.addActor(settingbutton.button);
        game.stage.addActor(groupbutton.button);
        game.stage.addActor(createbutton.button);

        Gdx.input.setInputProcessor(game.stage);


    }


    @Override
    public void show() {
        game.assets.back_tune.setVolume(1);
        if(!game.assets.back_tune.isPlaying() && game.back_tune_play) game.assets.back_tune.play();
        if (game.button_tune_play )game.assets.back_tune.setLooping(true);

    }


    @Override
    public void render(float delta) {
        playbutton.setTouchable();

        playbutton.button.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event,float x,float y){
               if(game.button_tune_play)  game.assets.button_tune.play();
              // game.back_tune.stop();
               game.getScreen().hide();
               game.stage.clear();

               //  GameScreen gameScreen = new GameScreen(game);
//               game.setScreen(gameScreen);
               com.mygdx.game.Stage PStage;

               PStage = new com.mygdx.game.Stage(4.85f*GameWidth, 15);
               PStage.obstacles[0] = new float[]{0.1f, 0.3f, 0.1f};
               PStage.obstacles[1] = new float[]{0.2f, -0.28f, 0.5f};
               PStage.obstacles[2] = new float[]{0.15f, -0.3f, 0.9f};
               PStage.obstacles[3] = new float[]{0.05f, 0.2f, 1f};
               PStage.obstacles[4] = new float[]{0.25f, 0.15f, 1.3f};
               PStage.obstacles[5] = new float[]{0.1f, -0.35f, 1.6f};
               PStage.obstacles[6] = new float[]{0.05f, 0f, 1.75f};
               PStage.obstacles[7] = new float[]{0.08f, -0.3f, 1.85f};
               PStage.obstacles[8] = new float[]{0.12f, 0.1f, 2.05f};
               PStage.obstacles[9] = new float[]{0.1f, 0.25f, 2.18f};
               PStage.obstacles[10] = new float[]{0.26f, -0.2f, 2.4f};
               PStage.obstacles[11] = new float[]{0.2f, 0f, 2.9f};
               PStage.obstacles[12] = new float[]{0.17f, 0.22f, 4.3f};
               PStage.obstacles[13] = new float[]{0.2f, 0.15f, 4.6f};
               PStage.obstacles[14] = new float[]{0.1f, -0.2f, 4.85f};


               game.setScreen(new PlayScreen(game,PStage,"MainScreen"));
           }
        });

        settingbutton.setTouchable();
        settingbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                if(game.button_tune_play)    game.assets.button_tune.play();
                game.getScreen().hide();
                game.stage.clear();
                SettingScreen settingScreen = new SettingScreen(game);
                game.setScreen(settingScreen);
            }
        });

        createbutton.setTouchable();
        createbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                if(game.button_tune_play)    game.assets.button_tune.play();
                game.getScreen().hide();
                game.stage.clear();
                UserGameScreen userGameScreen = new UserGameScreen(game);
                game.setScreen(userGameScreen);
            }
        });

        groupbutton.setTouchable();
        groupbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                if(game.button_tune_play)   game.assets.button_tune.play();
                game.getScreen().hide();
                game.stage.clear();
                OnlineStageScreen onlineStageScreen = new OnlineStageScreen(game);
                game.setScreen(onlineStageScreen);
            }
        });


      //  settingbutton.button.addAction(repeat(RepeatAction.FOREVER,sequence(
      //          rotateBy(360,1),rotateTo(0))));
        //settingbutton.button.act(delta);

        game.stage.act(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
            game.assets.back_tune.pause();
    //    game.stage.dispose();
    }
}
