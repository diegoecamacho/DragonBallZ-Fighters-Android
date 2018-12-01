package com.dragonballz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.dragonballz.game.Actors.ActorBase;

public class DialogBox extends ActorBase {

    private Label dialogLabel;
    private Label.LabelStyle labelStyle;
    private float padding = 16;

    private Skin skin;

    DialogBox(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("UI/dialog-translucent.png");

        skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));

        dialogLabel = new Label(" ", skin, "default");
        dialogLabel.setWrap(true);
        dialogLabel.setPosition(padding, padding);
        this.setDialogSize(getWidth(), getHeight());

        s.addActor(dialogLabel);
    }

    public void setDialogSize(float width, float height) {
        this.setSize(width, height);
        dialogLabel.setWidth(width - 2 * padding);
        dialogLabel.setHeight(height - 2 * padding);
    }

    public void setText(String text) {
        dialogLabel.setText(text);
    }

    public void setFontScale(float scale) {
        dialogLabel.setFontScale(scale);
    }

    public void setFontColor(Color color) {
        dialogLabel.setColor(color);
    }

    public void setBackgroundColor(Color color) {
        this.setColor(color);
    }

    public void alignTopLeft() {
        dialogLabel.setAlignment(Align.topLeft);
    }

    public void alignCenter() {
        dialogLabel.setAlignment(Align.center);
    }

}