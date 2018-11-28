package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;

import javax.swing.text.html.Option;

public class MainMenuScreen extends BaseScreen {


    //Table UITable;
    Skin SceneSkin;
    Image DBZLogo;
    Image DBZBackground;
    TextButton PlayButton;
    TextButton OptionsButtom;
    TextButton ScoreButton;


    @Override
    public void Initialize() {
        //UITable = new Table();
        //UITable.columnDefaults(0).expand();
        //UITable.columnDefaults(1).expand();
        //UITable.columnDefaults(2).expand();
        //UITable.setFillParent(true);
        //UITable.setDebug(true);

        DBZBackground = new Image(new Texture(Gdx.files.internal("DBZLogoBackground.png")));
        DBZBackground.setSize(SCREENWIDTH,SCREENHEIGHT);
        DBZLogo = new Image(new Texture(Gdx.files.internal("DBZLogo.png")));

        SceneSkin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));

        PlayButton = new TextButton("Play", SceneSkin);
        PlayButton.setOrigin(Align.center);
        PlayButton.getLabel().setFontScale(4);

        OptionsButtom = new TextButton("Options", SceneSkin);
        OptionsButtom.setOrigin(Align.center);
        OptionsButtom.setTransform(true);
        OptionsButtom.setScale(2);

        ScoreButton = new TextButton("Score", SceneSkin);
        ScoreButton.setOrigin(Align.center);
        ScoreButton.getLabel().setFontScale(4);

        InitializeButtonListeners();

        mainStage.addActor(DBZBackground);

       MenuBarTable.add(OptionsButtom).size(OptionsButtom.getWidth(),OptionsButtom.getHeight()+ 30).padLeft(OptionsButtom.getWidth() * 1.5f);

       ScreenTable.add(DBZLogo); // Title
       ScreenTable.row().padTop(SCREENHEIGHT/22);
       ScreenTable.add(PlayButton).width(PlayButton.getWidth() * 10);
       ScreenTable.row().padTop(SCREENHEIGHT/12);
       ScreenTable.add(ScoreButton).width(PlayButton.getWidth() * 10);
    }

    @Override
    public void InitializeButtonListeners(){
        PlayButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                DBZEngine.GetInstance().LoadScene(new GameScene());
                return false;
            }
        });

        OptionsButtom.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                DBZEngine.GetInstance().LoadScene(new OptionsMenuScreen());
                return false;
            }
        });

        ScoreButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                DBZEngine.GetInstance().LoadScene(new HighScoreScreen());
                return false;
            }
        });
    }


    @Override
    public void Update(float dt) {

    }

    @Override
    public void dispose() {
        super.dispose();
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
