package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

import ch.ceff.libgdx.tangram.config.GameConfig;

public class Square extends Shape {
    public Square(TextureRegion textureRegion) {
        super(textureRegion, new Polygon());
        super.setBounds(new Polygon(new float[]{
                getWidth() / 2f, 0,
                getWidth(), getHeight() / 2f,
                getWidth() / 2f, getHeight(),
                0, getHeight() / 2
        }));
        super.setPosition(GameConfig.WORLD_WIDTH / 2 + getWidth() / 2, GameConfig.WORLD_HEIGHT / 2);
        super.setDefaultBoundsOrigin();
        super.setDefaultRotation();
        super.setShapeColor(new Color(Color.valueOf("#FFF159")));
    }
}
