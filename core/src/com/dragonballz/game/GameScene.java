package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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


    Touchpad touchpad;
    Button PunchButton;
    Button KickButton;
    float deltaX;
    float deltaY;

    GameScene(){
        super();
        Initialize();
    }

    @Override
    public void Initialize() {
        GokuPlayer = new PlayerClass();

        GokuPlayer.setPosition(SCREENWIDTH/2,SCREENHEIGHT/2);
        GokuPlayer.setScale(4f);
        GokuPlayer.setDebug(true);

        PiccoloAI = new AIEnemy(GokuPlayer);
        PiccoloAI.setPosition(SCREENWIDTH/2 - SCREENWIDTH/4,SCREENHEIGHT/2);
        PiccoloAI.setScale(4f);
        PiccoloAI.setDebug(true);



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

        mainStage.addActor(GokuPlayer);
        mainStage.addActor(PiccoloAI);
    }


    @Override
    public void Update(float dt) {
        GokuPlayer.Move(deltaX, deltaY);
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
                Gdx.app.log("Punch", "Punncnajbfjkabsnfd");
                GokuPlayer.Punch();
                return false;
            }
        });

        KickButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                Gdx.app.log("Punch", "Punncnajbfjkabsnfd");
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
