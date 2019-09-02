package ch.ceff.libgdx.tangram.screen.loading;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;

import ch.ceff.libgdx.tangram.Tangram;
import ch.ceff.libgdx.tangram.config.GameConfig;
import ch.ceff.libgdx.tangram.entity.Logo;
import ch.ceff.libgdx.tangram.screen.game.GameScreen;
import ch.ceff.libgdx.tangram.util.Utils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class LoadingRenderer implements Disposable {
    private static final float LOADING_BAR_WIDTH = GameConfig.WORLD_WIDTH / 2;
    private static final float LOADING_BAR_HEIGHT = GameConfig.WORLD_HEIGHT / 25;
    private static final Logger log = new Logger(LoadingRenderer.class.getName(), Logger.DEBUG);

    private Stage stage;
    private Logo logo;
    private OrthographicCamera camera;
    private ShapeRenderer renderer;
    private Tangram context;
    private boolean hasLoadedSplash;
    private float realLoadingBarWidth;
    private float percent;

    public LoadingRenderer(Tangram context) {
        stage = new Stage(context.getScreenViewport(), context.getSpriteBatch());
        camera = context.getGameCamera();
        renderer = context.getRenderer();
        realLoadingBarWidth = LOADING_BAR_WIDTH;
        this.context = context;
    }

    public void render(TextureAtlas splashTextureAtlas, float delta, float progress) {
        Utils.clearScreen();

        percent = Interpolation.linear.apply(percent, progress, 0.1f);
        realLoadingBarWidth = LOADING_BAR_WIDTH * percent;

        renderer.setProjectionMatrix(stage.getViewport().getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(new Color(Color.valueOf("#53C5FB")));
        renderer.rect(
                GameConfig.WORLD_WIDTH / 2 - LOADING_BAR_WIDTH / 2,
                GameConfig.WORLD_HEIGHT / 2 - LOADING_BAR_HEIGHT * 3,
                realLoadingBarWidth,
                LOADING_BAR_HEIGHT
        );
        renderer.end();

        if (splashTextureAtlas != null && !hasLoadedSplash) {
            logo = new Logo(splashTextureAtlas);

            logo.addAction(
                    sequence(
                            Actions.scaleTo(1.0f, 1.0f, 0.20f, Interpolation.smooth),
                            fadeIn(0.25f),
                            Actions.delay(0.5f),
                            fadeOut(0.25f),
                            run(new Runnable() {
                                @Override
                                public void run() {
                                    context.setScreen(new GameScreen(context));
                                }
                            })
                    )
            );
            stage.addActor(logo);

            hasLoadedSplash = true;
        }

        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
