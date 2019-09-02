package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public class Square extends Shape {
    public Square(TextureRegion textureRegion) {
        super(textureRegion, new Polygon());
        super.setBounds(new Polygon(new float[]{
                getWidth() / 2f, 0,
                getWidth(), getHeight() / 2f,
                getWidth() / 2f, getHeight(),
                0, getHeight() / 2
        }));
        super.setPosition(getWidth() / 2, getHeight() + 2.5f);
        super.setDefaultBoundsOrigin();
        super.setDefaultRotation();
    }


}
