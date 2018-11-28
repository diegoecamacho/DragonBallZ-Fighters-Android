package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * <p>Base Class for all screens in the game</p>
 */
public abstract class BaseScreen implements  Screen {

    Stage mainStage;

    BaseScreen(){
        mainStage = new Stage();
    }

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(mainStage);

    }

    @Override
    public void render(float delta) {
        mainStage.act();

        //Draw
        Gdx.gl.glClearColor(0, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
    }


    @Override
    public void hide() {
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.removeProcessor(mainStage);
    }

    @Override
    public void dispose() {
        mainStage.dispose();

    }


}
