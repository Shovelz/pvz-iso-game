package com.pvz.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameMap {
	public abstract void render(OrthographicCamera camera);
	public abstract void update(float delta);
	public abstract void dispose();
}
