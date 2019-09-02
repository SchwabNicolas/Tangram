package ch.ceff.libgdx.tangram.screen.loading;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ch.ceff.libgdx.tangram.Tangram;

public class LoadingScreen extends ScreenAdapter {

    private final Tangram context;
    private final AssetManager assetManager;
    private LoadingRenderer renderer;

    public LoadingScreen(Tangram context) {
        this.context = context;
        this.assetManager = context.getAssetManager();
        renderer = new LoadingRenderer(context);

        assetManager.load("splash/splash.atlas", TextureAtlas.class);
        assetManager.finishLoading();
        assetManager.get("splash/splash.atlas", TextureAtlas.class);

        assetManager.load("gameplay/shapes.atlas", TextureAtlas.class);
    }

    @Override
    public void render(float delta) {
        renderer.render(assetManager.get("splash/splash.atlas", TextureAtlas.class), delta, assetManager.getProgress());

        if (assetManager.update()) {
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
