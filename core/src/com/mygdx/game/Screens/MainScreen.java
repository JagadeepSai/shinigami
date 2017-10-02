package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainClass;

import javax.swing.text.View;

import static java.awt.SystemColor.info;

/**
 * Created by root on 1/10/17.
 */

public class MainScreen extends ScreenAdapter {

    MainClass game;
    Stage stage;
    final TextButton button ;
    Texture background = new Texture("background.png");

   // Type font



    float GameWidth = Gdx.graphics.getWidth();
    float GameHeight = Gdx.graphics.getHeight();

    float AspectRatio1 = 16/9;

    float AspectRatio = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());

    Viewport viewport ;
    private Skin skin;

    public MainScreen(MainClass game){

        this.game = game;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Play.TTF"));
        FreeTypeFontParameter parametr = new FreeTypeFontParameter();
        parametr.size = 12 ;
        BitmapFont playfont =  generator.generateFont(parametr);
//        BitmapFontWriter.writeFont(data, new String[] {"font.png"},Gdx.files.absolute("font.fnt"), info, 512, 512);
//        BitmapFontWriter.writePixmaps(param.packer.getPages(), Gdx.files.absolute("imageDir"), name);

        generator.dispose();


        skin = new Skin(Gdx.files.internal("neon-ui.json"));
        skin.add("font", playfont, BitmapFont.class);
        FileHandle filehandle = Gdx.files.internal("neon-ui.json");
        FileHandle atlasfile = filehandle.sibling("neon-ui.json") ;
        if (atlasfile.exists()){
            skin.addRegions(new TextureAtlas(atlasfile));
        }
        skin.load(filehandle);
//        skin.addRegions(new TextureAtlas(Gdx.files.internal("neon-ui.atlas")));
//        skin.load(Gdx.files.internal("neon-ui.json"));

        viewport = new StretchViewport(GameWidth ,GameWidth/AspectRatio1);
        stage = new Stage(viewport);

        button = new TextButton("PLAY",skin,"default");
        button.getStyle().font = playfont;
        button.setWidth((150/310.5f)*GameWidth);
        button.setHeight(button.getWidth()*AspectRatio1/2f);
        button.setPosition(GameWidth/2 - button.getWidth()/2 ,GameWidth/(AspectRatio*2) + button.getHeight()/2);
       // TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
       /// tbs.font= playfont;


        stage.addActor(button);

        //stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        button.setTouchable(Touchable.enabled);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        stage.act(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,GameWidth,GameHeight);
        stage.getBatch().end();
        stage.draw();
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
    public void dispose() {
        stage.dispose();
    }
}
