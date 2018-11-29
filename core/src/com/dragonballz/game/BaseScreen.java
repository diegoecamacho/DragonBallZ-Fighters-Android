package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * <p>Base Class for all screens in the game</p>
 */
public abstract class BaseScreen implements  Screen, InputProcessor {

    static float SCREENWIDTH = Gdx.graphics.getWidth();
    static float SCREENHEIGHT = Gdx.graphics.getHeight();

    protected Container<Table> MenuBarContainer;
    protected Container<Table> TableContainer;
    protected Container<Table> ControllerUIContainer;

    protected Table MenuBarTable;
    protected Table ScreenTable;
    protected Table ControllerTable;

    protected Stage mainStage;
    protected Stage uiStage;

    protected boolean isScenePaused = false;

    BaseScreen(){
        mainStage = new Stage();
        uiStage = new Stage();

        //Menu Bar Layout
        MenuBarContainer = new Container<Table>();
        MenuBarContainer.setSize(SCREENWIDTH, SCREENHEIGHT/6);
        MenuBarContainer.setPosition(0,SCREENHEIGHT - MenuBarContainer.getHeight());
        MenuBarContainer.fill();

        //Debug Statement
        MenuBarContainer.setDebug(true);

        MenuBarTable = new Table();
        MenuBarTable.align(Align.center | Align.left);
        //MenuBarTable.setBounds(0,0,MenuBarContainer.getWidth(),MenuBarContainer.getHeight());
        //Debug Statement
        MenuBarTable.setDebug(true);

        ControllerUIContainer = new Container<Table>();
        ControllerUIContainer.setSize(SCREENWIDTH,SCREENHEIGHT/4);
        ControllerUIContainer.setPosition(0,0);
        ControllerUIContainer.fill();
        //Debug
        ControllerUIContainer.setDebug(true);

        ControllerTable = new Table();
        ControllerTable.align(Align.center);
        //Debug
        ControllerTable.setDebug(true);

        //Screen Layout
        TableContainer = new Container<Table>();
        TableContainer.setSize(SCREENWIDTH/2 ,SCREENHEIGHT - MenuBarContainer.getHeight());
        TableContainer.setPosition((SCREENWIDTH/2 - TableContainer.getWidth()/2),40);
        TableContainer.fill();
        //Debug Statement
        TableContainer.setDebug(true);


        ScreenTable = new Table();
        ScreenTable.setSize(TableContainer.getWidth(),TableContainer.getHeight());
        //Debug Statement
        ScreenTable.setDebug(false);

        ControllerUIContainer.setActor(ControllerTable);
        MenuBarContainer.setActor(MenuBarTable);
        TableContainer.setActor(ScreenTable);

        uiStage.addActor(MenuBarContainer);
        uiStage.addActor(ControllerUIContainer);
        uiStage.addActor(TableContainer);

        //Initialize();
    }

    public abstract void Initialize();

    public abstract void Update(float dt);

    public void InitializeButtonListeners(){
        return;
    }

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(mainStage);
        inputMultiplexer.addProcessor(uiStage);
    }

    @Override
    public void render(float delta) {

        if (isScenePaused){
            delta = 0;
        }
        else{
            delta = Math.min(delta, 1/60.0f);
        }

        mainStage.act(delta);
        uiStage.act(delta);
        Update(delta);

        //Draw
        Gdx.gl.glClearColor(0, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
        uiStage.draw();
    }


    @Override
    public void hide() {
        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.removeProcessor(this);
        inputMultiplexer.removeProcessor(mainStage);
        inputMultiplexer.removeProcessor(uiStage);
    }

    @Override
    public void dispose() {
        mainStage.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
