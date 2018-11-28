package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameScene extends BaseScreen {

    final Skin SceneSkin =  new Skin(Gdx.files.internal("default/skin/uiskin.json"));
   // final Skin CustomSkin =  new Skin(Gdx.files.internal("default/DIEGO/Pause.json"));
    Table UITable;

    TextButton PauseButton;

    Texture BackgroundImage = new Texture(Gdx.files.internal("Background.jpg"));

    PlayerActor playerActor;

    GameScene() {
        super();
        UITable = new Table();
        UITable.setDebug(true);
        UITable.columnDefaults(0).expandX();
        UITable.columnDefaults(1).expand();
        UITable.setFillParent(true);



        PauseButton = new TextButton("Options", SceneSkin);
        PauseButton.getLabel().setFontScale(3);

        UITable.add(PauseButton).left().height(200).width(200).padLeft(50).padTop(100);
        UITable.row();
        UITable.add().expand();



        mainStage.addActor(UITable);
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
