package com.pvz.game.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pvz.game.IsoGame;
import com.pvz.game.TilemapOverlay;
import com.pvz.game.tiles.AbstractTile;
import com.pvz.game.tiles.PlantTile;
import com.pvz.game.plants.*;
public class GameScreen implements Screen{


	private SpriteBatch batch;
	private OrthographicCamera camera;
	private TilemapOverlay mapObjects;
	private int scrollSpeed = 100;
	private int zoomSpeed = 5;
	private Vector3 unprojectVector = new Vector3();
	private Vector2 worldMousePosition = new Vector2();
	private AbstractTile prevHover;

	private TmxMapLoader maploader;
	private TiledMap map;
	private IsometricTiledMapRenderer renderer;
	private Viewport port;
	
	private List<Plant> plants = new ArrayList<Plant>();
	private PlantLoader plantLoader;
	
	public GameScreen(SpriteBatch batch) {

		this.batch = batch;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		port = new FitViewport(1920/3,1080/3, camera);
		camera.position.set(170, 0, 0);
		maploader = new TmxMapLoader();
		map = maploader.load("tileset.tmx");
		renderer = new IsometricTiledMapRenderer(map); 
//		camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		mapObjects = new TilemapOverlay(map);

//		map.getLayers().get(0).setOffsetX(-200);
//		map.getLayers().get(0).setOffsetY(-100);
		mapObjects.fillMap();
	}



	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	public void handleInput(float delta) {
		if (Gdx.input.isKeyPressed(Keys.A))
			camera.position.x -= delta * scrollSpeed;
		if (Gdx.input.isKeyPressed(Keys.D))
			camera.position.x += delta * scrollSpeed;
		if (Gdx.input.isKeyPressed(Keys.W))
			camera.position.y += delta * scrollSpeed;
		if (Gdx.input.isKeyPressed(Keys.S))
			camera.position.y -= delta * scrollSpeed;

		if (Gdx.input.isKeyPressed(Keys.Q))
			camera.zoom = Math.min(camera.zoom + zoomSpeed * delta, 8.0f);
		if (Gdx.input.isKeyPressed(Keys.E))
			camera.zoom = Math.max(camera.zoom - zoomSpeed * delta, 0.5f);

		
	}
	
	public void update(float delta) {
		handleInput(delta);
		
		camera.update();
		renderer.setView(camera);
		
	}
	@Override
	public void render(float delta) {

		int mx = Gdx.input.getX();
		int my = Gdx.input.getY();

		camera.unproject(unprojectVector.set(mx, my, 0.0f));
		worldMousePosition.set(unprojectVector.x, unprojectVector.y);

		if (prevHover!= null) {
			mapObjects.resetTileTexture(prevHover);
			prevHover = null;
		}
		
		AbstractTile t = mapObjects.get(worldMousePosition.x, worldMousePosition.y);
		if (t != null) {
			Gdx.graphics.setSystemCursor(SystemCursor.Hand);
			prevHover = t;
			mapObjects.set(t, new PlantTile(new Texture("peashooter.png"), new Vector2(0,0), new Vector2(50, 50)));
		}else {
			Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
		}

	    if (Gdx.input.isTouched() && t != null) {
		        
	    	mapObjects.plant(t, t);
		}

		//        System.out.println(t.tilemapPos.x);
		//        System.out.println(t.tilemapPos.y);
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	
		mapObjects.render(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		port.update(width, height);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
