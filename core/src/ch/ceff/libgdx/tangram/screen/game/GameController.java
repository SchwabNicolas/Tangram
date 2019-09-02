package ch.ceff.libgdx.tangram.screen.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import ch.ceff.libgdx.tangram.Tangram;
import ch.ceff.libgdx.tangram.config.GameConfig;
import ch.ceff.libgdx.tangram.entity.EntityFactory;
import ch.ceff.libgdx.tangram.entity.Shape;

public class GameController {
    private final Rectangle playableArea;
    private Array<Shape> shapes;

    public GameController(Tangram context) {
        float maxAspectRatio = 16f / 10f;
        float playableHeight = GameConfig.WORLD_WIDTH / maxAspectRatio;
        float playableMargin = (GameConfig.WORLD_HEIGHT - playableHeight) / 2f;

        playableArea = new Rectangle(
                0, playableMargin,
                GameConfig.WORLD_WIDTH, playableHeight
        );
        EntityFactory entityFactory = new EntityFactory(context.getAssetManager());
        shapes = new Array<Shape>();
        shapes.add(entityFactory.createParallelogram(), entityFactory.createSquare());
    }

    public Rectangle getPlayableArea() {
        return playableArea;
    }

    public Array<Shape> getShapes() {
        return shapes;
    }
}
