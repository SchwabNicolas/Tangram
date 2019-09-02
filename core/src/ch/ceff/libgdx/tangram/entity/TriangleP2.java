package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

import ch.ceff.libgdx.tangram.config.GameConfig;

public class TriangleP2 extends Shape {
    public TriangleP2(TextureRegion textureRegion) {
        super(textureRegion, new Polygon());
        super.setBounds(new Polygon(new float[]{
                0, 0,
                getWidth() / 2, getHeight(),
                getWidth(), 0,
                0, 0
        }));
        super.setPosition(GameConfig.WORLD_WIDTH / 2, GameConfig.WORLD_HEIGHT / 2 - getHeight() / 2);
        super.setDefaultBoundsOrigin();
        super.setDefaultRotation();
    }
}
