package com.pvz.game.screens;

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
import com.pvz.game.Tilemap;
import com.pvz.game.tiles.Tile;

public class GameScreen implements Screen{


	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Tilemap mapObjects;
	private int scrollSpeed = 100;
	private int zoomSpeed = 10;
	private Vector3 unprojectVector = new Vector3();
	private Vector2 worldMousePosition = new Vector2();
	private Vector2 worldPosition = new Vector2();
	private Tile prevHover;

	private TmxMapLoader maploader;
	private TiledMap map;
	private IsometricTiledMapRenderer renderer;
	private Viewport port;

	public GameScreen(SpriteBatch batch) {

		this.batch = batch;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		port = new FitViewport(1920,1080, camera);
//		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		maploader = new TmxMapLoader();
		map = maploader.load("tileset.tmx");
		renderer = new IsometricTiledMapRenderer(map); 
//		camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		mapObjects = new Tilemap(map);

//		map.getLayers().get(0).setOffsetX(-100);
//		map.getLayers().get(0).setOffsetY(-100);
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

//		int mx = Gdx.input.getX();
//		int my = Gdx.input.getY();

//		camera.unproject(unprojectVector.set(mx, my, 0.0f));
//		worldMousePosition.set(unprojectVector.x, unprojectVector.y);

//		if (prevHover!= null) {
//			map.resetTileTexture(prevHover);
//			prevHover = null;
//		}
		//        if (Gdx.input.isTouched()) {
//		Tile t = map.get(worldMousePosition.x, worldMousePosition.y);
//		if (t != null) {
//			Gdx.graphics.setSystemCursor(SystemCursor.Hand);
//			prevHover = t;
//			map.set(t);
//		}else {
//			Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
//		}


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
