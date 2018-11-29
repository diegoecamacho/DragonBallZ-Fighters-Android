package com.dragonballz.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.zip.GZIPOutputStream;

public class GameScene extends BaseScreen {
    final String[] GokuIdle = {"Goku Idle/GokuIdle_1.png","Goku Idle/GokuIdle_2.png","Goku Idle/GokuIdle_3.png","Goku Idle/GokuIdle_4.png","Goku Idle/GokuIdle_5.png","Goku Idle/GokuIdle_6.png","Goku Idle/GokuIdle_5.png","Goku Idle/GokuIdle_4.png","Goku Idle/GokuIdle_3.png","Goku Idle/GokuIdle_2.png","Goku Idle/GokuIdle_1.png"};
    final Skin skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));

    PlayerActor GokuPlayer;

    Touchpad touchpad;
    float deltaX;
    float deltaY;

    GameScene(){
        super();
        Initialize();
    }

    @Override
    public void Initialize() {
        GokuPlayer = new PlayerActor();
        GokuPlayer.loadAnimationFromFiles("Idle", GokuIdle,0.1f,true);
        GokuPlayer.setPosition(SCREENWIDTH/2,SCREENHEIGHT/2);
        GokuPlayer.setScale(5f);

        touchpad = new Touchpad(40.0f,skin);
        //touchpad.setPosition(SCREENWIDTH/5,SCREENHEIGHT/3);
        touchpad.setResetOnTouchUp(true);
        touchpad.getColor().a = 1.0f;
        touchpad.setWidth(touchpad.getWidth() * 2f);
        touchpad.setHeight(touchpad.getHeight() * 2f);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                deltaX = ((Touchpad) actor).getKnobPercentX();
                deltaY = ((Touchpad) actor).getKnobPercentY();

                Gdx.app.log("Delta X", "" + deltaX);
                Gdx.app.log("Delta Y", "" + deltaY);
            }
        });


        ControllerTable.add(touchpad).width(touchpad.getWidth() * 1.5f).height(touchpad.getHeight() * 1.5f);


        mainStage.addActor(GokuPlayer);
    }

    @Override
    public void Update(float dt) {
        if ( deltaX != 0 || deltaY != 0){
            GokuPlayer.Move(deltaX, deltaY);
        }
        GokuPlayer.boundToWorld();
    }

    @Override
    public void InitializeButtonListeners() {
        super.InitializeButtonListeners();
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
