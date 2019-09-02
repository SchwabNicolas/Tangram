package ch.ceff.libgdx.tangram.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityFactory {
    private final TextureAtlas textureAtlas;

    public EntityFactory(AssetManager assetManager) {
        textureAtlas = assetManager.get("gameplay/shapes.atlas", TextureAtlas.class);
    }

    public Parallelogram createParallelogram() {
        TextureRegion region = textureAtlas.findRegion("parallelogramme");
        return new Parallelogram(region);
    }

    public Square createSquare() {
        TextureRegion region = textureAtlas.findRegion("carre");
        return new Square(region);
    }
}
