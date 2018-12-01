package com.dragonballz.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

/**
 * Root Code for Gravity Flip the Game
 */
public class DBZEngine extends Game {
	public static DBZEngine engine;

	BaseScreen activeScreen;

	final public static float gravity = -15f;

	DBZEngine(){
		if (engine == null){
			engine = this;
		}
	}

	/**
	 *<p>Loads the passed Screen</p>
	 *<@param>scene</@param>
	 */
	public void LoadScene(BaseScreen scene){
		if (activeScreen != null)
			activeScreen.dispose();

		activeScreen = scene;
		this.setScreen(activeScreen);

	}

	/**
	 * <P>Get Current Instance of Engine</P>
	 * @return Engine Class
	 */
	public static DBZEngine GetInstance(){
		return  engine;
	}


	@Override
	public void create () {
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		LoadScene(new GameScene());
	}


	@Override
	public void dispose () {
		activeScreen.dispose();
		engine.dispose();

	}
}
