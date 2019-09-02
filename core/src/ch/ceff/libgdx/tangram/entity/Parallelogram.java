package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public class Parallelogram extends Shape {

    public Parallelogram(TextureRegion textureRegion) {
        super(textureRegion, new Polygon());
        super.setBounds(new Polygon(new float[]{
                0, 0,
                getWidth() / 3 * 2, 0,
                getWidth(), getHeight(),
                getWidth() / 3, getHeight()
        }));
        super.setPosition(getWidth()/2, getHeight());
        super.setDefaultBoundsOrigin();
        super.setDefaultRotation();
    }

}