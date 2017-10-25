package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;

/**
 * Created by suraj on 14/10/17.
 */

public class Assets {
    public static  Music button_tune;
    public static Music back_tune ;
    public static  Music gameOver_tune;

    public static Texture Parent;
    public static TextureRegion BackArrow;
    public static TextureRegion CircledPlay;
    public static TextureRegion CircledPlayWhite;
    public static TextureRegion Connect;
    public static TextureRegion ConnectWhite;
    public static TextureRegion Create;
    public static TextureRegion Delete;
    public static TextureRegion DeleteInBox;
    public static TextureRegion DeleteWhite;
    public static TextureRegion Facebook;
    public static TextureRegion FacebookIn;
    public static TextureRegion GoBack;
    public static TextureRegion GooglePlus;
    public static TextureRegion GooglePlusIn;
    public static TextureRegion MusicCircle;
    public static TextureRegion MusicIn;
    public static TextureRegion NavObs;
    public static TextureRegion Newuser;
    public static TextureRegion Obstacle;
    public static TextureRegion PalleteBB;
    public static TextureRegion PlayButton;
    public static TextureRegion Player;
    public static TextureRegion Resize;
    public static TextureRegion Save;
    public static TextureRegion Settings;
    public static TextureRegion Share;
    public static TextureRegion Speaker;
    public static TextureRegion SpeakerIn;
    public static TextureRegion UserGroups;
    public static TextureRegion White;
    public static TextureRegion Black;
    public static TextureRegion Wall;
    public static TextureRegion Login;
    public static TextureRegion Logout;
    public static TextureRegion LoveIn;
    public static TextureRegion LoveBlack;
    public static TextureRegion LoveWhite;
    public static TextureRegion Restart;


    public static void load () {
        System.out.print("Assets Being called");
        Parent= new Texture(Gdx.files.internal("spritesheet.png"));
        BackArrow= new TextureRegion(Parent,783,1536,260,260);
        CircledPlay= new TextureRegion(Parent,1155,1898,260,260 );
        CircledPlayWhite= new TextureRegion(Parent ,894,1898,260,260);
        Connect= new TextureRegion(Parent ,0,0,500,500);
        ConnectWhite= new TextureRegion(Parent,2057,501,500,500 );
        Create= new TextureRegion(Parent ,261,1536,260,260);
        Delete= new TextureRegion(Parent ,522,1536,260,260);
        DeleteInBox= new TextureRegion(Parent ,0,501,433,406);
        DeleteWhite= new TextureRegion(Parent ,1044,1536,260,260);
        Facebook= new TextureRegion(Parent ,0,1536,260,260);
        FacebookIn= new TextureRegion(Parent,1002,0,512,512 );
        GoBack= new TextureRegion(Parent,894,1797,100,100 );
        GooglePlus= new TextureRegion(Parent,1002,513,500,500 );
        GooglePlusIn= new TextureRegion(Parent ,1515,0,512,512);
        MusicCircle= new TextureRegion(Parent,1515,513,500,500);
        MusicIn= new TextureRegion(Parent ,501,0,500,500);
        NavObs= new TextureRegion(Parent ,501,501,433,405);
        Newuser= new TextureRegion(Parent ,1305,1536,260,260);
        Obstacle= new TextureRegion(Parent,641,908,240,241 );
        PalleteBB= new TextureRegion(Parent,1677,1898,260,260 );
        PlayButton= new TextureRegion(Parent ,2057,0,500,500);
        Player= new TextureRegion(Parent ,0,908,640,627);
        Resize= new TextureRegion(Parent,2057,1680,433,406 );
        Save= new TextureRegion(Parent,2057,1273,433,406 );
        Settings= new TextureRegion(Parent ,1515,1014,500,500);
        Share= new TextureRegion(Parent,1566,1536,260,260 );
        Speaker= new TextureRegion(Parent ,641,1150,260,260);
        SpeakerIn= new TextureRegion(Parent,1002,1014,500,500 );
        UserGroups= new TextureRegion(Parent ,1416,1898,260,260);
        Wall= new TextureRegion(Parent ,2028,0,28,572);
        White= new TextureRegion(Parent ,2057,1002,480,270);
        Black = new TextureRegion(Parent,0,1797,893,410);
        Logout= new TextureRegion(new Texture(Gdx.files.internal("Logout.png")));
        Login= new TextureRegion(new Texture(Gdx.files.internal("Login.png")));
        LoveWhite= new TextureRegion(new Texture(Gdx.files.internal("LoveWhite.png")));
        LoveBlack= new TextureRegion(new Texture(Gdx.files.internal("LoveBlack.png")));
        LoveIn= new TextureRegion(new Texture(Gdx.files.internal("LoveIn.png")));
        Restart = new TextureRegion(new Texture(Gdx.files.internal("Restart.png")));

        back_tune = Gdx.audio.newMusic(Gdx.files.internal("sounds/homescreen_music-not_to_be_played_when_user_plays_game.mp3"));
        button_tune = Gdx.audio.newMusic(Gdx.files.internal("sounds/Remaining_all_buttons.mp3"));
        gameOver_tune = Gdx.audio.newMusic(Gdx.files.internal("sounds/game_over_after _ball_hits_obstacle.wav"));



    }

}
