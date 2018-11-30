package com.dragonballz.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import javax.tools.Diagnostic;

public class AIEnemy extends ActorBase {

    final String[] PiccolloIdle = {"Picollo/PicolloIdle/PicolloIdle_1.png","Picollo/PicolloIdle/PicolloIdle_2.png","Picollo/PicolloIdle/PicolloIdle_3.png","Picollo/PicolloIdle/PicolloIdle_4.png","Picollo/PicolloIdle/PicolloIdle_3.png","Picollo/PicolloIdle/PicolloIdle_2.png","Picollo/PicolloIdle/PicolloIdle_1.png"};
    final String[] PiccolloPunch = {"Picollo/PicolloPunch_1.png","Picollo/PicolloPunch_2.png","Picollo/PicolloPunch_3.png","Picollo/PicolloPunch_4.png"};
    final String[] PiccolloKick = {"Picollo/PicoloKick_1.png","Picollo/PicoloKick_2.png","Picollo/PicoloKick_3.png","Picollo/PicoloKick_4.png","Picollo/PicoloKick_5.png","Picollo/PicoloKick_6.png"};
    PlayerClass player;
    private boolean inAnimation;

    public AIEnemy(PlayerClass player){
        this.player = player;
        this.loadAnimationFromFiles("Idle", PiccolloIdle,(1/10f),true);
        this.loadAnimationFromFiles("Punch", PiccolloPunch,(1/10f),true);
        this.loadAnimationFromFiles("Kick", PiccolloKick,(1/10f),true);
    }

    @Override
    public void act(float dt) {
        super.act(dt);



        if (getPosition().dst(player.getPosition()) < getWidth() * 3 && !inAnimation)
        {
            int random = MathUtils.random(2);
           switch (random){
               case 0:
                   Punch();
               case 1:
                   Kick();
           }
        }

        Move();


        if (isAnimationFinished() && inAnimation){
            Gdx.app.log("Punch", "Animation Finite");
            inAnimation = false;
        }
    }

    public void Move() {
        movementDir = player.getPosition().sub(getPosition());
        movementDir = movementDir.nor();

        if (!inAnimation) {

            if (movementDir.x == 0 && movementDir.y == 0) {
                setAnimation("Idle");
            }
            else {
                if (movementDir.y > 0) {
                    movementDir.y *= 3;
                }
                setAnimation("Walk");
                if (0 < movementDir.x && !spriteFlipped) {
                    FlipCurrentAnim();
                    spriteFlipped = true;
                }
                if (movementDir.x < 0 && spriteFlipped) {
                    FlipCurrentAnim();
                    spriteFlipped = false;
                }
            }
        }
        if (getPosition().dst(player.getPosition()) <  10 )
        moveBy(movementDir.x * speed, movementDir.y * speed);
    }

    public void Punch(){
        if (!inAnimation){
            ResetElapsed();
            setAnimation("Punch");
            inAnimation = true;
        }
    }

    public void Kick(){
        if (!inAnimation){
            ResetElapsed();
            setAnimation("Kick");
            inAnimation = true;
        }
    }

}
