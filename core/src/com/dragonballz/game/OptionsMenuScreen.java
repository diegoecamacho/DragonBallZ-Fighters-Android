package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;


public class OptionsMenuScreen extends BaseScreen {

    Skin sceneSkin;
    Table UITable;
    Label MenuTitle;
    Label AudioText;
    Slider AudioSlider;
    TextButton ReturnButton;


    OptionsMenuScreen(){
        super();
        UITable = new Table();
        UITable.columnDefaults(0).expand(true,true);
        UITable.columnDefaults(1).expand(true,true);
        UITable.columnDefaults(2).expand(true,true);
        UITable.setFillParent(true);
        UITable.setDebug(true);

        sceneSkin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        MenuTitle = new Label("Options Menu", sceneSkin);
        AudioSlider = new Slider(0,1,0.1f,false, sceneSkin);
        Container<Slider> sliderContainer = new Container<Slider>(AudioSlider);
        sliderContainer.setTransform(true);
        sliderContainer.scaleBy(5);
        ReturnButton = new TextButton("Main Menu", sceneSkin);

        MenuTitle.setFontScale(5);
        MenuTitle.setAlignment(Align.center);



        ReturnButton.getLabel().setFontScale(4);



        ReturnButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                DBZEngine.GetInstance().LoadScene(new MainMenuScreen());
                return false;
            }
        });

        UITable.add(MenuTitle).padTop(15).width(Value.percentWidth(.5f,UITable)).height(Value.percentHeight(0.2f,UITable)).center();
        UITable.row();
        UITable.add(sliderContainer).padRight(400);
        UITable.row();
        UITable.add(ReturnButton).width(Value.percentWidth(0.2f,UITable)).height(Value.percentHeight(0.1f,UITable));

        mainStage.addActor(UITable);

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
