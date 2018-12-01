package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.dragonballz.game.Actors.ActorBase;

public class DialogScene extends BaseScreen {
    final String[] GokuIdle = {"Goku Idle/GokuIdle_1.png","Goku Idle/GokuIdle_2.png","Goku Idle/GokuIdle_3.png","Goku Idle/GokuIdle_4.png","Goku Idle/GokuIdle_5.png","Goku Idle/GokuIdle_6.png","Goku Idle/GokuIdle_5.png","Goku Idle/GokuIdle_4.png","Goku Idle/GokuIdle_3.png","Goku Idle/GokuIdle_2.png","Goku Idle/GokuIdle_1.png"};
    final String[] PicolloIdle = {"Picollo/PicolloIdle/PicolloIdle_1.png","Picollo/PicolloIdle/PicolloIdle_2.png","Picollo/PicolloIdle/PicolloIdle_3.png","Picollo/PicolloIdle/PicolloIdle_4.png","Picollo/PicolloIdle/PicolloIdle_3.png","Picollo/PicolloIdle/PicolloIdle_2.png","Picollo/PicolloIdle/PicolloIdle_1.png"};

    final Skin SceneSkin =  new Skin(Gdx.files.internal("default/skin/uiskin.json"));

    TextButton PauseButton;

    Texture BackgroundImage = new Texture(Gdx.files.internal("DBZLogoBackground.png"));

    ActorBase GokuActor;
    ActorBase PicolloActor;

    DialogBox dialogBox;

    private int currentDialogIndex = 0;

    String[] Dialog = {"Piccolo: What’s wrong, Goku? Why did you want to meet 500 metres above the battle ground and what’s with the fighting stance?",
            "Goku: Don’t play dumb with me, Piccolo. Chi-Chi told me everything. I know about the affair! You were my friend! How could you sleep with my wife? \n",
            "Piccolo: What? Chi-Chi’s not my type at all! Not enough antennas for my taste. Plus, I would never do that to you. Either she’s lying or …oh no… it can’t be…\n",
            "Goku: What is it?",
            "Piccolo: My evil twin, Diccolo. He must’ve returned. His whole life has been about destroying mine! He’s always been jealous of me.",
            "Goku: An evil twin? Really? This is anime not a soap opera. Just admit that you’ve always been jealous of my hot wife and I might let you off easy. \n",
            "Piccolo: I’m serious! It was Diccolo! And Chi-Chi’s not even that hot. ",
            "Goku: That’s it. I'm going to kick you right in the dragon balls!"};

    DialogScene(){
        super();
        Initialize();
    }

    @Override
    public void Initialize() {



        GokuActor = new ActorBase();
        GokuActor.loadAnimationFromFiles("Idle",GokuIdle,(1/10f),true);
        GokuActor.setPosition(SCREENWIDTH/2 - (SCREENWIDTH/3),SCREENHEIGHT/1.5f);
        GokuActor.setScale(5);
        GokuActor.FlipCurrentAnim();

        PicolloActor = new ActorBase();
        PicolloActor.loadAnimationFromFiles("Idle",PicolloIdle,(1/10f),true);
        PicolloActor.setPosition(SCREENWIDTH/2 + (SCREENWIDTH/3),SCREENHEIGHT/1.5f);
        PicolloActor.setScale(4);

        dialogBox = new DialogBox(0,0,mainStage);
        dialogBox.setDialogSize(SCREENWIDTH,SCREENHEIGHT/3);
        dialogBox.setText(Dialog[currentDialogIndex]);
        dialogBox.alignCenter();
        dialogBox.setFontScale(5);
        dialogBox.setFontColor(Color.WHITE);
        dialogBox.setBackgroundColor(Color.GRAY);


        PauseButton = new TextButton("Options", SceneSkin);
        PauseButton.getLabel().setFontScale(3);

        InitializeButtonListeners();

        mainStage.addActor(GokuActor);
        mainStage.addActor(PicolloActor);
    }

    @Override
    public void Update(float dt) {

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        currentDialogIndex++;
        if (currentDialogIndex >= Dialog.length){
            DBZEngine.GetInstance().LoadScene(new GameScene());
            return super.touchDown(screenX, screenY, pointer, button);
        }
        dialogBox.setText(Dialog[currentDialogIndex]);
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
