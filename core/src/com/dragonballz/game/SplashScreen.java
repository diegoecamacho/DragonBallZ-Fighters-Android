package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class SplashScreen extends BaseScreen {
    Table UITable;

    Skin SceneSkin;

    Label SplashText;

    float timeElapsed;
    final float TOTALLOADSCREENTIME = 1f;

    SplashScreen()
    {
        super();

        UITable = new Table();
        UITable.columnDefaults(0).expand();
        UITable.columnDefaults(1).expand();
        UITable.columnDefaults(2).expand();
        UITable.setFillParent(true);
        UITable.setDebug(true);

        SceneSkin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        SplashText = new Label("Splash Screen" , SceneSkin);

        SplashText.setFontScale(8);

        UITable.add();
        UITable.row();
        UITable.add(SplashText);
        UITable.row();
        UITable.add();

        mainStage.addActor(UITable);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        timeElapsed += delta;
        if (timeElapsed >= TOTALLOADSCREENTIME) {
            DBZEngine.GetInstance().LoadScene(new MainMenuScreen());
        }
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
