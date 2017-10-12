package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.game.MainClass;
import com.mygdx.game.Stage;

public class PlayScreen extends ScreenAdapter{
    public PlayScreen(MainClass game){
        this.game=game;
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

    public Vector3[] Obstaclepos;


    public Box2DDebugRenderer b2dr= new Box2DDebugRenderer();
    public SpriteBatch batch = new SpriteBatch();
    public Texture Player = new Texture("game_elements/obstacle.png");
    public Texture Obstacle = new Texture(("icons/MeshFill-260_12.png"));
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



    Stage stage = new Stage(2,2);

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
        batch.begin();
        for(int i=0;i<PStage.noofobstacles;i++){
            batch.draw(Obstacle,Obstaclepos[i].y-Obstaclepos[i].x ,
                    Obstaclepos[i].z-Obstaclepos[i].x,2*Obstaclepos[i].x,2*Obstaclepos[i].x);
        }
        batch.draw(Player,player.getPosition().x - PlayerRadious ,player.getPosition().y - PlayerRadious,2*PlayerRadious,2*PlayerRadious);

        batch.end();

      //  b2dr.render(world,camera.combined);
    }
    public void update(float delta){
        world.step(1/60f,6,2);
        if(player.getPosition().y > Presentyp)
            camera.position.set(0,player.getPosition().y,0);
        camera.update();
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
        PStage = new Stage(10, 15);
        PStage.obstacles[0] = new Vector3(0.1f, 0.3f, 0.1f);
        PStage.obstacles[1] = new Vector3(0.2f, -0.28f, 0.5f);
        PStage.obstacles[2] = new Vector3(0.15f, -0.3f, 0.9f);
        PStage.obstacles[3] = new Vector3(0.05f, 0.2f, 1f);
        PStage.obstacles[4] = new Vector3(0.25f, 0.15f, 1.3f);
        PStage.obstacles[5] = new Vector3(0.1f, -0.35f, 1.6f);
        PStage.obstacles[6] = new Vector3(0.05f, 0f, 1.75f);
        PStage.obstacles[7] = new Vector3(0.08f, -0.3f, 1.85f);
        PStage.obstacles[8] = new Vector3(0.12f, 0.1f, 2.05f);
        PStage.obstacles[9] = new Vector3(0.1f, 0.25f, 2.18f);
        PStage.obstacles[10] = new Vector3(0.26f, -0.2f, 2.4f);
        PStage.obstacles[11] = new Vector3(0.2f, 0f, 2.9f);
        PStage.obstacles[12] = new Vector3(0.17f, 0.22f, 4.3f);
        PStage.obstacles[13] = new Vector3(0.2f, 0.15f, 4.6f);
        PStage.obstacles[14] = new Vector3(0.1f, -0.2f, 4.85f);

        Obstaclepos = new Vector3[PStage.noofobstacles] ;


            //Obstaclepos[1].x=1;

        for (int i = 0; i < PStage.noofobstacles; i++) {
            Obstaclepos[i]= new Vector3(w*PStage.obstacles[i].x/Scale,w*PStage.obstacles[i].y/Scale,w*PStage.obstacles[i].z/Scale);

            BodyDef def = new BodyDef();
            def.type = BodyDef.BodyType.StaticBody;
            def.position.set(Obstaclepos[i].y, Obstaclepos[i].z);
            def.fixedRotation = true;
            Body b = world.createBody(def);
            CircleShape shape = new CircleShape();
            shape.setRadius(Obstaclepos[i].x);
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
