package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

import ch.ceff.libgdx.tangram.config.GameConfig;

public class TriangleP1 extends Shape {
    public TriangleP1(TextureRegion textureRegion) {
        super(textureRegion, new Polygon());
        super.setBounds(new Polygon(new float[]{
                getWidth(), 0,
                0, getHeight() / 2,
                getWidth(), getHeight(),
                getWidth(), 0
        }));
        super.setPosition(GameConfig.WORLD_WIDTH / 2 + getWidth() * 1.5F, GameConfig.WORLD_HEIGHT / 2 + getWidth());
        super.setDefaultBoundsOrigin();
        super.setDefaultRotation();
    }
}
