package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.dragonballz.game.Actors.AIEnemy;
import com.dragonballz.game.Actors.ActorBase;
import com.dragonballz.game.Actors.PlayerClass;

public class GameScene extends BaseScreen {

    final Skin skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));

    PlayerClass GokuPlayer;
    AIEnemy PiccoloAI;

    ActorBase GokuHealth;
    ActorBase PicolloHealth;

    static Music BattleMusic;


    Touchpad touchpad;
    Button PunchButton;
    Button KickButton;
    float deltaX;
    float deltaY;
    private Image DBZBackground;

    GameScene(){
        super();
        Initialize();
    }

    public static void  GameOver(Actor actor){
        actor.remove();
        BattleMusic.pause();
        DBZEngine.GetInstance().LoadScene(new MainMenuScreen());
    }

    @Override
    public void Initialize() {

        DBZBackground = new Image(new Texture(Gdx.files.internal("DBZLogoBackground.png")));
        DBZBackground.setSize(SCREENWIDTH,SCREENHEIGHT);

        GokuPlayer = new PlayerClass();

        GokuPlayer.setPosition(SCREENWIDTH/2 - SCREENWIDTH/4,SCREENHEIGHT/2);
        GokuPlayer.setScale(4f);
        GokuPlayer.setDebug(true);

        GokuHealth = new ActorBase();
        GokuHealth.loadTexture("GreenBar.png");
        GokuHealth.setOrigin(GokuHealth.getX()/2,GokuHealth.getY()/2);
        GokuHealth.setPosition(120,SCREENHEIGHT-120);
        GokuHealth.setScale(50*GokuPlayer.GetHealth(),4f);

        PiccoloAI = new AIEnemy(GokuPlayer, mainStage);
        PiccoloAI.setPosition(SCREENWIDTH/2 + SCREENWIDTH/4,SCREENHEIGHT/2);
        PiccoloAI.setScale(4f);
        PiccoloAI.setDebug(true);

        PicolloHealth = new ActorBase();
        PicolloHealth.loadTexture("GreenBar.png");
        PicolloHealth.setOrigin(PicolloHealth.getX()/2,PicolloHealth.getY()/2);
        PicolloHealth.setPosition(SCREENWIDTH-110,SCREENHEIGHT-80);
        PicolloHealth.rotateBy(180);
        PicolloHealth.setScale(50*PiccoloAI.GetHealth(),4f);

        GokuPlayer.SetFocus(PiccoloAI);

        BattleMusic = Gdx.audio.newMusic(Gdx.files.internal("PiccoloBattle.mp3"));
        BattleMusic.setLooping(true);
        BattleMusic.setVolume(1.0f);
        BattleMusic.play();



        PunchButton = new Button(skin);
        PunchButton.setTransform(true);
        PunchButton.scaleBy(3);

        KickButton = new Button(skin);
        KickButton.setTransform(true);
        KickButton.setScale(3);


        touchpad = new Touchpad(40.0f,skin);

        touchpad.setResetOnTouchUp(true);
        touchpad.getColor().a = 1.0f;
        touchpad.setWidth(touchpad.getWidth() * 2f);
        touchpad.setHeight(touchpad.getHeight() * 2f);



        InitializeButtonListeners();

        ControllerTable.add(touchpad).width(touchpad.getWidth() * 1.5f).height(touchpad.getHeight() * 1.5f).padRight(SCREENWIDTH/1.5F);
        ControllerTable.add(KickButton).padRight(SCREENWIDTH/12);
        ControllerTable.add(PunchButton);

        mainStage.addActor(DBZBackground);

        mainStage.addActor(GokuHealth);
        mainStage.addActor(PicolloHealth);

        mainStage.addActor(GokuPlayer);
        mainStage.addActor(PiccoloAI);
    }


    @Override
    public void Update(float dt) {
        GokuPlayer.Move(deltaX, deltaY);
        GokuHealth.setScale(50*GokuPlayer.GetHealth(),4f);
        PicolloHealth.setScale(50*PiccoloAI.GetHealth(),4f);
    }

    @Override
    public void InitializeButtonListeners() {
        super.InitializeButtonListeners();
        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                deltaX = ((Touchpad) actor).getKnobPercentX();
                deltaY = ((Touchpad) actor).getKnobPercentY();

                Gdx.app.log("Delta X", "" + deltaX);
                Gdx.app.log("Delta Y", "" + deltaY);
            }
        });

        PunchButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                GokuPlayer.Punch();
                return false;
            }
        });

        KickButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                GokuPlayer.Kick();
                return false;
            }
        });
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
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

}
