package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameScene extends BaseScreen {

    final String[] GokuIdle = {"Goku Idle/GokuIdle_1.png","Goku Idle/GokuIdle_2.png","Goku Idle/GokuIdle_3.png","Goku Idle/GokuIdle_4.png","Goku Idle/GokuIdle_5.png","Goku Idle/GokuIdle_6.png","Goku Idle/GokuIdle_5.png","Goku Idle/GokuIdle_4.png","Goku Idle/GokuIdle_3.png","Goku Idle/GokuIdle_2.png","Goku Idle/GokuIdle_1.png"};
    final String[] PicolloIdle = {"Picollo/PicolloIdle/PicolloIdle_1.png","Picollo/PicolloIdle/PicolloIdle_2.png","Picollo/PicolloIdle/PicolloIdle_3.png","Picollo/PicolloIdle/PicolloIdle_4.png","Picollo/PicolloIdle/PicolloIdle_3.png","Picollo/PicolloIdle/PicolloIdle_2.png","Picollo/PicolloIdle/PicolloIdle_1.png"};

    final Skin SceneSkin =  new Skin(Gdx.files.internal("default/skin/uiskin.json"));
   // final Skin CustomSkin =  new Skin(Gdx.files.internal("default/DIEGO/Pause.json"));

    TextButton PauseButton;

    Texture BackgroundImage = new Texture(Gdx.files.internal("Background.jpg"));

    PlayerActor GokuActor;
    PlayerActor PicolloActor;

    GameScene(){
        super();

        GokuActor = new PlayerActor();
        GokuActor.loadAnimationFromFiles("Idle",GokuIdle,0.1f,true);
        GokuActor.setPosition(SCREENWIDTH/2 - (SCREENWIDTH/4),SCREENHEIGHT/2);
        GokuActor.setScale(5);
        GokuActor.FlipCurrentAnim(true);

        PicolloActor = new PlayerActor();
        PicolloActor.loadAnimationFromFiles("Idle",PicolloIdle,0.1f,true);
        PicolloActor.setPosition(SCREENWIDTH/2 + (SCREENWIDTH/4),SCREENHEIGHT/2);
        PicolloActor.setScale(5);
        PicolloActor.FlipCurrentAnim(false);

        PauseButton = new TextButton("Options", SceneSkin);
        PauseButton.getLabel().setFontScale(3);

        InitializeButtonListeners();

        mainStage.addActor(GokuActor);
        mainStage.addActor(PicolloActor);
    }

    @Override
    public void Initialize() {

    }

    @Override
    public void Update(float dt) {

    }

    @Override
    public void InitializeButtonListeners(){
        PauseButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                Gdx.app.debug("Buttom Press", "Flip Sprite");
                // GokuActor.FlipCurrentAnim(true);
                //DBZEngine.GetInstance().LoadScene(new MainMenuScreen());
                return  true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        mainStage.getBatch().begin();
        mainStage.getBatch().draw(BackgroundImage,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainStage.getBatch().end();
        mainStage.draw();
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
