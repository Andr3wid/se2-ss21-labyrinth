package se2.gruppe2.moving_maze;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    final MovingMazeGame game;
    OrthographicCamera camera;

    public GameScreen(final MovingMazeGame game) {
        this.game = game;
        camera = MovingMazeGame.getStandardizedCamera();
    }

    @Override
    public void show() {
        System.out.println("Gamescreen has been shown!");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Game screen", 100, 100);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
