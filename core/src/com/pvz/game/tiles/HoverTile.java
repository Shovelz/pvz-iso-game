package com.pvz.game.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class HoverTile extends AbstractTile{


	public HoverTile(Vector2 tileMapPos, Vector2 worldPos) {
		super(null, tileMapPos, worldPos);
	}

	@Override
	public void render(SpriteBatch batch) {
		if(this.texture != null) {
			Color color = batch.getColor();

			float oldAlpha = color.a;
			color.a = 0.5f;

			batch.setColor(color);
			batch.draw(texture, worldPos.x, worldPos.y);

			color.a = oldAlpha;
			batch.setColor(color);

		}
	}

	@Override
	public void setTexture(Texture tex) {
		this.texture = tex;
	}




}