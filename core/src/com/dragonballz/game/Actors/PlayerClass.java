package com.dragonballz.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


public class PlayerClass extends ActorBase {

    final String[] GokuIdle = {"Goku Idle/GokuID_1.png","Goku Idle/GokuID_2.png","Goku Idle/GokuID_3.png","Goku Idle/GokuID_4.png","Goku Idle/GokuID_3.png","Goku Idle/GokuID_2.png","Goku Idle/GokuID_1.png"};
    final String[] GokuWalk = {"Goku Idle/GokuIdle_1.png","Goku Idle/GokuIdle_2.png","Goku Idle/GokuIdle_3.png","Goku Idle/GokuIdle_4.png","Goku Idle/GokuIdle_5.png","Goku Idle/GokuIdle_6.png","Goku Idle/GokuIdle_5.png","Goku Idle/GokuIdle_4.png","Goku Idle/GokuIdle_3.png","Goku Idle/GokuIdle_2.png","Goku Idle/GokuIdle_1.png"};
    final String[] GokuPunch = {"Goku Idle/GokuPunch_1.png","Goku Idle/GokuPunch_2.png","Goku Idle/GokuPunch_3.png","Goku Idle/GokuPunch_4.png"};
    final String[] GokuHighKick = {"Goku Idle/GokuHighKick_1.png","Goku Idle/GokuHighKick_2.png","Goku Idle/GokuHighKick_3.png","Goku Idle/GokuHighKick_4.png","Goku Idle/GokuHighKick_5.png","Goku Idle/GokuHighKick_6.png"};
   private boolean inAnimation = false;

    public PlayerClass(){
        super();
        this.loadAnimationFromFiles("Idle", GokuIdle,(1/10f),true);
        this.loadAnimationFromFiles("Walk", GokuWalk,(1/10f),true);
        this.loadAnimationFromFiles("Punch", GokuPunch,(1/10f),false);
        this.loadAnimationFromFiles("Kick", GokuHighKick,(1/10f),false);
        setBoundaryPolygon(4);
    }

    public void Move(float x, float y){
        movementDir = new Vector2(x,y);
        if (!inAnimation) {

            if (x == 0 && y == 0) {
                setAnimation("Idle");
            } else {
                if (y > 0) {
                    movementDir.y *= 3;
                }
                setAnimation("Walk");
                if (0 < x && !spriteFlipped) {
                    FlipCurrentAnim();
                    spriteFlipped = true;
                }
                 if (x < 0 && spriteFlipped) {
                     FlipCurrentAnim();
                     spriteFlipped = false;
                 }
                }
            }
            moveBy(movementDir.x * speed, movementDir.y * speed);

        }


    @Override
    public void act(float dt) {
        super.act(dt);
        if (isAnimationFinished() && inAnimation){
            Gdx.app.log("Punch", "Animation Finite");
            inAnimation = false;
        }
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
