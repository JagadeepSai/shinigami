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
   // Stage stage;
    GeneralButton playbutton ;
    GeneralButton settingbutton ;
    GeneralButton groupbutton;
    GeneralButton createbutton;
    Texture background = new Texture("background.png");

    public float GameWidth = Gdx.graphics.getWidth();
	public float GameHeight = Gdx.graphics.getHeight();
	public float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
	public float AspectRatio1 = 16/9;



  //  private Skin skin;

    public MainScreen(MainClass gam){
        this.game = gam;
     //   game.stage = new Stage(new StretchViewport(GameWidth ,GameWidth/AspectRatio1));
       //   game.stage.clear();
       // game.viewport = new StretchViewport(GameWidth ,GameWidth/AspectRatio1);
       // game.stage.setViewport(game.viewport);
          game.stage.getViewport().update((int)GameWidth,(int)(GameHeight));
        /// Font for the Skin ( Failed )
    /*
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Play.TTF"));
          FreeTypeFontParameter parametr = new FreeTypeFontParameter();
        parametr.size = 12 ;
        BitmapFont playfont =  generator.generateFont(parametr);
        BitmapFontWriter.writeFont(data, new String[] {"font.png"},Gdx.files.absolute("font.fnt"), info, 512, 512);
        BitmapFontWriter.writePixmaps(param.packer.getPages(), Gdx.files.absolute("imageDir"), name);

        generator.dispose();
    */
    /// Skin for the buttons

  //      skin = new Skin(Gdx.files.internal("neon-ui.json"));
      /*  skin.add("font", playfont, BitmapFont.class);
        FileHandle filehandle = Gdx.files.internal("neon-ui.json");
        FileHandle atlasfile = filehandle.sibling("neon-ui.json") ;
        if (atlasfile.exists()){
            skin.addRegions(new TextureAtlas(atlasfile));
        }
        skin.load(filehandle);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("neon-ui.atlas")));
        skin.load(Gdx.files.internal("neon-ui.json"));

                button = new TextButton("PLAY",skin,"default");
        button.getStyle().font = playfont;
        button.setWidth((150/310.5f)*GameWidth);
        button.setHeight(button.getWidth()*AspectRatio1/2f);
        button.setPosition(GameWidth/2 - button.getWidth()/2 ,GameWidth/(AspectRatio1*2) + button.getHeight()/2);
       // TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
       /// tbs.font= playfont;

         stage.addActor(button);

*/


        playbutton = new GeneralButton("icons/Circled Play-260.png","");
        playbutton.setWidth(GameWidth/3);
        playbutton.setHeight(playbutton.getWidth()/AspectRatio1);
        playbutton.setPosition(GameWidth/2 - playbutton.getWidth()/2 ,GameWidth/(AspectRatio1*2) + playbutton.getHeight());

        settingbutton = new GeneralButton("icons/Settings-500.png","");
        settingbutton.setWidth(GameWidth/6);
        settingbutton.setHeight(settingbutton.getWidth()/AspectRatio1);
        settingbutton.setPosition(GameWidth/5 - settingbutton.getWidth()/2 , GameHeight/(AspectRatio1*6) - settingbutton.getHeight()/2 );
     //   settingbutton.setOrigin(settingbutton.getWidth()/2,settingbutton.getWidth()/2);
       // settingbutton.addAction(Actions.rotateBy(360,1));

        groupbutton = new GeneralButton("icons/User Groups-260.png","");
        groupbutton.setWidth(GameWidth/5.3f);
        groupbutton.setHeight(groupbutton.getWidth()/AspectRatio1);
        groupbutton.setPosition(3.9f*GameWidth/5 - groupbutton.getWidth()/2 , GameHeight/(AspectRatio1*6) - groupbutton.getHeight()/2 );

        createbutton = new GeneralButton("icons/Create -260.png","");
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

    }


    @Override
    public void render(float delta) {
        playbutton.setTouchable();/*
        playbutton.addChangeListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getScreen().hide();
                game.stage.clear();
                game.setScreen(new GameScreen(game));
            }
        });*/
        playbutton.button.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event,float x,float y){
               game.getScreen().hide();
               game.stage.clear();
               GameScreen gameScreen = new GameScreen(game);
               game.setScreen(gameScreen);
           }
        });

        settingbutton.setTouchable();
        settingbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                game.getScreen().hide();
                game.stage.clear();
                SettingScreen settingScreen = new SettingScreen(game);
                game.setScreen(settingScreen);
            }
        });
        /*
        settingbutton.addChangeListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getScreen().hide();
                game.stage.clear();
                game.setScreen(new SettingScreen(game));
            }
        });*/
  
//        settingbutton.addAction(repeat(RepeatAction.FOREVER,sequence(
//                rotateBy(360,1),rotateTo(0))));

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
    //    game.stage.dispose();
    }
}
