package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Screens.MainScreen;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Shows the animations at the start of app start
 */
public class Animate extends ScreenAdapter {

    // Constant rows and columns of the sprite sheet
    int FRAME_COLS = 5, FRAME_ROWS = 8;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    int count = 0;
    Texture noragami ;
    float GameWidth = Gdx.graphics.getWidth();
    float GameHeight = Gdx.graphics.getHeight();
    float AspectRatio = 16/9;

    // A variable for tracking elapsed time for the animation
    float stateTime;
    MainClass game;

    public  Animate(MainClass gam){
        this.game = gam;
        noragami = new Texture(Gdx.files.internal("Noragami.png"));

        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("EyeS.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        stateTime = 0f;

    }


    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(205f/255,205f/255,205f/255,1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += delta; // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        game.stage.getBatch().begin();
       game.stage.getBatch().draw(currentFrame, 0, GameHeight - GameWidth*800/600,GameWidth,GameWidth*800/600);
        game.stage.getBatch().draw(noragami,GameWidth/6f,GameHeight/14,GameWidth/1.5f,GameWidth/4*AspectRatio);

        game.stage.getBatch().end();
        if(walkAnimation.isAnimationFinished(stateTime)){
            if(count== 150){
            game.stage.clear();
                game.toast.showtost("Welcome "+game.Username);
            game.setScreen(new MainScreen(game));}

            else count++;
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        walkSheet.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }
}
