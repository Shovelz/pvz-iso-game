package com.pvz.game.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractTile {
	
	protected Texture texture;
	protected Vector2 tilemapPos;
	protected Vector2 worldPos;
	
	public AbstractTile(Texture t, Vector2 tileMapPos, Vector2 wPos) {
		texture = t;
		tilemapPos = tileMapPos;
		worldPos = wPos;
		
	}
	
	public Texture getTexture() {
		return texture;
	};
	
	public Vector2 getTilemapPos() {
		return tilemapPos;
	}

	public Vector2 getWorldPos() {
		return worldPos;
	}

	public abstract void render(SpriteBatch batch);
	
	public abstract void setTexture(Texture tex); 
}