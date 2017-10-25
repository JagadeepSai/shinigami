package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.MainClass;
import com.mygdx.game.Stage;
import com.sun.java.swing.plaf.windows.WindowsLabelUI;

public class PlayScreen extends ScreenAdapter{
    public PlayScreen(MainClass game){
        this.game=game;
        Player=game.assets.Player;
        Obstacle=game.assets.Obstacle;
        Wall=game.assets.Wall;
    }

    public enum GameState{
        start,ongoing,end;
    }
    public MainClass game;
    public float Scale =8f;
    GameState gameState ;
    float w=Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    float PlayerRadious=w*0.02f/Scale;

    public World world=new World(new Vector2(0,0),false);
    public Body player = createPlayer();
    public Stage PStage;
    public float Presentxp;
    public float Presentyp;

    public float[][] Obstaclepos;


    public Box2DDebugRenderer b2dr= new Box2DDebugRenderer();
    public SpriteBatch batch = new SpriteBatch();
    public TextureRegion Player ;
    public TextureRegion Wall;
    public TextureRegion Obstacle ;
    public OrthographicCamera camera=new OrthographicCamera(w/Scale,h/Scale);

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
                    game.setScreen(new MainScreen(game));
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



    //Stage stage = new Stage(2,2);

    PlayScreen(MainClass game, int stage){

    }
    public Body createPlayer(){
        Body pBody;
        BodyDef def= new BodyDef();
        def.type=BodyDef.BodyType.DynamicBody;
        def.position.set(-10,-10);
        def.fixedRotation=true;
        pBody=world.createBody(def);
        CircleShape shape = new CircleShape();
        shape.setRadius(PlayerRadious);
       // Gdx.app.log(String.valueOf(PlayerRadious),"");
        //shape.setRadius(10);
        //shape.setAsBox(32/2,32/2);
        //shape.setAsBox(0.1f,0.1f);
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
        //System.out.println(Presentxp);
        //System.out.println(Presentyp);
        batch.begin();
        //batch.draw(Obstacle,0,0,10,10);

        for(int i=0;i<PStage.noofobstacles;i++){
            batch.draw(Obstacle,Obstaclepos[i][1]-Obstaclepos[i][0] ,
                    Obstaclepos[i][2]-Obstaclepos[i][0],2*Obstaclepos[i][0],2*Obstaclepos[i][0]);
        }
        batch.draw(Player,player.getPosition().x - PlayerRadious ,player.getPosition().y - PlayerRadious,2*PlayerRadious,2*PlayerRadious);
        batch.draw(Wall,w/2/Scale-1,-h/2,1,h);
        batch.draw(Wall,-w/2/Scale,-h/2,1,h);
        batch.end();

      //  b2dr.render(world,camera.combined);
    }
    public void update(float delta){
        world.step(1/60f,6,2);
        if(player.getPosition().y > Presentyp)
            camera.position.set(0,player.getPosition().y,0);
        camera.update();
        if(player.getPosition().x < -w/2/Scale  || player.getPosition().x > w/2/Scale){
            gameState=GameState.end;
        }
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        //super.resize(width, height);
    }

    @Override
    public void show() {
       // Gdx.app.log(String.valueOf(h), String.valueOf(w));


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                gameState = GameState.end;
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
        Gdx.input.setInputProcessor(new InputHandler());
        Presentxp = player.getPosition().x - PlayerRadious;
        Presentyp = player.getPosition().y - PlayerRadious;
        PStage = new Stage(10, 15,"Hello");
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

        Obstaclepos = new float[PStage.noofobstacles][3] ;


        for (int i = 0; i < PStage.noofobstacles; i++) {
            Obstaclepos[i]= new float[]{w*PStage.obstacles[i][0]/Scale,w*PStage.obstacles[i][1]/Scale,w*PStage.obstacles[i][2]/Scale};

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
        //super.pause();
    }

    @Override
    public void resume() {
        //super.resume();
    }

    @Override
    public void dispose() {
        //super.dispose();
    }
}
