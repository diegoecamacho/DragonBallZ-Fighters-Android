package com.dragonballz.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;

public class GameOverScreen extends BaseScreen {
    Table UITable;

    Skin SceneSkin;

    Label HighScoreText;
    TextButton MainMenuButton;

    GameOverScreen()
    {
        super();

        UITable = new Table();
        UITable.columnDefaults(0).expand();
        UITable.columnDefaults(1).expand();
        UITable.columnDefaults(2).expand();
        UITable.setFillParent(true);
        UITable.setDebug(true);


        SceneSkin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        HighScoreText = new Label("Game Over Menu" , SceneSkin);
        MainMenuButton = new TextButton("Main Menu", SceneSkin);

        HighScoreText.setFontScale(8);
        MainMenuButton.getLabel().setFontScale(4);

        MainMenuButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                DBZEngine.GetInstance().LoadScene(new MainMenuScreen());
                return false;
            }
        });

        UITable.add(HighScoreText);
        UITable.row();

        UITable.add();
        UITable.row();
        UITable.add(MainMenuButton).width(Value.percentWidth(0.2f,UITable)).height(Value.percentHeight(0.1f,UITable));

        mainStage.addActor(UITable);
    }

    @Override
    public void Initialize() {

    }

    @Override
    public void Update(float dt) {

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
