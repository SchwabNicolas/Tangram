package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

import ch.ceff.libgdx.tangram.config.GameConfig;

public class TriangleG1 extends Shape {
    public TriangleG1(TextureRegion textureRegion) {
        super(textureRegion, new Polygon());
        super.setBounds(new Polygon(new float[]{
                0, getHeight(),
                getWidth() / 2, 0,
                getWidth(), getHeight(),
                0, getHeight()
        }));
        super.setPosition(GameConfig.WORLD_WIDTH / 2, GameConfig.WORLD_HEIGHT / 2 + getHeight() / 2);
        super.setDefaultBoundsOrigin();
        super.setDefaultRotation();
        super.setShapeColor(new Color(Color.valueOf("#FF914F")));
    }
}
