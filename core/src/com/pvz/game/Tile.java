package com.pvz.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	
	private Texture t;
	public Vector2 tilemapPos;
	public Vector2 worldPos;
	
	public Tile(Texture t, Vector2 tileMapPos, Vector2 worldPos) {
		this.t = t;
		this.tilemapPos = tileMapPos;
		this.worldPos = worldPos;
		
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(t,  worldPos.x, worldPos.y);
	}
	
	public void setTexture(Texture tex) {
		this.t = tex;
	}
}