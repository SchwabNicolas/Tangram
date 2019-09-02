package ch.ceff.libgdx.tangram;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FillViewport;

import ch.ceff.libgdx.tangram.config.GameConfig;
import ch.ceff.libgdx.tangram.screen.loading.LoadingScreen;

public class Tangram extends Game {
    private static final Logger log = new Logger(Tangram.class.getSimpleName(), Logger.DEBUG);

    private AssetManager assetManager;
    private OrthographicCamera gameCamera;
    private FillViewport screenViewport;
    private SpriteBatch spriteBatch;
    private ShapeRenderer renderer;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assetManager = new AssetManager();
        gameCamera = new OrthographicCamera();
        screenViewport = new FillViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, gameCamera);
        spriteBatch = new SpriteBatch();
        renderer = new ShapeRenderer();

        setScreen(new LoadingScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        renderer.dispose();
        assetManager.dispose();
    }

    public static Logger getLog() {
        return log;
    }

    public OrthographicCamera getGameCamera() {
        return gameCamera;
    }

    public FillViewport getScreenViewport() {
        return screenViewport;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public ShapeRenderer getRenderer() {
        return renderer;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
