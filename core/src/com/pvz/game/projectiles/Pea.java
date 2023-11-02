package com.pvz.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pea extends Projectile {

	public Pea(Vector2 start, int damage) {
		super(0f, 20, new Texture("pea.png"), start, damage);
		
	}
	@Override
	public void render(SpriteBatch batch, float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

}
