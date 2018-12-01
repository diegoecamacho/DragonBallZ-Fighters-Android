package com.dragonballz.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dragonballz.game.DBZEngine;

import javax.tools.Diagnostic;

public class AIEnemy extends ActorBase {

    final String[] PiccolloIdle = {"Picollo/PicolloIdle/PicolloIdle_1.png","Picollo/PicolloIdle/PicolloIdle_2.png","Picollo/PicolloIdle/PicolloIdle_3.png","Picollo/PicolloIdle/PicolloIdle_4.png","Picollo/PicolloIdle/PicolloIdle_3.png","Picollo/PicolloIdle/PicolloIdle_2.png","Picollo/PicolloIdle/PicolloIdle_1.png"};
    final String[] PiccolloPunch = {"Picollo/PicolloPunch_1.png","Picollo/PicolloPunch_2.png","Picollo/PicolloPunch_3.png","Picollo/PicolloPunch_4.png"};
    final String[] PiccolloKick = {"Picollo/PicoloKick_1.png","Picollo/PicoloKick_2.png","Picollo/PicoloKick_3.png","Picollo/PicoloKick_4.png","Picollo/PicoloKick_5.png","Picollo/PicoloKick_6.png"};
    PlayerClass player;
    private boolean inAnimation = false;

    public float timeBetweenAttacks = 10;
    float timePassed = 0;


    public AIEnemy(PlayerClass player , Stage stage){
        this.player = player;
        this.loadAnimationFromFiles("Idle", PiccolloIdle,(1/10f),true);
        this.loadAnimationFromFiles("Punch", PiccolloPunch,(1/10f),true);
        this.loadAnimationFromFiles("Kick", PiccolloKick,(1/10f),true);
        speed = 10;
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        ApplyGravity(dt);
        if (getPosition().dst(player.getPosition()) < 300 && !inAnimation)
        {
            int random = MathUtils.random(2);
            switch (random){
                case 0:
                    Punch();
                case 1:
                    Kick();
            }
        }
        if (isAnimationFinished() && inAnimation){

            inAnimation = false;
        }

        else if (getPosition().dst(player.getPosition()) >  300 )
             Move();






    }

    public void Move() {
        movementDir = player.getPosition().sub(getPosition());
        movementDir = movementDir.nor();

        if (!inAnimation) {

            if (movementDir.x == 0 && movementDir.y == 0) {
                setAnimation("Idle");
            }
            else {
                ResetElapsed();
                setAnimation("Idle");
                if (movementDir.y > 0) {
                    movementDir.y *= 3;
                }
                if (0 < movementDir.x && !spriteFlipped) {
                    FlipCurrentAnim();
                    spriteFlipped = true;
                }
                if (movementDir.x < 0 && spriteFlipped) {
                    FlipCurrentAnim();
                    spriteFlipped = false;
                }
            }
            moveBy(movementDir.x * speed, movementDir.y * speed);
        }
        Gdx.app.log("Move", " " + getPosition().dst(player.getPosition()));


    }

    void ApplyGravity(float dt){
        moveBy(0, DBZEngine.gravity);
    }

    public void Punch(){
        if (!inAnimation){
            ResetElapsed();
            setAnimation("Punch");
            player.TakeDamage(0.025f);
            inAnimation = true;
        }
    }

    public void Kick(){
        if (!inAnimation){
            ResetElapsed();
            setAnimation("Kick");
            player.TakeDamage(0.025f);
            inAnimation = true;
        }
    }

}
