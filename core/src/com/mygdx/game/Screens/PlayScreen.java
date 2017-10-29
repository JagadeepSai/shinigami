package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.MainButton.GeneralButton;
import com.mygdx.game.MainClass;
import com.mygdx.game.Stage;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * Screen which apppears when play button is pressed in a screen
 */
public class PlayScreen extends ScreenAdapter{
    public enum GameState{
        start,ongoing,end;
    }

    public MainClass game;
    public float Scale =8f;
    String PreviousScreen;
    boolean completed = false;

    GameState gameState ;
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    float PlayerRadious=w*0.02f/Scale;

    public World world=new World(new Vector2(0,0),false);
    public Body player = createPlayer();
    public Stage PStage;
    public float Presentxp;
    public float Presentyp;

    public float[][] Obstaclepos;


    public float GameWidth = Gdx.graphics.getWidth();
    public float GameHeight = Gdx.graphics.getHeight();
    public float AspectRatio1 = (float)(Gdx.graphics.getHeight())/(float)(Gdx.graphics.getWidth());
    public float AspectRatio = 16/9;

    Label.LabelStyle labelStyle;
    Group group;
    Image background ;
    Sprite sprite ;

    GeneralButton backbutton;
    GeneralButton restartbutton;
    Label Display;

    public SpriteBatch batch = new SpriteBatch();
    public TextureRegion Player ;
    public TextureRegion Wall;
    public TextureRegion Obstacle ;
    public OrthographicCamera camera=new OrthographicCamera(w/Scale,h/Scale);


    public  PlayScreen(MainClass game , Stage stage,String Pre){
        this.game=game;
        PreviousScreen = Pre;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/BebasNeue Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)((8*GameWidth/9)/10f);
        BitmapFont font12 = generator.generateFont(parameter);
        generator.dispose();

        labelStyle = new Label.LabelStyle(font12,Color.BLACK);

        group = new Group();
        group.setWidth((GameWidth/2));
        group.setHeight((GameWidth/2*AspectRatio));
        group.setPosition(GameWidth/2 - group.getWidth()/2,GameHeight/2 - group.getHeight()/2);


        background = new Image(game.assets.White);
        background.setFillParent(true);

        Display = new Label("Let's Party",labelStyle);
        Display.setPosition(group.getWidth()/5.5f,3.5f*group.getHeight()/5);

        backbutton = new GeneralButton(game.assets.BackArrow,game.assets.BackArrow);
        backbutton.setWidth(group.getWidth()/3);
        backbutton.setHeight(backbutton.getWidth()/AspectRatio);
        backbutton.setPosition(group.getWidth()/3 - backbutton.getWidth()/1.5f,(2*group.getHeight()/6) - backbutton.getHeight()/2);

        restartbutton = new GeneralButton(game.assets.Restart,game.assets.Restart);
        restartbutton.setWidth((group.getWidth()/3));
        restartbutton.setHeight((restartbutton.getWidth()/AspectRatio));
        restartbutton.setPosition(group.getWidth()- 1.25f*restartbutton.getWidth(),(2*group.getHeight()/6) - backbutton.getHeight()/2);

        sprite = new Sprite(this.game.assets.Black);

        sprite.setColor(0,0,0,0.1f);
        sprite.setSize(GameWidth,GameHeight);
        sprite.setPosition(-GameWidth/2,-GameHeight/2);
        sprite.setAlpha(0);

        group.addActor(background);
        group.addActor(Display);
        group.addActor(backbutton.button);
        group.addActor(restartbutton.button);
        group.setVisible(false);

        game.stage.addActor(group);

        Player=game.assets.Player;
        Obstacle=game.assets.Obstacle;
        Wall=game.assets.Wall;
        PStage=stage;
    }


    public class InputHandler implements InputProcessor{

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }
        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {

            switch (gameState){
                case start:
                    gameState=GameState.ongoing;
                    world.setGravity(new Vector2(0,-500000/w));
                    break;
                case ongoing:
                    if(screenX>w/2){
                        player.setLinearVelocity(5000000/w,10000000/w);
                    }
                    else{
                        player.setLinearVelocity(-5000000/w,10000000/w);
                    }
                    break;
                case end:
                    break;
            }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }

    /**
     * Creates a Box2d body
     * @return body
     */
    public Body createPlayer(){
        Body pBody;
        BodyDef def= new BodyDef();
        def.type=BodyDef.BodyType.DynamicBody;
        def.position.set(-10,-10);
        def.fixedRotation=true;
        pBody=world.createBody(def);
        CircleShape shape = new CircleShape();
        shape.setRadius(PlayerRadious);
        pBody.createFixture(shape,0.1f);
        shape.dispose();
        return pBody;
    }

    @Override
    public void render(float delta) {

        update(Gdx.graphics.getDeltaTime());
        Presentxp=player.getPosition().x;
        if(Presentyp < player.getPosition().y)
            Presentyp=player.getPosition().y;
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for(int i=0;i<PStage.noofobstacles;i++){
            batch.draw(Obstacle,Obstaclepos[i][1]-Obstaclepos[i][0] ,
                    Obstaclepos[i][2]-Obstaclepos[i][0],2*Obstaclepos[i][0],2*Obstaclepos[i][0]);
        }
        batch.draw(Player,player.getPosition().x - PlayerRadious ,player.getPosition().y - PlayerRadious,2*PlayerRadious,2*PlayerRadious);
        batch.draw(Wall,w/2/Scale-1,-h/2,1,h);
        batch.draw(Wall,-w/2/Scale,-h/2,1,h);
        sprite.draw(batch);
        batch.end();

        game.stage.draw();
    }

    /**
     * Updates the map with time
     * @param delta Timelapse between to updates
     */
    public void update(float delta){
        world.step(1/60f,6,2);
        if(player.getPosition().y > Presentyp)
            camera.position.set(0,player.getPosition().y,0);
        camera.update();
        //Check for wall
        if(player.getPosition().x < -w/2/Scale  || player.getPosition().x > w/2/Scale || player.getPosition().y < camera.position.y -h/2/Scale){
            if(!completed) {
                if (game.button_tune_play) game.assets.gameOver_tune.play();
                gameState = GameState.end;
                Display.setText("Game Over");
                group.setVisible(true);
                sprite.setAlpha(0.5f);
            }
        }
        batch.setProjectionMatrix(camera.combined);

        if(player.getPosition().y > PStage.height/Scale){

            if(game.load) {
                game.completed = true;
                restartbutton.button.setVisible(false);
                Display.setText("Shared :)");
                sprite.setAlpha(0.5f);
                group.setVisible(true);
                completed = true;
            }
            else{
                sprite.setAlpha(0.5f);
                group.setVisible(true);
                completed = true;
            }
            System.out.println("Crossed : ");
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

        backbutton.setTouchable();
        backbutton.button.addListener(new ClickListener(){


            @Override
            public void clicked(InputEvent event, float x, float y){

                if(game.button_tune_play)  game.assets.button_tune.play();

                if(!game.completed) game.load = false;
                game.getScreen().hide();
                game.stage.clear();

                if  (PreviousScreen == "UserGameScreen") {
                    UserGameScreen userGameScreen = new UserGameScreen(game);
                    game.setScreen(userGameScreen);
                }
                else if (PreviousScreen == "MainScreen"){
                    MainScreen mainScreen = new MainScreen(game);
                    game.setScreen(mainScreen);
                }
                else if (PreviousScreen == "OnlineStageScreen"){
                    OnlineStageScreen onlineStageScreen = new OnlineStageScreen(game);
                    game.setScreen(onlineStageScreen);
                }
            }
        });

        restartbutton.setTouchable();
        restartbutton.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.button_tune_play) game.assets.button_tune.play();
                    game.stage.clear();
                    game.setScreen(new PlayScreen(game,PStage,PreviousScreen));
            }
        });
        //Contact listiner for the world, detects collison
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if(!completed) {
                    if (game.button_tune_play) game.assets.gameOver_tune.play();
                    gameState = GameState.end;

                    Display.setText("Game Over");
                    group.setVisible(true);
                    sprite.setAlpha(0.5f);
                }
                player.setLinearVelocity(player.getLinearVelocity().x,-1000000000*w);
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        gameState = GameState.start;
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(game.stage);
        multiplexer.addProcessor(new InputHandler());
        Gdx.input.setInputProcessor(multiplexer);
        Presentxp = player.getPosition().x - PlayerRadious;
        Presentyp = player.getPosition().y - PlayerRadious;

        Obstaclepos = new float[PStage.noofobstacles][3] ;
        //Adding fixtures to the box2d bodies
        for (int i = 0; i < PStage.noofobstacles; i++) {
            Obstaclepos[i]= new float[]{w*PStage.obstacles[i][0]/Scale,
                    w*PStage.obstacles[i][1]/Scale,w*PStage.obstacles[i][2]/Scale};
            BodyDef def = new BodyDef();
            def.type = BodyDef.BodyType.StaticBody;
            def.position.set(Obstaclepos[i][1], Obstaclepos[i][2]);
            def.fixedRotation = true;
            Body b = world.createBody(def);
            CircleShape shape = new CircleShape();
            shape.setRadius(Obstaclepos[i][0]);
            b.createFixture(shape, 0);
            shape.dispose();
        }
    }
    @Override
    public void hide() {

        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
